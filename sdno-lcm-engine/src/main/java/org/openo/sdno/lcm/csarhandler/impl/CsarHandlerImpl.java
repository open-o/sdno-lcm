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

package org.openo.sdno.lcm.csarhandler.impl;

import java.io.File;
import java.net.URL;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.openo.sdno.lcm.catalogclient.PackageResourceApiClient;
import org.openo.sdno.lcm.csarhandler.CsarHandler;
import org.openo.sdno.lcm.exception.ExternalComponentException;
import org.openo.sdno.lcm.exception.LcmInternalException;
import org.openo.sdno.lcm.restclient.catalog.model.PackageMeta;
import org.openo.sdno.lcm.templatemodel.csar.Csar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CsarHandlerImpl implements CsarHandler {

    private static final Logger log = Logger.getLogger("CsarHandlerImpl");

    private PackageResourceApiClient packageResourceApiClient;

    private File getCsarFile(String csarName) {

        // TODO no caching yet
        if(csarName == null || csarName.isEmpty()) {

            throw new InvalidParameterException("csarName may not be empty or null");
        }

        // TODO try this with resources!
        File tmpCsarFile = null;
        try {
            Csar csar = this.getCsarByName(csarName);
            String downloadUri = csar.getDownloadUri();

            tmpCsarFile = File.createTempFile(csarName, ".csar");
            FileUtils.copyURLToFile(new URL(downloadUri), tmpCsarFile);
            return tmpCsarFile;

        } catch(Exception e) {
            log.severe("Failed to download or store the temporary CSAR file");
            throw new LcmInternalException("Failed to download or store the temporary CSAR file", e);
        }
    }

    /* (non-Javadoc)
     * @see org.openo.sdno.lcm.csarhandler.CsarHandler#getCsarByName(java.lang.String)
     */
    @Override
    public Csar getCsarByName(String csarName) {

        // TODO no caching yet
        if(csarName == null || csarName.isEmpty()) {

            throw new InvalidParameterException("csarName may not be empty or null");
        }

        List<PackageMeta> packageMetaList =
                packageResourceApiClient.queryPackageListByCond(csarName, null, null, null, null);

        if(packageMetaList.isEmpty()) {
            log.severe("Empty package list returned from catalog");
            throw new ExternalComponentException("Catalog returned an empty list for the query of CSAR " + csarName);
        } else {

            if(packageMetaList.size() > 1) {
                log.warning(String.format("%s packages returned from catalog -expected one", packageMetaList.size()));
            }
            PackageMeta packageMeta = packageMetaList.get(0);
            return new Csar(packageMeta.getCsarId(), packageMeta.getName(), packageMeta.getDownloadUri());
        }
    }

    @Autowired
    public void setPackageResourceApiClient(PackageResourceApiClient packageResourceApiClient) {
        this.packageResourceApiClient = packageResourceApiClient;
    }

    /* (non-Javadoc)
     * @see org.openo.sdno.lcm.csarhandler.CsarHandler#getCsarId(java.lang.String)
     */
    @Override
    public String getCsarId(String csarName) {

        return this.getCsarByName(csarName).getCsarId();
    }
}
