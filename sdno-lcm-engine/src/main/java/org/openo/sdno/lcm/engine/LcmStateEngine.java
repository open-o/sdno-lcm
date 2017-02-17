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

package org.openo.sdno.lcm.engine;

import java.io.File;
import java.net.URL;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.openo.sdno.lcm.ariaclient.ParserApiClient;
import org.openo.sdno.lcm.catalogclient.ModelResourceApiClient;
import org.openo.sdno.lcm.catalogclient.PackageResourceApiClient;
import org.openo.sdno.lcm.exception.ExternalComponentException;
import org.openo.sdno.lcm.exception.LcmInternalException;
import org.openo.sdno.lcm.restclient.catalog.model.PackageMeta;
import org.openo.sdno.lcm.serviceinventoryclient.DefaultMssApiClient;
import org.openo.sdno.lcm.statetablehandler.StateTableHandler;
import org.openo.sdno.lcm.templateinstanceparser.TemplateInstanceParser;
import org.openo.sdno.lcm.templatemodel.service.Instance;
import org.openo.sdno.lcm.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LcmStateEngine {

    private static final Logger log = Logger.getLogger("LcmStateEngine");

    private StateTableHandler stateTableHandler;

    private ModelResourceApiClient modelResourceApiClient;

    private PackageResourceApiClient packageResourceApiClient;

    private ParserApiClient parserApiClient;

    private DefaultMssApiClient defaultMssApiClient;
    
    private TemplateInstanceParser templateInstanceParser; 

    public Map<String, Object> execute(Map<String, String> params) {

        if(params == null) {

            throw new IllegalArgumentException("input params may not be null");
        }
        log.fine("params:" + params.toString());

        // check if the nsid is included in params
        if(params.containsKey(Constants.LCM_NBI_SERVICE_ID) && !params.get(Constants.LCM_NBI_SERVICE_ID).isEmpty()) {

            String nsid = params.get(Constants.LCM_NBI_SERVICE_ID);
            log.info(Constants.LCM_NBI_SERVICE_ID + " is " + nsid);

            // TODO execute the appropriate workflow...
            // TODO get the service from the inventory
            // stateTableHandler.validateServiceTransition(null, null, null);
            return null;
        } else {
            log.info(Constants.LCM_NBI_SERVICE_ID + " is not found in params");

            // no connectivity service ID, so this must be a create
            if(params.containsKey(Constants.LCM_NBI_CSAR_NAME) && !params.get(Constants.LCM_NBI_CSAR_NAME).isEmpty()) {

                String csarName = params.get(Constants.LCM_NBI_CSAR_NAME);
                log.info(Constants.LCM_NBI_CSAR_NAME + " is " + csarName);
                return executeCreate(csarName);
            } else {
                log.info(Constants.LCM_NBI_CSAR_NAME + " is not found in params");
                throw new LcmInternalException("No workflows possible with the parameters given");
            }
        }
    }

    Map<String, Object> executeCreate(String csarName) {

        if(csarName == null || csarName.isEmpty()) {

            throw new InvalidParameterException("csarName may not be empty or null");
        }

        List<PackageMeta> packageMetaList =
                packageResourceApiClient.queryPackageListByCond(csarName, null, null, null, null);

        if(packageMetaList.isEmpty()) {
            log.severe("Empty package list returned from catalog");
            throw new ExternalComponentException("Catalog returned an empty list for the query of CSAR " + csarName);
        }
        if(packageMetaList.size() > 1)
            log.warning(String.format("%s packages returned from catalog -expected one", packageMetaList.size()));
        for(PackageMeta pm : packageMetaList) {
            log.finer(pm.toString());
        }
        if(!packageMetaList.isEmpty()) {

            PackageMeta packageMeta = packageMetaList.get(0);
            log.info(packageMeta.toString());
            String downloadUri = packageMeta.getDownloadUri();
            try {
                FileUtils.copyURLToFile(new URL(downloadUri), File.createTempFile(csarName, ".csar"));
            } catch(Exception e) {
                log.severe("Failed to download or store the temporary CSAR file");
                throw new LcmInternalException("Failed to download or store the temporary CSAR file", e);
            }
            log.info("Now we can do something with the CSAR...");
            String csarId = packageMeta.getCsarId();

            // get raw template instance from catalog
            String serviceTemplate = modelResourceApiClient.getServiceTemplateRawData(csarId);
            Instance templateInstance = templateInstanceParser.parse(serviceTemplate);
        }

        // TODO get service instance from inventory
        // TODO get state from instance - except in create case we know already

        return new HashMap<String, Object>();
    }

    @Autowired
    public void setStateTableHandler(StateTableHandler stateTableHandler) {
        this.stateTableHandler = stateTableHandler;
    }

    @Autowired
    public void setModelResourceApiClient(ModelResourceApiClient modelResourceApiClient) {
        this.modelResourceApiClient = modelResourceApiClient;
    }

    @Autowired
    public void setPackageResourceApiClient(PackageResourceApiClient packageResourceApiClient) {
        this.packageResourceApiClient = packageResourceApiClient;
    }

    @Autowired
    public void setParserApiClient(ParserApiClient parserApiClient) {
        this.parserApiClient = parserApiClient;
    }

    @Autowired
    public void setDefaultMssApiClient(DefaultMssApiClient defaultMssApiClient) {
        this.defaultMssApiClient = defaultMssApiClient;
    }

    @Autowired    
    public void setTemplateInstanceParser(TemplateInstanceParser templateInstanceParser) {
        this.templateInstanceParser = templateInstanceParser;
    }

}
