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

import java.io.IOException;
import java.util.logging.Logger;

import org.openo.sdno.lcm.catalogclient.ModelResourceApiClient;
import org.openo.sdno.lcm.exception.ExternalComponentException;
import org.openo.sdno.lcm.restclient.catalog.api.ModelResourceApi;
import org.openo.sdno.lcm.restclient.catalog.model.Parameters;
import org.openo.sdno.lcm.restclient.catalog.model.QueryRawDataCondition;
import org.openo.sdno.lcm.restclient.catalog.model.ServiceTemplate;
import org.openo.sdno.lcm.restclient.catalog.model.ServiceTemplateRawData;
import org.openo.sdno.lcm.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author mark
 */
@Component
@PropertySource("classpath:config.properties")
public class ModelResourceApiClientImpl implements ModelResourceApiClient {

    private static final String FAILED_TO_GET_SERVICE_TEMPLATE_PARAMETERS = "Failed to get service template parameters";

    private static final String FAILED_TO_QUERY_SERVICE_TEMPLATE_BY_RAW_DATA =
            "Failed to query service template by raw data";

    private static final String FAILED_TO_QUERY_SERVICE_TEMPLATE_BY_ID = "Failed to query service template by ID";

    private final Logger log = Logger.getLogger("ModelResourceApiClientImpl");

    @Autowired
    private Environment env;

    private ModelResourceApi getModelResourceApi() {
        // It's recommended to create an instance of `ApiClient` per thread in a multithreaded
        // environment
        ModelResourceApi modelResourceApi = new ModelResourceApi();
        modelResourceApi.getApiClient().setBasePath(env.getRequiredProperty(Constants.COMMON_TOSCA_CATALOG_BASE_PATH));
        return modelResourceApi;
    }

    /*
     * (non-Javadoc)
     * @see org.openo.sdno.catalogclient.ModelResourceApiClient#
     * getServiceTemplateById(java.lang.String)
     */
    @Override
    public ServiceTemplate getServiceTemplateById(String serviceTemplateId) {

        ModelResourceApi modelResourceApi = getModelResourceApi();
        try {
            return modelResourceApi.getServiceTemplateById(serviceTemplateId);

        } catch(Exception e) {
            log.severe(FAILED_TO_QUERY_SERVICE_TEMPLATE_BY_ID);
            throw new ExternalComponentException(FAILED_TO_QUERY_SERVICE_TEMPLATE_BY_ID, e);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.openo.sdno.catalogclient.ModelResourceApiClient#
     * getServiceTemplateParameters(java.lang.String)
     */
    @Override
    public Parameters getServiceTemplateParameters(String servicetemplateid) {

        ModelResourceApi modelResourceApi = getModelResourceApi();
        try {
            return modelResourceApi.getServiceTemplateParameters(servicetemplateid);

        } catch(Exception e) {
            log.severe(FAILED_TO_GET_SERVICE_TEMPLATE_PARAMETERS);
            throw new ExternalComponentException(FAILED_TO_GET_SERVICE_TEMPLATE_PARAMETERS, e);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.openo.sdno.catalogclient.ModelResourceApiClient#
     * getServiceTemplateRawData(org.openo.sdno.lcm.restclient.catalog.model.
     * QueryRawDataCondition)
     */
    @Override
    public String getServiceTemplateRawData(String csarId) {

        QueryRawDataCondition body = new QueryRawDataCondition();
        body.setCsarId(csarId);

        ModelResourceApi modelResourceApi = getModelResourceApi();
        try {
            ServiceTemplateRawData serviceTemplateRawData = modelResourceApi.getServiceTemplateRawData(body);

            String rawData = serviceTemplateRawData.getRawData();
            log.fine("raw data: " + this.unprettyPrint(rawData));
            return rawData;

        } catch(Exception e) {
            log.severe(FAILED_TO_QUERY_SERVICE_TEMPLATE_BY_RAW_DATA + body.toString());
            throw new ExternalComponentException(FAILED_TO_QUERY_SERVICE_TEMPLATE_BY_RAW_DATA, e);
        }
    }

    private String unprettyPrint(String prettyJson) throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readValue(prettyJson, JsonNode.class);
        return jsonNode.toString();
    }

    public void setEnv(Environment env) {
        this.env = env;
    }

}
