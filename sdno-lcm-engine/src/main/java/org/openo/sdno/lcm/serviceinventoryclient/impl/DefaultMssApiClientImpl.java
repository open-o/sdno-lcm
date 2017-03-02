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

package org.openo.sdno.lcm.serviceinventoryclient.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.openo.sdno.lcm.exception.ExternalComponentException;
import org.openo.sdno.lcm.restclient.serviceinventory.api.DefaultApi;
import org.openo.sdno.lcm.restclient.serviceinventory.model.ConnectivityService;
import org.openo.sdno.lcm.restclient.serviceinventory.model.CreateConnectivityServiceRequest;
import org.openo.sdno.lcm.restclient.serviceinventory.model.CreateConnectivityServiceResponse;
import org.openo.sdno.lcm.restclient.serviceinventory.model.GetAllConnectivityServiceResponse;
import org.openo.sdno.lcm.restclient.serviceinventory.model.GetConnectivityServiceResponse;
import org.openo.sdno.lcm.restclient.serviceinventory.model.UpdateConnectivityServiceRequest;
import org.openo.sdno.lcm.restclient.serviceinventory.model.UpdateResponse;
import org.openo.sdno.lcm.serviceinventoryclient.DefaultMssApiClient;
import org.openo.sdno.lcm.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author mark
 */
@Component
public class DefaultMssApiClientImpl implements DefaultMssApiClient {

    private static final String FAILED_TO_GET_CONNECTIVITY_SERVICES = "Failed to get connectivity services";

    private static final String FAILED_TO_DELETE_CONNECTIVITY_SERVICE = "Failed to delete connectivity service";

    private static final String FAILED_TO_CREATE_CONNECTIVITY_SERVICE = "Failed to create connectivity service";

    private static final String FAILED_TO_READ_CONNECTIVITY_SERVICES = "Failed to read connectivity service";

    private static final String FAILED_TO_UPDATE_CONNECTIVITY_SERVICES = "Failed to update connectivity service";

    private final Logger log = Logger.getLogger("DefaultApiClientImpl");

    @Autowired
    private Environment env;

    private DefaultApi getDefaultApi() {
        // It's recommended to create an instance of `ApiClient` per thread in a multithreaded
        // environment
        DefaultApi defaultApi = new DefaultApi();
        defaultApi.getApiClient().setBasePath(env.getRequiredProperty(Constants.SDNO_BRS_SERVICEINVENTORY_BASE_PATH));
        return defaultApi;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.openo.sdno.lcm.serviceinventoryclient.DefaultMssApiClient#createConnectivityService(org.
     * openo.sdno.lcm.restclient.serviceinventory.model.CreateConnectivityServiceRequest)
     */
    @Override
    public CreateConnectivityServiceResponse createConnectivityService(ConnectivityService connectivityService) {

        // TODO change parameter type to generic?
        DefaultApi defaultApi = this.getDefaultApi();
        try {
            CreateConnectivityServiceRequest body = new CreateConnectivityServiceRequest();
            List<ConnectivityService> objects = new ArrayList<ConnectivityService>();
            objects.add(connectivityService);
            body.setObjects(objects);
            return defaultApi.createConnectivityService(body);
        } catch(Exception e) {
            log.severe(FAILED_TO_CREATE_CONNECTIVITY_SERVICE);
            throw new ExternalComponentException(FAILED_TO_CREATE_CONNECTIVITY_SERVICE, e);
        }
    }

    /*
     * (non-Javadoc)
     * @see
     * org.openo.sdno.lcm.serviceinventoryclient.DefaultMssApiClient#deleteConnectivityService(java.
     * lang.String)
     */
    @Override
    public void deleteConnectivityService(String id) {

        DefaultApi defaultApi = this.getDefaultApi();
        try {
            defaultApi.deleteConnectivityService(id);
        } catch(Exception e) {
            log.severe(FAILED_TO_DELETE_CONNECTIVITY_SERVICE);
            throw new ExternalComponentException(FAILED_TO_DELETE_CONNECTIVITY_SERVICE, e);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.openo.sdno.lcm.serviceinventoryclient.DefaultMssApiClient#getConnectivityService()
     */
    @Override
    public GetAllConnectivityServiceResponse getConnectivityService() {

        DefaultApi defaultApi = this.getDefaultApi();
        try {
            return defaultApi.getConnectivityService();
        } catch(Exception e) {
            log.severe(FAILED_TO_GET_CONNECTIVITY_SERVICES);
            throw new ExternalComponentException(FAILED_TO_GET_CONNECTIVITY_SERVICES, e);
        }
    }

    /*
     * (non-Javadoc)
     * @see
     * org.openo.sdno.lcm.serviceinventoryclient.DefaultMssApiClient#readConnectivityService(java.
     * lang.String)
     */
    @Override
    public GetConnectivityServiceResponse readConnectivityService(String id) {

        DefaultApi defaultApi = this.getDefaultApi();
        try {
            return defaultApi.readConnectivityService(id);
        } catch(Exception e) {
            log.severe(FAILED_TO_READ_CONNECTIVITY_SERVICES);
            throw new ExternalComponentException(FAILED_TO_READ_CONNECTIVITY_SERVICES, e);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.openo.sdno.lcm.serviceinventoryclient.DefaultMssApiClient#
     * updateConnectivityServiceRequest(java.lang.String,
     * org.openo.sdno.lcm.restclient.serviceinventory.model.UpdateConnectivityServiceRequest)
     */
    @Override
    public UpdateResponse updateConnectivityServiceRequest(String id, UpdateConnectivityServiceRequest body) {
        DefaultApi defaultApi = this.getDefaultApi();
        try {
            return defaultApi.updateConnectivityServiceRequest(id, body);
        } catch(Exception e) {
            log.severe(FAILED_TO_UPDATE_CONNECTIVITY_SERVICES);
            throw new ExternalComponentException(FAILED_TO_UPDATE_CONNECTIVITY_SERVICES, e);
        }
    }

    public void setEnv(Environment env) {
        this.env = env;
    }

}
