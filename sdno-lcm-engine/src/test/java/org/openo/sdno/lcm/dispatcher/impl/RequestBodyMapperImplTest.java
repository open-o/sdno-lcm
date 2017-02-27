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

import org.apache.commons.io.FileUtils;
import java.nio.charset.Charset;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import static org.testng.Assert.*;

import org.openo.sdno.lcm.dispatcher.RequestBodyMapper;

/**
 * This class test RequestBodyMapperImpl based on "node-properties-test.json" and "mapper-test.tsmap".
 * Below are the expected JSON nodes to be returned.
 *   ModelWithScalar:  {"id":"MyId","name":"MyName","size":1,"enabled":true}
 *   ModelWithScalarArray:  {"id":"MyId","numbers":[1,2]}
 *   ModelWithSubModel:  {"id":"MyId","address":{"ip":"10.0.0.1","port":80}}
 *   ModelWithObjectArray:  {"id":"MyId","rules":[{"protocol":"Protocol1","policy":"Policy1"},{"protocol":"Protocol2","policy":"Policy2"}]}
*/

@Test(groups = {"sdno-lcm-unit"})
@ContextConfiguration(locations = {"classpath:spring-test-config.xml"})
public class RequestBodyMapperImplTest extends AbstractTestNGSpringContextTests {
    private RequestBodyMapperImpl mapper;
    private JsonNode properties;
    private JsonNode mapperSpec;
    private JsonNodeFactory nodeFactory;

    @BeforeClass
    public void initialize() {
        nodeFactory = JsonNodeFactory.instance;
        mapper = new RequestBodyMapperImpl();

        try {
            String propertyContent = FileUtils.readFileToString(FileUtils.getFile("src","test", "resources", "node-properties-test.json"), Charset.defaultCharset());
            properties = (JsonNode) new ObjectMapper().readTree(propertyContent);

            String mapperSpecContent = FileUtils.readFileToString(FileUtils.getFile("src","test", "resources", "mapper-test.tsmap"), Charset.defaultCharset());
            mapperSpec = (JsonNode) new ObjectMapper().readTree(mapperSpecContent);
        }  catch(IOException e) {
            assertTrue(false, "Mapper specification and/or node properties files are missed or the content cannot be parsed successfully.");
        }
    }


    @Test
    public void testModelWithScalar() {
        ObjectNode objectNode = nodeFactory.objectNode();
        mapper.mapping(properties, mapperSpec, "ModelWithScalar", objectNode);

        assertEquals(objectNode.get("id").asText(), "MyId", "Unexpected value is returned!");
        assertEquals(objectNode.get("name").asText(), "MyName", "Unexpected value is returned!");
        assertEquals(objectNode.get("size").asInt(), 1, "Unexpected value is returned!");
        assertTrue(objectNode.get("enabled").asBoolean(), "Unexpected value is returned!");

        return;
    }

    @Test
    public void testModelWithScalarArray() {
        ObjectNode objectNode = nodeFactory.objectNode();
        mapper.mapping(properties, mapperSpec, "ModelWithScalarArray", objectNode);

        assertEquals(objectNode.get("id").asText(), "MyId", "Unexpected value is returned!");
        assertEquals(objectNode.get("numbers").get(0).asInt(), 1, "Unexpected value is returned!");
        assertEquals(objectNode.get("numbers").get(1).asInt(), 2, "Unexpected value is returned!");

        return;
    }

    @Test
    public void testModelWithSubModel() {
        ObjectNode objectNode = nodeFactory.objectNode();
        mapper.mapping(properties, mapperSpec, "ModelWithSubModel", objectNode);

        assertEquals(objectNode.get("id").asText(), "MyId", "Unexpected value is returned!");
        assertEquals(objectNode.get("address").get("ip").asText(), "10.0.0.1", "Unexpected value is returned!");
        assertEquals(objectNode.get("address").get("port").asInt(), 80, "Unexpected value is returned!");

        return;
    }

    @Test
    public void testModelWithObjectArray() {
        ObjectNode objectNode = nodeFactory.objectNode();
        mapper.mapping(properties, mapperSpec, "ModelWithObjectArray", objectNode);

        assertEquals(objectNode.get("id").asText(), "MyId", "Unexpected value is returned!");
        assertEquals(objectNode.get("rules").get(0).get("protocol").asText(), "Protocol1", "Unexpected value is returned!");
        assertEquals(objectNode.get("rules").get(0).get("policy").asText(), "Policy1", "Unexpected value is returned!");
        assertEquals(objectNode.get("rules").get(1).get("protocol").asText(), "Protocol2", "Unexpected value is returned!");
        assertEquals(objectNode.get("rules").get(1).get("policy").asText(), "Policy2", "Unexpected value is returned!");

        return;
    }
}