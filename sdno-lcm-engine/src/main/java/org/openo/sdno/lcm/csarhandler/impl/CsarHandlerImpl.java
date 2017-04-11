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
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.InvalidParameterException;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.io.IOUtils;
import org.openo.sdno.lcm.catalogclient.PackageResourceApiClient;
import org.openo.sdno.lcm.csarhandler.CsarHandler;
import org.openo.sdno.lcm.exception.ExternalComponentException;
import org.openo.sdno.lcm.exception.LcmInternalException;
import org.openo.sdno.lcm.restclient.catalog.model.PackageMeta;
import org.openo.sdno.lcm.templatemodel.csar.Csar;
import org.openo.sdno.lcm.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;

import io.swagger.models.Swagger;
import io.swagger.util.Yaml;

@Component
public class CsarHandlerImpl implements CsarHandler {

    static final String CSAR2 = ".csar";

    private static final Logger log = Logger.getLogger("CsarHandlerImpl");

    private PackageResourceApiClient packageResourceApiClient;

    private FileHandler fileHandler;

    private Mapper mapper;

    /**
     * Get the ZipFile handle to the actual CSAR correspoding to the CSAR meta
     * data
     * 
     * @param csar
     *            the CSAR meta data
     * @return the ZipFile CSAR
     */
    private ZipFile getCsarZipFile(Csar csar) {

        File tmpCsarFile = null;
        try {
            String downloadUri = csar.getDownloadUri();

            tmpCsarFile = fileHandler.getFile(csar.getCsarId(), CSAR2, downloadUri);
            ZipFile zippy = new ZipFile(tmpCsarFile);
            return zippy;

        } catch (Exception e) {
            log.severe("Failed to download or store the temporary CSAR file");
            throw new LcmInternalException("Failed to download or store the temporary CSAR file", e);
        }
    }

    /**
     * Get the CSAR meta from the catalog based on csar ID.
     * 
     * @param csarId
     * @return
     */
    private Csar getCsarById(String csarId) {

        if (csarId == null || csarId.isEmpty()) {

            throw new InvalidParameterException("csarId may not be empty or null");
        }
        PackageMeta packageMeta = packageResourceApiClient.queryPackageById(csarId);

        if (null == packageMeta) {
            log.severe("Null packageMeta returned from catalog");
            throw new ExternalComponentException("Catalog returned null for the query of CSAR " + csarId);
        }
        return new Csar(packageMeta.getCsarId(), packageMeta.getName(), packageMeta.getDownloadUri());
    }

    @Override
    public Swagger getSwaggerSpec(String csarId, String swaggerPath) {

        Csar csar = this.getCsarById(csarId);
        ZipFile zippy = this.getCsarZipFile(csar);
        ZipEntry entry = zippy.getEntry(swaggerPath);
        try (InputStream inStream = zippy.getInputStream(entry)) {

            String entryString = IOUtils.toString(inStream, Charset.defaultCharset());
            Swagger swagger = Yaml.mapper().readValue(entryString, Swagger.class);
            return swagger;

        } catch (Exception ex) {
            throw new LcmInternalException("Failed to get the swagger spec for " + swaggerPath, ex);
        }
    }

    @Override
    public JsonNode getMapperSpec(String csarId, String mapperPath) {
        Csar csar = this.getCsarById(csarId);
        ZipFile zippy = this.getCsarZipFile(csar);
        ZipEntry entry = zippy.getEntry(mapperPath);
        try (InputStream inStream = zippy.getInputStream(entry)) {

            String entryString = IOUtils.toString(inStream, Charset.defaultCharset());
            return mapper.stringToNode(entryString);

        } catch (Exception ex) {
            throw new LcmInternalException("Failed to get the mapper spec for " + mapperPath, ex);
        }
    }

    @Autowired
    public void setPackageResourceApiClient(PackageResourceApiClient packageResourceApiClient) {
        this.packageResourceApiClient = packageResourceApiClient;
    }

    @Autowired
    public void setFileHandler(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    @Autowired
    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }
}
