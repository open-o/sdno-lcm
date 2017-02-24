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

import org.openo.sdno.lcm.model.workplan.WorkItem;
import com.fasterxml.jackson.databind.JsonNode;


import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;


@Test(groups = {"sdno-lcm-unit"})
@ContextConfiguration(locations = {"classpath:spring-test-config.xml"})
public class RequestBodyMapperImplTest extends AbstractTestNGSpringContextTests {

    @Test
    public void testWithEmptyMapSpec() {
        return;
    }

}
