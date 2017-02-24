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

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.openo.sdno.lcm.util.SwaggerUtils;

import static com.google.common.base.Preconditions.*;
import org.springframework.stereotype.Component;

import org.openo.sdno.lcm.dispatcher.RequestBodyMapper;

@Component
public class RequestBodyMapperImpl implements RequestBodyMapper {
    private JsonNodeFactory nodeFactory;

    //constructor
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

        JsonNode mapperSpec = workItem.getMapperSpec();
        String modelName = SwaggerUtils.getBodyModelNameFromSwagger(workItem.getSwaggerSpec(), workItem.getApiUrl(), workItem.getMethod());

        JsonNode nodeProperties = workItem.getNode().getProperties();

        JsonNode resultNode = nodeFactory.objectNode();

        mapping(nodeProperties, mapperSpec, modelName, resultNode);

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
     * @param resultNode used to hold the constructed JSON. act as the output.
     */
    protected void mapping(JsonNode properties, JsonNode mapperSpec, String modelName, JsonNode resultNode) {
        checkNotNull(properties, "Properties (node instance) cannot be null.");
        checkNotNull(mapperSpec, "Mapper specification cannot be null.");
        checkNotNull(resultNode, "Result node cannot be null.");
        checkArgument((modelName!=null)&&(modelName.length()>0), "Model name cannot be null or empty.");

        //get related mapping rules
        JsonNode mappingRules = mapperSpec.get(modelName);
        //For each rule (Key-Value pair. Note that key is the field name in the data model and value is the property name in node instance.):
            // Check the value and format of the key.  Format is described in  SDNO-1249.
            // If the key is a basic type, simply copy node property (property name is specified in the value of the mapping rule) to the data model / Json object to be returned by Mapper.
            // If the key is one array type, use a loop to process each item.
            // If the key is a complex type, call mapping() recursively to do the mapping.
    }
}
