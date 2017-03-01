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
public class WorkPlanTest extends AbstractTestNGSpringContextTests {

    @Test
    public void testCreateInsertGet() {
        WorkPlan plan = new WorkPlan();
        WorkItem item1 = new WorkItem(new Node(), new Swagger(), JsonNodeFactory.instance.objectNode(), "/api1", HttpMethod.GET);
        WorkItem item2 = new WorkItem(new Node(), new Swagger(), JsonNodeFactory.instance.objectNode(), "/api2", HttpMethod.POST);

        plan.insert(item1);
        plan.insert(item2);

        assertEquals(plan.size(), 2, "Unexpected size is returned.");
        assertEquals(plan.getWorkItem(0).getApiUrl(), "/api1", "Unexpected apiUrl is returned.");
        assertEquals(plan.getWorkItem(1).getApiUrl(), "/api2", "Unexpected apiUrl is returned.");
        assertEquals(plan.getWorkItem(0).getMethod(), HttpMethod.GET, "Unexpected method is returned.");
        assertEquals(plan.getWorkItem(1).getMethod(), HttpMethod.POST, "Unexpected method is returned.");
    }

    @Test(expectedExceptions = LcmInternalException.class)
    public void testIllegalAccess1() {
        WorkPlan plan = new WorkPlan();
        plan.getWorkItem(-1);
    }

    @Test(expectedExceptions = LcmInternalException.class)
    public void testIllegalAccess2() {
        WorkPlan plan = new WorkPlan();
        plan.getWorkItem(2);
    }
}
