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

import java.util.HashMap;
import java.util.Map;

import org.openo.sdno.lcm.catalogclient.ModelResourceApiClient;
import org.openo.sdno.lcm.catalogclient.PackageResourceApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.easymock.EasyMock.*;

@Test(groups = {"sdno-lcm-unit"})
@ContextConfiguration(locations = {"classpath:spring-test-config.xml"})
public class LcmStateEngineTest extends AbstractTestNGSpringContextTests {

    @Autowired
    LcmStateEngine lcmStateEngine;

    ModelResourceApiClient mockModelResourceApiClient;

    PackageResourceApiClient mockPackageResourceApiClient;

    @BeforeMethod
    public void before() {

        // set mock API clients
        mockModelResourceApiClient = mock(ModelResourceApiClient.class);

        mockPackageResourceApiClient = mock(PackageResourceApiClient.class);
    }

    @Test
    public void execute() {

        // simple test to exercise the code a little for now
        replay(mockModelResourceApiClient);
        replay(mockPackageResourceApiClient);

        Map<String, String> params = new HashMap<String, String>();
        lcmStateEngine.execute(params);
    }

    @Test
    public void executeCreate() {

        // simple test to exercise the code a little for now
        replay(mockModelResourceApiClient);
        replay(mockPackageResourceApiClient);

        lcmStateEngine.executeCreate("csarName");
    }
}
