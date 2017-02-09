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

package org.openo.sdno.lcm.ariaclient.impl;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.replay;

import org.openo.sdno.lcm.exception.ExternalComponentException;
import org.openo.sdno.lcm.util.Constants;
import org.springframework.core.env.Environment;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test(groups = {"sdno-lcm-unit"})
public class ParserApiClientImplTest {

    private static final String DUMMY_PATH = "http://127.0.0.1:9999/openbanana/catamaran/v987654321";

    ParserApiClientImpl parserApiClientImpl;

    Environment mockEnvironment;

    @BeforeMethod
    public void before() {
        parserApiClientImpl = new ParserApiClientImpl();
        mockEnvironment = mock(Environment.class);
        parserApiClientImpl.setEnv(mockEnvironment);

        expect(mockEnvironment.getRequiredProperty(Constants.COMMON_TOSCA_ARIA_BASE_PATH)).andReturn(DUMMY_PATH);
        replay(mockEnvironment);
    }

    /**
     * Test expected exception is throw when there is no parser at the URI
     */
    @Test(expectedExceptions = ExternalComponentException.class)
    public void parseControllerInstanceFile() {
        parserApiClientImpl.parseControllerInstanceFile(DUMMY_PATH, "{}");
    }

    /**
     * Test expected exception is throw when there is no parser at the URI
     */
    @Test(expectedExceptions = ExternalComponentException.class)
    public void parseControllerInstanceUpload() {
        parserApiClientImpl.parseControllerInstanceUpload("{}", "{}");
    }
}
