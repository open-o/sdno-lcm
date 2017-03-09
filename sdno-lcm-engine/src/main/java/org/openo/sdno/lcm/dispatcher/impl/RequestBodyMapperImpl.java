/*
 * Copyright 2017 Huawei Technologies Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.openo.sdno.lcm.dispatcher.impl;

import org.openo.sdno.lcm.model.workplan.WorkItem;
import io.swagger.models.Swagger;
import org.openo.sdno.lcm.templatemodel.service.Node;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.Iterator;
import java.io.IOException;
import java.util.logging.Logger;

import org.openo.sdno.lcm.util.SwaggerUtils;
import org.openo.sdno.lcm.exception.LcmInternalException;

import static com.google.common.base.Preconditions.*;
import org.springframework.stereotype.Component;

import org.openo.sdno.lcm.dispatcher.RequestBodyMapper;

@Component
public class RequestBodyMapperImpl implements RequestBodyMapper {
    private final Logger logger = Logger.getLogger("RequestBodyMapperImpl");

    private JsonNodeFactory nodeFactory;

    /**
     * Constructor
     */
    public RequestBodyMapperImpl() {
        nodeFactory = JsonNodeFactory.instance;
    }

    /**
     * Create the body of Http Request needed to fullfil a task (WorkItem) in the workplan.
     * Notes:
     * WorkItem contains node instance (properties), swagger specification, and mapper
     * specification that are needed to map node properties to Http Request body.
     *
     * @param workItem the WorkItem to be executed
     * @return JsonNode whose serialization will be used as the body of Http Request
     *         that fullfils the task.
     */
    @Override
    public JsonNode map(final WorkItem workItem) {
        checkNotNull(workItem, "Work item cannot be null.");

        //prepare input parameters needed by mapping();
        JsonNode mapperSpec = workItem.getMapperSpec();
        String modelName = SwaggerUtils.getBodyModelNameFromSwagger(workItem.getSwaggerSpec(),
                                                                    workItem.getApiUrl(), workItem.getMethod());
        if(null==modelName) return null;

        JsonNode nodeProperties = workItem.getNode().getProperties();

        ObjectNode objectNode = nodeFactory.objectNode();  //the output

        //real workhorse
        mapping(nodeProperties, mapperSpec, modelName, objectNode);

        //return as JsonNode
        JsonNode resultNode = null;
        try {
            resultNode = (JsonNode) new ObjectMapper().readTree(objectNode.toString());
        } catch(IOException e) {
            logger.severe(
                String.format("Unexpected error happens when converting ObjectNode to JsonNode. ObjectNode: %s",
                              objectNode.toString()));

            throw new LcmInternalException("JSON format error.", e);
        }
        return resultNode;
    }

    /**
     * Create one Json object needed by the body of Http request based on mapper specification,
     * the name of data model in API specification, and the values in node properties.
     * "protected" is used temporarily here to test this method directly since it's a little hard
     * to prepare a proper work item.
     *
     * @param properties the properties in node instance
     * @param mapperSpec the whole specification for the mapper
     * @param modelName teh name of the model/type to be constructed
     * @param objectNode used to hold the constructed JSON. act as the output.
     */
    protected void mapping(JsonNode properties, JsonNode mapperSpec, String modelName, ObjectNode objectNode) {
        checkNotNull(properties, "Properties (node instance) cannot be null.");
        checkNotNull(mapperSpec, "Mapper specification cannot be null.");
        checkNotNull(objectNode, "object node (output) cannot be null.");
        checkArgument((modelName!=null)&&(modelName.length()>0), "Model name cannot be null or empty.");

        //get related mapping rules
        ObjectNode mappingRules = null;
        try {
            mappingRules = (ObjectNode) new ObjectMapper().readTree((mapperSpec.get(modelName)).toString());
        } catch(IOException e) {
            logger.severe(
                String.format("Unexpected error happens when converting mapper spec to ObjectNode. Mapper Spec: %s",
                              mapperSpec.get(modelName).toString()));
            throw new LcmInternalException("JSON format error.", e);
        }

        //process mapping rules one by one
        Iterator<String> ruleNameIterator = mappingRules.fieldNames();
        while(ruleNameIterator.hasNext()) {
            //construct mapping rule class
            String keyStr = ruleNameIterator.next();
            String valueStr = (mappingRules.get(keyStr)).asText();
            RequestBodyMappingRule rule = new RequestBodyMappingRule(keyStr, valueStr);

            //get corresponding value node in properties
            String apiFieldName = rule.getKeyName();
            String nodePropertyName = rule.getValueName();
            JsonNode valueNode = (properties.get(nodePropertyName));

            //skip if properties has no such value (optional property)
            if(null == valueNode) continue;

            //variables for holding temporary results
            ArrayNode arrayNode = null;
            ArrayNode valueArray = null;
            ObjectNode newObjectNode = null;
            JsonNode value = null;
            switch(rule.getKeyType()) {
                case SCALAR:  //mapping scalar
                    value = valueNode.get("value");
                    objectNode.put(apiFieldName, value);
                    break;
                case OBJECT: //mapping object
                    //create new object node for sub-model and add to the node for output.
                    newObjectNode = nodeFactory.objectNode();
                    objectNode.put(apiFieldName, newObjectNode);

                    //handle the case that one type (object) in API has no correponding definition in TOSCA template
                    if(rule.getValueName().equals(RequestBodyMappingRule.NO_STEP_INTO_STR)) {
                        valueNode = properties;
                    }

                    mapping(valueNode, mapperSpec, rule.getKeyObjectModelName(), newObjectNode);
                    break;
                case SCALAR_ARRAY: //mapping scalar array
                    //create new array node and add to the node for output.
                    arrayNode = nodeFactory.arrayNode();
                    objectNode.put(apiFieldName, arrayNode);

                    //convert valueNode to array node
                    try {
                        valueArray = (ArrayNode) new ObjectMapper().readTree(valueNode.toString());
                    } catch(IOException e) {
                        logger.severe(
                            String.format("Unexpected error when converting value node to ArrayNode. value node: %s",
                                          valueNode.toString()));
                        throw new LcmInternalException("JSON format error", e);
                    }

                    //mapping array items one by one
                    for(int i=0; i<valueArray.size(); i++) {
                        JsonNode itemNode = valueArray.get(i);
                        value = itemNode.get("value");
                        arrayNode.add(value);
                    }

                    break;
                case OBJECT_ARRAY: //mapping object array
                    //create new array node and add to the node for output.
                    arrayNode = nodeFactory.arrayNode();
                    objectNode.put(apiFieldName, arrayNode);

                    //convert valueNode to array node
                    try {
                        valueArray = (ArrayNode) new ObjectMapper().readTree(valueNode.toString());
                    } catch(IOException e) {
                        logger.severe(
                            String.format("Unexpected error when converting value node to ArrayNode. value node: %s",
                                          valueNode.toString()));
                        throw new LcmInternalException("JSON format error", e);
                    }

                    //mapping array items (sub-model) one by one
                    for(int i=0; i<valueArray.size(); i++) {
                        JsonNode itemNode = valueArray.get(i);
                        newObjectNode = nodeFactory.objectNode();
                        arrayNode.add(newObjectNode);
                        mapping(itemNode, mapperSpec, rule.getKeyObjectModelName(), newObjectNode);
                    }

                    break;
                default:
                    throw new LcmInternalException(
                        String.format("Unknown API Field Type is found in mapping rule. mapping key: %s", keyStr));
            }
        }
    }
}