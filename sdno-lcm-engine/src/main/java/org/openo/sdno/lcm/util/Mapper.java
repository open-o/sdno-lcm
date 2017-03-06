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

package org.openo.sdno.lcm.util;

import java.util.Map;
import java.util.logging.Logger;

import org.openo.sdno.lcm.exception.LcmInternalException;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author mark
 */
@Component
public class Mapper {

    private static final Logger log = Logger.getLogger("Mapper");

    /**
     * Fills a Map with the values of the bean-style object's properties.
     * Limited to String properties for now. Works by checking the getters, not
     * the fields! Doesn't handle non-String primitive fields.
     * NB does not add properties to the map that have null value
     * 
     * @param bean
     * @return
     */
    public Map<String, Object> beanToMap(Object bean) {

        ObjectMapper oMapper = new ObjectMapper();
        // do not add properties to the map that have null value
        oMapper.setSerializationInclusion(Include.NON_NULL);
        Map<String, Object> map;
        try {
            map = oMapper.convertValue(bean, Map.class);
        } catch(Exception ex) {
            log.severe(String.format("Failed to convert an object to Map<String, String> due to error %s. /n object: ",
                    ex.getMessage(), bean.toString()));
            throw new LcmInternalException(
                    "Failed to convert an object to Map - maybe the values are not simply strings", ex);
        }
        return map;
    }

    public <T> T mapToBean(T beanInstance, Map<String, Object> properties) {
        // TODO
        return null;
    }

    public JsonNode stringToNode(String string) {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readValue(string, JsonNode.class);
        } catch(Exception e) {
            log.severe(String.format("JSON cannot be parsed: \n%s", string));
            throw new LcmInternalException("Failed to parse JSON string, ", e);
        }
        return jsonNode;
    }
}
