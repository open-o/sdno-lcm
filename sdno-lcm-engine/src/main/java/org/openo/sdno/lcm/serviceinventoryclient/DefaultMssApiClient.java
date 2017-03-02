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

import org.openo.sdno.lcm.restclient.serviceinventory.ApiException;
import org.openo.sdno.lcm.restclient.serviceinventory.model.ConnectivityService;
import org.openo.sdno.lcm.restclient.serviceinventory.model.CreateConnectivityServiceResponse;
import org.openo.sdno.lcm.restclient.serviceinventory.model.GetAllConnectivityServiceResponse;
import org.openo.sdno.lcm.restclient.serviceinventory.model.GetConnectivityServiceResponse;
import org.openo.sdno.lcm.restclient.serviceinventory.model.UpdateConnectivityServiceRequest;
import org.openo.sdno.lcm.restclient.serviceinventory.model.UpdateResponse;

/**
 * @author mark
 */
public interface DefaultMssApiClient {

    /**
     * Add a new connectivity services
     * 
     * @param body the createConnectivityService to be created (required)
     * @return CreateConnectivityServiceResponse
     * @throws ApiException if fails to make API call
     */
    public CreateConnectivityServiceResponse createConnectivityService(ConnectivityService connectivityService);

    /**
     * Delete the connectivity service specified in request
     * 
     * @param id ID of the connectivity service to be deleted (required)
     * @throws ApiException if fails to make API call
     */
    void deleteConnectivityService(String id);

    /**
     * get connectivity services
     * 
     * @return GetAllConnectivityServiceResponse
     * @throws ApiException if fails to make API call
     */
    public GetAllConnectivityServiceResponse getConnectivityService();

    /**
     * Get the connectivity service specified in request
     * 
     * @param id ID of the connectivity service to be queried (required)
     * @return GetConnectivityServiceResponse
     * @throws ApiException if fails to make API call
     */
    public GetConnectivityServiceResponse readConnectivityService(String id);

    /**
     * modify the connectivity service specified in request
     * 
     * @param id ID of the connectivity service to be updated (required)
     * @param body the partial connectivity service for the update operation (required)
     * @return UpdateResponse
     * @throws ApiException if fails to make API call
     */
    public UpdateResponse updateConnectivityServiceRequest(String id, UpdateConnectivityServiceRequest body);
}
