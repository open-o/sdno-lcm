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


package org.openo.sdno.lcm.serviceinventoryclient;

import org.openo.sdno.lcm.restclient.serviceinventory.model.ConnectivityService;
import org.openo.sdno.lcm.restclient.serviceinventory.model.ResponseConnectivityService;
import org.openo.sdno.lcm.restclient.serviceinventory.model.UpdateConnectivityService;
import org.openo.sdno.lcm.restclient.serviceinventory.model.UpdateResponseConnectivityService;

/**
 * @author mark
 */
public interface DefaultMssApiClient {

    /**
     * Add a new connectivity services
     * 
     * @param body the createConnectivityService to be created (required)
     * @return ResponseConnectivityService
     * @throws ApiException if fails to make API call
     */
    ResponseConnectivityService createConnectivityService(ConnectivityService body);

    /**
     * Delete the connectivity services
     * 
     * @param id ID of the connectivity service to be deleted (required)
     * @throws ApiException if fails to make API call
     */
    void deleteConnectivityService(String id);

    /**
     * Add a new connectivity services
     * 
     * @return ResponseConnectivityService
     * @throws ApiException if fails to make API call
     */
    ResponseConnectivityService getConnectivityService();

    /**
     * Read the connectivity services
     * 
     * @param id ID of the connectivity service to be deleted (required)
     * @return ResponseConnectivityService
     * @throws ApiException if fails to make API call
     */
    ResponseConnectivityService readConnectivityService(String id);

    /**
     * modify the value.
     * 
     * @param id ID of the connectivity service to be deleted (required)
     * @param body the partial connectivity service for the update operation (required)
     * @return UpdateResponseConnectivityService
     * @throws ApiException if fails to make API call
     */
    UpdateResponseConnectivityService updateConnectivityService(String id, UpdateConnectivityService body);
}
