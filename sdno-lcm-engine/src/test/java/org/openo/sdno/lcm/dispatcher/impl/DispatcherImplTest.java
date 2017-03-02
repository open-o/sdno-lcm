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

import java.util.List;
import java.nio.charset.Charset;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

import org.apache.http.HttpResponse;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.ProtocolVersion;

import io.swagger.util.Yaml;
import io.swagger.models.Swagger;
import io.swagger.models.HttpMethod;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.openo.sdno.lcm.model.workplan.WorkItem;
import org.openo.sdno.lcm.model.workplan.WorkPlan;
import org.openo.sdno.lcm.model.workplan.WorkPlanExecutionResult;
import org.openo.sdno.lcm.model.workplan.WorkPlanExecutionStrategy;
import org.openo.sdno.lcm.templatemodel.service.Node;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import static org.testng.Assert.*;
import static org.easymock.EasyMock.*;

import org.openo.sdno.lcm.genericclient.GenericApiClient;
import org.openo.sdno.lcm.exception.LcmInternalException;
import org.openo.sdno.lcm.exception.ExternalComponentException;

@Test(groups = {"sdno-lcm-unit"})
@ContextConfiguration(locations = {"classpath:spring-test-config.xml"})
public class DispatcherImplTest extends AbstractTestNGSpringContextTests {
    @Autowired
    DispatcherImpl dispatcher;

    private WorkPlan plan;

    @BeforeClass
    public void initialize() {
        Swagger swagger = null;
        JsonNode properties = null;
        JsonNode mapperSpec = null;
        try {
            String content = FileUtils.readFileToString(
                FileUtils.getFile("src","test", "resources", "swagger-test.jaml"), Charset.defaultCharset());
            swagger = Yaml.mapper().readValue(content, Swagger.class);

            String propertyContent = FileUtils.readFileToString(
                FileUtils.getFile("src","test", "resources", "node-properties-test.json"), Charset.defaultCharset());
            properties = (JsonNode) new ObjectMapper().readTree(propertyContent);

            String mapperSpecContent = FileUtils.readFileToString(
                FileUtils.getFile("src","test", "resources", "mapper-test.tsmap"), Charset.defaultCharset());
            mapperSpec = (JsonNode) new ObjectMapper().readTree(mapperSpecContent);
        }  catch(IOException e) {
            assertTrue(false, "Files cannot be parsed successfully.");
        }

        Node node = new Node();
        node.setProperties(properties);

        WorkItem item1 = new WorkItem(node, swagger, mapperSpec, "/openoapi/sdnoservicechain/v1/paths/test", HttpMethod.GET);
        WorkItem item2 = new WorkItem(node, swagger, mapperSpec, "/openoapi/sdnoservicechain/v1/paths/test", HttpMethod.GET);
        WorkItem item3 = new WorkItem(node, swagger, mapperSpec, "/openoapi/sdnoservicechain/v1/paths/test", HttpMethod.GET);
        WorkItem item4 = new WorkItem(node, swagger, mapperSpec, "/openoapi/sdnoservicechain/v1/paths/test", HttpMethod.GET);

        plan = new WorkPlan();
        plan.insert(item1);
        plan.insert(item2);
        plan.insert(item3);
        plan.insert(item4);
    }

    @Test
    public void testWorkPlanSuccess() {
        HttpResponse rsp = new BasicHttpResponse(new ProtocolVersion("http",1,1), 200, "Success");
        GenericApiClient mockClient = mock(GenericApiClient.class);
        dispatcher.setGenericApiClient(mockClient);
        expect(mockClient.execute(anyObject(), anyObject(), anyObject(), anyObject(),
                                  anyObject(), anyObject(), anyObject(), anyObject()))
            .andReturn(rsp).times(4);
        replay(mockClient);

        WorkPlanExecutionResult result = dispatcher.dispatch(plan, WorkPlanExecutionStrategy.FAIL_FAST);
        assertTrue(result.getOverallResult(), "execution doesn't handle response correctly.");
    }

