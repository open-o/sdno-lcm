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

package org.openo.sdno.lcm.templateinstanceparser.impl;

import java.util.Map;
import java.util.logging.Logger;

import org.openo.sdno.lcm.exception.LcmInternalException;
import org.openo.sdno.lcm.templateinstanceparser.TemplateInstanceParser;
import org.openo.sdno.lcm.templatemodel.service.Instance;
import org.openo.sdno.lcm.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class TemplateInstanceParserImpl implements TemplateInstanceParser {

    private final Logger log = Logger.getLogger("TemplateInstanceParserImpl");

    private Mapper mapper;

    /*
     * (non-Javadoc)
     * @see org.openo.sdno.lcm.templateinstanceparser.TemplateInstanceParser#parse(java.lang.String)
     */
    @Override
    public Instance parse(String serviceInstanceJson) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode root = objectMapper.readTree(serviceInstanceJson);

            // Get Name
            JsonNode instanceNode = root.path("instance");

            Instance instancePojo = objectMapper.treeToValue(instanceNode, Instance.class);

            return instancePojo;

        } catch(Exception e) {
            throw new LcmInternalException("Failed to parse serviceInstanceJson", e);
        }
    }

    @Override
    public Instance parse(Map<String, Object> serviceInstanceMap) {

        // Instance instance = new Instance();
        // instance.setDescription((String)serviceInstanceMap.get("description"));
        // instance.setMetadata((Metadata)serviceInstanceMap.get("metadata"));
        // LinkedHashMap<String, Object> nodeMap = (LinkedHashMap<String,
        // Object>)serviceInstanceMap.get("nodes");
        // List<Node> nodeList = new ArrayList<>();
        // for(Object n:nodeMap.values()) {
        //
        //
        // Node node = mapper.mapToBean(Node.class, (Map<String,Object>)n);
        // nodeList.add(node);
        // }
        // instance.setNodes(nodeList);
        Object instanceObj = serviceInstanceMap.get("instance");
        Instance instance = mapper.mapToBean(Instance.class, (Map<String, Object>)instanceObj);
        log.info("Parsed Instance from Map:\n" + instance.toString());
        return instance;
    }

    @Autowired
    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }

}
