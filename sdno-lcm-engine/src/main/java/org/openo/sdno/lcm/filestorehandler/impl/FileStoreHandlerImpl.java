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

package org.openo.sdno.lcm.filestorehandler.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

import org.openo.sdno.lcm.exception.LcmInternalException;
import org.openo.sdno.lcm.filestorehandler.FileStoreHandler;
import org.openo.sdno.lcm.templateinstanceparser.TemplateInstanceParser;
import org.openo.sdno.lcm.templatemodel.service.Instance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Component to manage the storage of JSON instances of the template. This is a
 * temporary substitute for full storage in the service inventory.
 */
@Component
public class FileStoreHandlerImpl implements FileStoreHandler {

    private final Logger log = Logger.getLogger("FileStoreHandlerImpl");

    private TemplateInstanceParser templateInstanceParser;

    private File tempFileDir;

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.openo.sdno.lcm.filestorehandler.FileStoreHandler#storeInstanceToFile(
     * org.openo.sdno.lcm.templatemodel.service.Instance, java.lang.String)
     */
    @Override
    public void storeInstanceToFile(Instance instance, String fileId) {
        String instanceJson = instance.toJsonString();

        // File tmpFile;
        File tempFile = getTempFile(fileId);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile));) {
            bufferedWriter.write(instanceJson);
        } catch (IOException e) {
            log.severe("Failed to store Instance to file due to " + e.getMessage());
            throw new LcmInternalException(String.format("Failed to write to temp file %s", tempFile.getName()), e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.openo.sdno.lcm.filestorehandler.FileStoreHandler#getInstanceFromFile(
     * java.lang.String)
     */
    @Override
    public Instance getInstanceFromFile(String fileId) {

        File tempFile = getTempFile(fileId);
        if (!tempFile.exists()) {

            throw new LcmInternalException(String.format("Temp file %s not found", tempFile));
        }

        String line = null;
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(tempFile))) {

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (Exception e) {
            throw new LcmInternalException(String.format("getInstanceFromFile failed due to %s", e.getMessage(), e));
        }
        String serviceInstanceJson = stringBuilder.toString();

        Instance instance = templateInstanceParser.parse(serviceInstanceJson);
        return instance;
    }

    private File getTempFile(String fileId) {
        String fileName = fileId + ".json";
        File tempFile = new File(getTempFileDir(), fileName);
        return tempFile;
    }

    /**
     * Workaround to determine the directory used for the temporary file
     * storage.
     */
    private File getTempFileDir() {

        if (null == this.tempFileDir) {

            this.tempFileDir = new File(System.getProperty("user.home"));

        }
        return this.tempFileDir;
    }

    @Autowired
    public void setTemplateInstanceParser(TemplateInstanceParser templateInstanceParser) {
        this.templateInstanceParser = templateInstanceParser;
    }
}
