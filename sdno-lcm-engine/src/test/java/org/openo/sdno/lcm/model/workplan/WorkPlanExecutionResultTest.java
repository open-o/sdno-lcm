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

package org.openo.sdno.lcm.model.workplan;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.models.HttpMethod;
import io.swagger.models.Swagger;
import org.openo.sdno.lcm.templatemodel.service.Node;

import org.openo.sdno.lcm.exception.LcmInternalException;

/**
 * Unit tests for SwaggerUtils
 */
@Test(groups = {"sdno-lcm-unit"})
@ContextConfiguration(locations = {"classpath:spring-test-config.xml"})
public class WorkPlanExecutionResultTest extends AbstractTestNGSpringContextTests {

    @Test
    public void testCreateInsertGet() {
        WorkPlanExecutionResult result = new WorkPlanExecutionResult();

        WorkItem item1 = new WorkItem(new Node(), new Swagger(), JsonNodeFactory.instance.objectNode(), "/api1", HttpMethod.GET);
        WorkItem item2 = new WorkItem(new Node(), new Swagger(), JsonNodeFactory.instance.objectNode(), "/api2", HttpMethod.POST);
        WorkItem item3 = new WorkItem(new Node(), new Swagger(), JsonNodeFactory.instance.objectNode(), "/api3", HttpMethod.PUT);
        WorkItem item4 = new WorkItem(new Node(), new Swagger(), JsonNodeFactory.instance.objectNode(), "/api4", HttpMethod.GET);
        WorkItem item5 = new WorkItem(new Node(), new Swagger(), JsonNodeFactory.instance.objectNode(), "/api5", HttpMethod.POST);
        WorkItem item6 = new WorkItem(new Node(), new Swagger(), JsonNodeFactory.instance.objectNode(), "/api6", HttpMethod.PUT);

        result.setOverallResult(false);
        result.addSucceededItem(item1);
        result.addFailedItem(item2);
        result.addFailedItem(item3);
        result.addUnprocessedItem(item4);
        result.addUnprocessedItem(item5);
        result.addUnprocessedItem(item6);

        assertFalse(result.getOverallResult(), "Unexpected result is returned.");
        assertEquals(result.getSucceededItems().size(), 1, "Succeeded Item List size is wrong.");
        assertEquals(result.getFailedItems().size(), 2, "Failed Item List size is wrong.");
        assertEquals(result.getUnprocessedItems().size(), 3, "Unprocessed Item List size is wrong.");
    }
}
