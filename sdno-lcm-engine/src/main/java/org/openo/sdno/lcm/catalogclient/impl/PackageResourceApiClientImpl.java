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

import java.util.List;
import java.util.logging.Logger;

import org.openo.sdno.lcm.catalogclient.PackageResourceApiClient;
import org.openo.sdno.lcm.exception.ExternalComponentException;
import org.openo.sdno.lcm.restclient.catalog.api.PackageResourceApi;
import org.openo.sdno.lcm.restclient.catalog.model.PackageMeta;
import org.openo.sdno.lcm.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author mark
 */
@Component
@PropertySource("classpath:config.properties")
public class PackageResourceApiClientImpl implements PackageResourceApiClient {

    private static final String FAILED_TO_RETRIEVE_PACKAGE_LIST_BY_CONDITION =
            "Failed to retrieve package list by condition";

    private static final String FAILED_TO_QUERY_PACKAGE_BY_ID = "Failed to query package by ID";

    private final Logger log = Logger.getLogger("PackageResourceApiClientImpl");

    @Autowired
    private Environment env;

    private PackageResourceApi getPackageResourceApi() {
        // It's recommended to create an instance of `ApiClient` per thread in a multithreaded
        // environment
        PackageResourceApi packageResourceApi = new PackageResourceApi();
        packageResourceApi.getApiClient()
                .setBasePath(env.getRequiredProperty(Constants.COMMON_TOSCA_CATALOG_BASE_PATH));
        return packageResourceApi;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.openo.sdno.catalogclient.PackageResourceApiClient#queryPackageById(
     * java.lang.String)
     */
    @Override
    public PackageMeta queryPackageById(String csarId) {

        PackageResourceApi packageResourceApi = this.getPackageResourceApi();
        try {
            return packageResourceApi.queryPackageById(csarId);

        } catch(Exception e) {
            log.severe(FAILED_TO_QUERY_PACKAGE_BY_ID);
            throw new ExternalComponentException(FAILED_TO_QUERY_PACKAGE_BY_ID, e);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.openo.sdno.catalogclient.PackageResourceApiClient#
     * queryPackageListByCond(java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public List<PackageMeta> queryPackageListByCond(String name, String provider, String version,
            String deletionPending, String type) {

        PackageResourceApi packageResourceApi = this.getPackageResourceApi();
        try {
            return packageResourceApi.queryPackageListByCond(name, provider, version, deletionPending, type);

        } catch(Exception e) {
            log.severe(String.format("%s; name:%s, provider:%s, version:%s, deletionPending:%s, type:%s",
                    FAILED_TO_RETRIEVE_PACKAGE_LIST_BY_CONDITION, name, provider, version, deletionPending, type));
            throw new ExternalComponentException(FAILED_TO_RETRIEVE_PACKAGE_LIST_BY_CONDITION, e);
        }
    }

    public void setEnv(Environment env) {
        this.env = env;
    }

}
