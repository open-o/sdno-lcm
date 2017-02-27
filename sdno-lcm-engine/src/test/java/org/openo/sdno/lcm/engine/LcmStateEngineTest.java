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

package org.openo.sdno.lcm.engine;

import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.replay;

import java.util.HashMap;
import java.util.Map;

import org.openo.sdno.lcm.catalogclient.ModelResourceApiClient;
import org.openo.sdno.lcm.catalogclient.PackageResourceApiClient;
import org.openo.sdno.lcm.engine.impl.LcmStateEngineImpl;
import org.openo.sdno.lcm.engine.impl.WorkflowRegistry;
import org.openo.sdno.lcm.exception.LcmInternalException;
import org.openo.sdno.lcm.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test(groups = {"sdno-lcm-unit"})
@ContextConfiguration(locations = {"classpath:spring-test-config.xml"})
public class LcmStateEngineTest extends AbstractTestNGSpringContextTests {

    @Autowired
    LcmStateEngineImpl lcmStateEngine;

    @Autowired
    WorkflowRegistry workflowRegistry;

    ModelResourceApiClient mockModelResourceApiClient;

    PackageResourceApiClient mockPackageResourceApiClient;

    @BeforeMethod
    public void before() {

        // set mock API clients
        mockModelResourceApiClient = mock(ModelResourceApiClient.class);

        mockPackageResourceApiClient = mock(PackageResourceApiClient.class);
    }

    /**
     * Empty input params so we expect an exception
     */
    @Test(expectedExceptions = LcmInternalException.class)
    public void executeEmptyParams() {

        // simple test to exercise the code a little for now
        replay(mockModelResourceApiClient);
        replay(mockPackageResourceApiClient);

        Map<String, Object> params = new HashMap<String, Object>();
        lcmStateEngine.execute(params);
    }

    /**
     * Test no such workflow registered (create)
     */
    @Test(expectedExceptions = LcmInternalException.class)
    public void executeCsarName() {

        // simple test to exercise the code a little for now
        replay(mockModelResourceApiClient);
        replay(mockPackageResourceApiClient);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put(Constants.LCM_NBI_CSAR_NAME, "myLittleCsar");
        lcmStateEngine.execute(params);
    }

    /**
     * Test no such workflow registered (non-create)
     */
    @Test(expectedExceptions = LcmInternalException.class)
    public void executeServiceId() {

        // simple test to exercise the code a little for now
        replay(mockModelResourceApiClient);
        replay(mockPackageResourceApiClient);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put(Constants.LCM_NBI_SERVICE_ID, "slimShady");
        // workflows are not implemented
        Assert.assertNull(lcmStateEngine.execute(params));
    }

}
