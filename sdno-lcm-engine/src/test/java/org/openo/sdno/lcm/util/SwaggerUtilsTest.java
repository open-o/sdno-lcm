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

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import static org.testng.Assert.*;

import org.apache.commons.io.FileUtils;
import java.nio.charset.Charset;
import java.io.IOException;

import java.util.List;

import io.swagger.models.Swagger;
import io.swagger.util.Yaml;
import io.swagger.models.HttpMethod;
import io.swagger.models.parameters.BodyParameter;
import io.swagger.models.parameters.PathParameter;
import io.swagger.models.parameters.QueryParameter;
import io.swagger.models.parameters.HeaderParameter;

/**
 * Unit tests for SwaggerUtils
 */
@Test(groups = {"sdno-lcm-unit"})
@ContextConfiguration(locations = {"classpath:spring-test-config.xml"})
public class SwaggerUtilsTest extends AbstractTestNGSpringContextTests {
    private Swagger swagger;

    @BeforeClass
    public void initialize() {
        swagger = null;
        try {
            String content = FileUtils.readFileToString(FileUtils.getFile("src","test", "resources", "swagger-test.jaml"), Charset.defaultCharset());
            swagger = Yaml.mapper().readValue(content, Swagger.class);
        }  catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetConsumeSuccessfully() {
        String consume = SwaggerUtils.getConsumeFromSwagger(swagger, "/openoapi/sdnoservicechain/v1/paths", HttpMethod.POST);
        assertEquals(consume, "application/json", "Unexpected consume is returned!");
    }

    @Test
    public void testGetConsumeFromNonexistOperation() {
        String consume = SwaggerUtils.getConsumeFromSwagger(swagger, "/openoapi/sdnoservicechain/v1/XXX", HttpMethod.POST);
        assertNull(consume, "Consume is not null!");
    }

    @Test
    public void testGetNonexistHeaderParameters() {
        List<HeaderParameter> hps = SwaggerUtils.getHeaderParametersFromSwagger(swagger, "/openoapi/sdnoservicechain/v1/paths", HttpMethod.POST);
        assertEquals(hps.size(), 0, "Unexpected header parameters are returned!");
    }

    @Test
    public void testGetHeaderParametersFromNonexistOperation() {
        List<HeaderParameter> hps = SwaggerUtils.getHeaderParametersFromSwagger(swagger, "/openoapi/sdnoservicechain/v1/paths", HttpMethod.GET);
        assertEquals(hps.size(), 0, "Unexpected header parameters are returned!");
    }

    @Test
    public void testGetPathParametersSuccessfully() {
        List<PathParameter> pps = SwaggerUtils.getPathParametersFromSwagger(swagger, "/openoapi/sdnoservicechain/v1/paths/{uuid}", HttpMethod.DELETE);
        assertEquals(pps.size(), 1, "Unexpected path parameters are returned!");
        assertEquals(pps.get(0).getName(), "uuid", "Unexpected path parameters are returned!");
    }

    @Test
    public void testGetNonexistPathParameters() {
        List<PathParameter> pps = SwaggerUtils.getPathParametersFromSwagger(swagger, "/openoapi/sdnoservicechain/v1/paths", HttpMethod.POST);
        assertEquals(pps.size(), 0, "Unexpected path parameters are returned!");
    }

    @Test
    public void testGetPathParametersFromNonexistOperation() {
        List<PathParameter> pps = SwaggerUtils.getPathParametersFromSwagger(swagger, "/openoapi/sdnoservicechain/v1/XXX", HttpMethod.POST);
        assertEquals(pps.size(), 0, "Unexpected path parameters are returned!");
    }

    @Test
    public void testGetNonexistQueryParameters() {
        List<QueryParameter> qps = SwaggerUtils.getQueryParametersFromSwagger(swagger, "/openoapi/sdnoservicechain/v1/paths", HttpMethod.POST);
        assertEquals(qps.size(), 0, "Unexpected query parameters are returned!");
    }

    @Test
    public void testGetBodyModelNameSuccessfully() {
        String modelName = SwaggerUtils.getBodyModelNameFromSwagger(swagger, "/openoapi/sdnoservicechain/v1/paths", HttpMethod.POST);
        assertEquals(modelName, "ServiceChainPath", "Body Model Name is wrong!");
    }

    @Test
    public void testGetNonexistBodyModelName() {
        String modelName = SwaggerUtils.getBodyModelNameFromSwagger(swagger, "/openoapi/sdnoservicechain/v1/paths/{uuid}", HttpMethod.DELETE);
        assertNull(modelName, "Body Model Name is not null!");
    }

    @Test
    public void testGetBodyModelNameFromNonexistOperation() {
        String modelName = SwaggerUtils.getBodyModelNameFromSwagger(swagger, "/openoapi/sdnoservicechain/v1/paths/XXX", HttpMethod.GET);
        assertNull(modelName, "Body Model Name is not null!");
    }
}
