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

package org.openo.sdno.lcm.webapp.api;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openo.sdno.lcm.engine.LcmStateEngine;
import org.openo.sdno.lcm.webapp.Swagger2SpringBoot;
import org.openo.sdno.lcm.webapp.model.NsCreationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Swagger2SpringBoot.class)
@WebIntegrationTest
public class NsApiControllerTest {

    private static final String SDNO_LCM_API_NS = "http://127.0.0.1:8080/openoapi/sdnonslcm/v1/ns/";
    @Autowired
    private LcmStateEngine lcmStateEngine;

    /**
     * Test that an NS create not containing nsdId returns http 400
     */
    @Test
    public void testBadRequest() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>("{\"banana\":\"123\"}", headers);

        RestTemplate template = new TestRestTemplate();
        ResponseEntity<NsCreationResponse> forEntity = template
                .postForEntity(SDNO_LCM_API_NS, entity, NsCreationResponse.class);
        Assert.assertEquals(forEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Test that an NS create fails with 404 when a required microservice cannot be reached
     */
    @Test
    public void testMissingDependency() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>("{\"nsdId\":\"123\"}", headers);

        RestTemplate template = new TestRestTemplate();
        ResponseEntity<NsCreationResponse> forEntity = template
                .postForEntity(SDNO_LCM_API_NS, entity, NsCreationResponse.class);
        Assert.assertEquals(forEntity.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    /**
     * Test that an internal error results in http 500
     */
    @Test
    public void testInternalError() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>("{\"nsdId\":\"123\"}", headers);

        // set the package client to null so we generate an exception trying to get the CSAR
        lcmStateEngine.setPackageResourceApiClient(null);

        RestTemplate template = new TestRestTemplate();
        ResponseEntity<NsCreationResponse> forEntity = template
                .postForEntity(SDNO_LCM_API_NS, entity, NsCreationResponse.class);
        Assert.assertEquals(forEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
