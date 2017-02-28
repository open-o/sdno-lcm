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

package org.openo.sdno.lcm.decomposer.impl;

import java.nio.charset.Charset;

//import org.apache.commons.io.FileUtils;
//import org.openo.sdno.lcm.decomposer.Decomposer;
//import org.openo.sdno.lcm.templateinstanceparser.TemplateInstanceParser;
//import org.openo.sdno.lcm.templateinstanceparser.impl.TemplateInstanceParserImpl;
//import org.openo.sdno.lcm.templatemodel.service.Instance;
//import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import org.openo.sdno.lcm.decomposer.BRSMapping;
import org.openo.sdno.lcm.util.NetworkElementMO;
import java.util.Map;
import java.util.HashMap;

@Test(groups = {"sdno-lcm-unit"})
public class BRSMappingImplTest {
	
	BRSMapping brsMapper;
	Map<String, String> condition = new HashMap<>();
	
    @Test
	public void printNodeName() 
	 {
    	brsMapper = new BRSMappingImpl();
        condition.put ("ipAddress","10.10.10.10");
        System.out.println (condition.get("ipAddress"));

        NetworkElementMO neMO = brsMapper.retrieveResourceFromBRS ("site", condition);
//		return;
	 }	
}
