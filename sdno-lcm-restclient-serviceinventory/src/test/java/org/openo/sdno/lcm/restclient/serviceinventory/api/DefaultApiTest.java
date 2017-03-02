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

package org.openo.sdno.lcm.restclient.serviceinventory.api;

import org.openo.sdno.lcm.restclient.serviceinventory.ApiException;
import org.openo.sdno.lcm.restclient.serviceinventory.model.CreateConnectivityServiceRequest;
import org.openo.sdno.lcm.restclient.serviceinventory.model.CreateConnectivityServiceResponse;
import org.openo.sdno.lcm.restclient.serviceinventory.model.GetAllConnectivityServiceResponse;
import org.openo.sdno.lcm.restclient.serviceinventory.model.GetConnectivityServiceResponse;
import org.openo.sdno.lcm.restclient.serviceinventory.model.UpdateResponse;
import org.openo.sdno.lcm.restclient.serviceinventory.model.UpdateConnectivityServiceRequest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for DefaultApi
 */
public class DefaultApiTest {

    private final DefaultApi api = new DefaultApi();

    
    /**
     * 
     *
     * Add a new connectivity services
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void createConnectivityServiceTest() throws ApiException {
        CreateConnectivityServiceRequest body = null;
        // CreateConnectivityServiceResponse response = api.createConnectivityService(body);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * Delete the connectivity service specified in request
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteConnectivityServiceTest() throws ApiException {
        String id = null;
        // api.deleteConnectivityService(id);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * get connectivity services
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getConnectivityServiceTest() throws ApiException {
        // GetAllConnectivityServiceResponse response = api.getConnectivityService();

        // TODO: test validations
    }
    
    /**
     * 
     *
     * Get the connectivity service specified in request
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void readConnectivityServiceTest() throws ApiException {
        String id = null;
        // GetConnectivityServiceResponse response = api.readConnectivityService(id);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * modify the connectivity service specified in request
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void updateConnectivityServiceRequestTest() throws ApiException {
        String id = null;
        UpdateConnectivityServiceRequest body = null;
        // UpdateResponse response = api.updateConnectivityServiceRequest(id, body);

        // TODO: test validations
    }
    
}