    @Test
    public void testWorkPlanFailFast() {
        HttpResponse rsp = new BasicHttpResponse(new ProtocolVersion("http",1,1), 200, "Success");
        GenericApiClient mockClient = mock(GenericApiClient.class);
        dispatcher.setGenericApiClient(mockClient);
        expect(mockClient.execute(anyObject(), anyObject(), anyObject(), anyObject(),
                                  anyObject(), anyObject(), anyObject(), anyObject()))
            .andReturn(rsp)
            .andThrow(new ExternalComponentException("Internal Exception"));
        replay(mockClient);

        WorkPlanExecutionResult result = dispatcher.dispatch(plan, WorkPlanExecutionStrategy.FAIL_FAST);
        assertFalse(result.getOverallResult(), "execution doesn't handle response correctly.");
        assertEquals(result.getSucceededItems().size(), 1, "the number of success item is wrong.");
        assertEquals(result.getFailedItems().size(), 1, "the number of failed item is wrong.");
        assertEquals(result.getUnprocessedItems().size(), 2, "the number of unprocessed item is wrong.");
    }

    @Test
    public void testWorkPlanContinueExecution() {
        HttpResponse rsp = new BasicHttpResponse(new ProtocolVersion("http",1,1), 200, "Success");
        GenericApiClient mockClient = mock(GenericApiClient.class);
        dispatcher.setGenericApiClient(mockClient);
        expect(mockClient.execute(anyObject(), anyObject(), anyObject(), anyObject(),
                                  anyObject(), anyObject(), anyObject(), anyObject()))
            .andReturn(rsp)
            .andThrow(new LcmInternalException("Internal Exception"))
            .andReturn(rsp)
            .andThrow(new LcmInternalException("External Exception"));
        replay(mockClient);

        WorkPlanExecutionResult result = dispatcher.dispatch(plan, WorkPlanExecutionStrategy.CONTINUE_EXECUTE);
        assertFalse(result.getOverallResult(), "execution doesn't handle response correctly.");
        assertEquals(result.getSucceededItems().size(), 2, "the number of success item is wrong.");
        assertEquals(result.getFailedItems().size(), 2, "the number of failed item is wrong.");
        assertEquals(result.getUnprocessedItems().size(), 0, "the number of unprocessed item is wrong.");
    }

    @Test
    public void testExecuteWorkItemSuccess() {
        HttpResponse rsp = new BasicHttpResponse(new ProtocolVersion("http",1,1), 200, "Success");
        GenericApiClient mockClient = mock(GenericApiClient.class);
        dispatcher.setGenericApiClient(mockClient);
        expect(mockClient.execute(anyObject(), anyObject(), anyObject(), anyObject(),
                                  anyObject(), anyObject(), anyObject(), anyObject()))
            .andReturn(rsp);
        replay(mockClient);

        boolean result = dispatcher.executeWorkItem(plan.getWorkItem(0));
        assertTrue(result, "execution doesn't handle response correctly.");
    }

    @Test
    public void testExecuteWorkItemCatchException() {
        GenericApiClient mockClient = mock(GenericApiClient.class);
        dispatcher.setGenericApiClient(mockClient);
        expect(mockClient.execute(anyObject(), anyObject(), anyObject(), anyObject(),
                                  anyObject(), anyObject(), anyObject(), anyObject()))
            .andThrow(new LcmInternalException("Internal Exception"))
            .andThrow(new ExternalComponentException("External Exception"));
        replay(mockClient);

        boolean result = dispatcher.executeWorkItem(plan.getWorkItem(0));
        assertFalse(result, "execution doesn't handle response correctly.");
        result = dispatcher.executeWorkItem(plan.getWorkItem(1));
        assertFalse(result, "execution doesn't handle response correctly.");
    }


    @Test
    public void testSuccessJudge() {
        HttpResponse rsp = new BasicHttpResponse(new ProtocolVersion("http",1,1), 200, "Success");
        boolean success = dispatcher.isSuccessful(rsp);
        assertTrue(success, "wrong judgement");

        rsp = new BasicHttpResponse(new ProtocolVersion("http",1,1), 202, "Success");
        success = dispatcher.isSuccessful(rsp);
        assertTrue(success, "wrong judgement");

        rsp = new BasicHttpResponse(new ProtocolVersion("http",1,1), 300, "Fail");
        success = dispatcher.isSuccessful(rsp);
        assertFalse(success, "wrong judgement");

        rsp = new BasicHttpResponse(new ProtocolVersion("http",1,1), 199, "Fail");
        success = dispatcher.isSuccessful(rsp);
        assertFalse(success, "wrong judgement");
    }
}
