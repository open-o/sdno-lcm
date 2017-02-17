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

import org.openo.sdno.lcm.exception.LcmInternalException;
import org.openo.sdno.lcm.templateinstanceparser.TemplateInstanceParser;
import org.openo.sdno.lcm.templatemodel.service.Instance;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class TemplateInstanceParserImpl implements TemplateInstanceParser {

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

            // JsonNode nodesNode = instanceNode.path("nodes");
            //
            // Iterator<JsonNode> elements = nodesNode.elements();
            // ((Iterable<JsonNode>)elements).forEach((k) -> System.out.println("element : " + k));

            // StreamSupport.stream(root.spliterator(), false /* or whatever */);

        } catch(Exception e) {
            throw new LcmInternalException("Failed to parse serviceInstanceJson", e);
        }
    }

}
