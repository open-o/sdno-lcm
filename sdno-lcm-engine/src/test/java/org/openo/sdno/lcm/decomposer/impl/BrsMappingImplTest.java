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
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.eclipse.jetty.util.log.Log;
import org.openo.sdno.lcm.catalogclient.impl.ModelResourceApiClientImpl;
import org.openo.sdno.lcm.decomposer.BrsMapping;
import org.openo.sdno.lcm.util.Constants;

import java.util.Map;
import java.util.HashMap;


import static org.easymock.EasyMock.anyString;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.replay;

@Test(groups = { "sdno-lcm-unit" })
public class BrsMappingImplTest {

    BrsMapping brsMapper;
    
    @Test
    public void getBrsMEBasedOnIpAddress() {
        Log.info("getBrsMEBasedOnIpAddress");
        brsMapper = new BrsMappingImpl();
        Map<String, String> condition = new HashMap<>();

        condition.put("ipAddress", "192.168.243.148");
        condition.put("id", "0");
        System.out.println(condition.get("ipAddress"));

        System.out.println(brsMapper.retrieveResourceFromBrs("Fw", condition));
    }

    @Test
    public void getvCPEBasedOnSiteName() {
        brsMapper = new BrsMappingImpl();
        Map<String, String> condition = new HashMap<>();

        condition.put("siteName", "Site10");
        condition.put("id", "0");
        System.out.println(condition.get("siteName"));

        System.out.println(brsMapper.retrieveResourceFromBrs("vCpe", condition));
    }

    @Test
    public void getDcLbBasedOnIpAddress() {
        brsMapper = new BrsMappingImpl();
        Map<String, String> condition = new HashMap<>();

        condition.put("ipAddress", "192.168.241.141");
        condition.put("id", "0");
        System.out.println(condition.get("ipAddress"));

        System.out.println(brsMapper.retrieveResourceFromBrs("dcLb", condition));
    }

    @Test
    public void getDcFwBasedOnIpAddress() {
        brsMapper = new BrsMappingImpl();
        Map<String, String> condition = new HashMap<>();

        condition.put("ipAddress", "192.168.243.143");
        condition.put("id", "0");
        System.out.println(condition.get("ipAddress"));

        System.out.println(brsMapper.retrieveResourceFromBrs("dcFw", condition));
    }

    @Test
    public void getSiteIdBasedOnSiteName() {
        brsMapper = new BrsMappingImpl();
        Map<String, String> condition = new HashMap<>();

        condition.put("name", "Site1");
        System.out.println(condition.get("name"));

        System.out.println(brsMapper.retrieveSiteNode(condition));
    }
}
