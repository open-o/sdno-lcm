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
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openo.sdno.lcm.ariaclient.ParserApiClient;
import org.openo.sdno.lcm.catalogclient.ModelResourceApiClient;
import org.openo.sdno.lcm.catalogclient.PackageResourceApiClient;
import org.openo.sdno.lcm.restclient.catalog.model.PackageMeta;
import org.openo.sdno.lcm.restclient.catalog.model.ServiceTemplate;
import org.openo.sdno.lcm.statetablehandler.StateTableHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LcmStateEngine {

    private static final Logger log = Logger.getLogger("LcmStateEngine");

    private StateTableHandler stateTableHandler;

    private ModelResourceApiClient modelResourceApiClient;

    private PackageResourceApiClient packageResourceApiClient;

    private ParserApiClient parserApiClient;

    public Map<String, Object> execute(Map<String, String> params) {

        if(params == null) {

            throw new InvalidParameterException("input params may not be null");
        }
        log.fine("params:" + params.toString());

        // check if the nsid is included in params
        String nsid = params.get("nsid");
        if(null != params.get("nsid")) {

            log.info("nsid is " + nsid);
        } else {
            log.info("nsid is not found in params");
            if(null != params.get("nsName")) {

                String csarName = params.get("nsdId"); // ????????????????????
                log.info("csarId is " + csarName);
                return executeCreate(csarName);
            } else {
                log.info("nsdId is not found in params");
            }
        }
        return null;

    }

    Map<String, Object> executeCreate(String csarName) {

        if(csarName == null || csarName.isEmpty()) {

            throw new InvalidParameterException("csarName may not be empty or null");
        }
        try {
            // get TOSCA template
            // PackageMeta packageMetaL = packageResourceApiClient.queryPackageById(csarId);
            List<PackageMeta> packageMetaList =
                    packageResourceApiClient.queryPackageListByCond(csarName, null, null, null, null);

            if(packageMetaList.isEmpty())
                log.severe("Empty package list returned from catalog");
            if(packageMetaList.size() > 1)
                log.warning(String.format("%s packages returned from catalog -expected one", packageMetaList.size()));
            for(PackageMeta pm : packageMetaList) {
                log.finer(pm.toString());
            }
            if(!packageMetaList.isEmpty()) {

                PackageMeta packageMeta = packageMetaList.get(0);
                log.info(packageMeta.toString());
                String downloadUri = packageMeta.getDownloadUri();
                FileUtils.copyURLToFile(new URL(downloadUri), File.createTempFile(csarName, ".csar"));
                log.info("Now we can do something with the CSAR...");
                String csarId = packageMeta.getCsarId();

                // get raw template instance from catalog
                String serviceTemplate = modelResourceApiClient.getServiceTemplateRawData(csarId);

                ////////////////// need to check if the instance coming back in rawdata is the full
                ////////////////// instance, or if we need parser
                // log.finer("serviceTemplate: " + serviceTemplate);
                //
                // String templateUri = serviceTemplate.getDownloadUri();
                // // parse the template
                // Object pathParsedObject =
                ////////////////// parserApiClient.parseControllerInstanceFile(templateUri, "");
                // log.finer("path parsed: " + pathParsedObject.toString());
                //
                // String templateStr = null;
                // try {
                // templateStr = IOUtils.toString(new URL(templateUri), StandardCharsets.UTF_8);
                // } catch(IOException e) {
                // // TODO Auto-generated catch block
                // e.printStackTrace();
                // }
                Object obj = parserApiClient.parseControllerInstanceUpload(serviceTemplate, "");
                log.finer("downloaded: " + obj.toString());
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        // get service instance from inventory
        // get state from instance - except in create case we know already

        // List<PackageMeta> queryPackageListByCond =
        // packageResourceApiClient.queryPackageListByCond(null, null, null,
        // null,
        // null);
        return null;
    }

    // public StateTableHandler getStateTableHandler() {
    // return stateTableHandler;
    // }

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

}
