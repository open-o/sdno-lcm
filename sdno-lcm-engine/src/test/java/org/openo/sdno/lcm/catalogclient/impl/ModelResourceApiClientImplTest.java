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

package org.openo.sdno.lcm.catalogclient.impl;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.replay;

import java.util.HashMap;

import org.openo.sdno.lcm.exception.ExternalComponentException;
import org.openo.sdno.lcm.util.Constants;
import org.springframework.core.env.Environment;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test(groups = {"sdno-lcm-unit"})
public class ModelResourceApiClientImplTest {

    private static final String DUMMY_PATH = "http://127.0.0.1:9999/openbanana/catamaran/v987654321";

    private static final String FAKE_TEMPLATE_ID = "myTemplateId";

    ModelResourceApiClientImpl modelResourceApiClientImpl;

    Environment mockEnvironment;

    @BeforeMethod
    public void before() {
        modelResourceApiClientImpl = new ModelResourceApiClientImpl();
        mockEnvironment = mock(Environment.class);
        modelResourceApiClientImpl.setEnv(mockEnvironment);

        expect(mockEnvironment.getRequiredProperty(Constants.COMMON_TOSCA_CATALOG_BASE_PATH)).andReturn(DUMMY_PATH);
        replay(mockEnvironment);
    }

    /**
     * Test expected exception is throw when there is no catalog at the URI
     */
    @Test(expectedExceptions = ExternalComponentException.class)
    public void getServiceTemplateById() {

        modelResourceApiClientImpl.getServiceTemplateById(FAKE_TEMPLATE_ID);
    }

    /**
     * Test expected exception is throw when there is no catalog at the URI
     */
    @Test(expectedExceptions = ExternalComponentException.class)
    public void getServiceTemplateParameters() {

        modelResourceApiClientImpl.getServiceTemplateParameters(FAKE_TEMPLATE_ID);
    }

    /**
     * Test expected exception is throw when there is no catalog at the URI
     */
    @Test(expectedExceptions = ExternalComponentException.class)
    public void getServiceTemplateRawData() {

        modelResourceApiClientImpl.getServiceTemplateRawData(FAKE_TEMPLATE_ID, new HashMap<String, String>());
    }
}
