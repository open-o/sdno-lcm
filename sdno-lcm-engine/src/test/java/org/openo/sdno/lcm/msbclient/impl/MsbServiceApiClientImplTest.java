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

package org.openo.sdno.lcm.msbclient.impl;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.replay;

import org.openo.sdno.lcm.restclient.msb.model.MicroServiceFullInfo;
import org.openo.sdno.lcm.util.Constants;
import org.springframework.core.env.Environment;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test(groups = {"sdno-lcm-unit"})
public class MsbServiceApiClientImplTest {

    private static final String DUMMY_PATH = "http://127.0.0.1:9999/openbanana/catamaran/v987654321";

    MsbServiceApiClientImpl msbClient;

    Environment mockEnvironment;

    @BeforeMethod
    public void before() {
        msbClient = new MsbServiceApiClientImpl();
        mockEnvironment = mock(Environment.class);
        msbClient.setEnv(mockEnvironment);
        expect(mockEnvironment.getProperty("server.port")).andReturn("8554");
        expect(mockEnvironment.getProperty(Constants.SDNO_LCM_DEFAULT_IP)).andReturn("127.0.0.1");
        expect(mockEnvironment.getProperty("server.contextPath")).andReturn("sdno-lcm/v1/whatever");
        expect(mockEnvironment.getRequiredProperty(Constants.COMMON_SERVICES_MSB_BASE_PATH)).andReturn(DUMMY_PATH);
        replay(mockEnvironment);
    }

    /**
     * Test result is null when there is no MSB at the URI
     */
    @Test
    public void register() {
        MicroServiceFullInfo microServiceFullInfo = msbClient.register();
        Assert.assertNull(microServiceFullInfo);
    }
}
