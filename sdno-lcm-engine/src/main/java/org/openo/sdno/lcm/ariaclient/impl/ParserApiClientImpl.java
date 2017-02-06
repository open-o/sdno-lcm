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

package org.openo.sdno.lcm.ariaclient.impl;

import java.util.logging.Logger;

import org.openo.sdno.lcm.ariaclient.ParserApiClient;
import org.openo.sdno.lcm.restclient.aria.ApiException;
import org.openo.sdno.lcm.restclient.aria.api.ParserApi;
import org.openo.sdno.lcm.restclient.aria.model.IndirectData;

/**
 * @author mark
 */
public class ParserApiClientImpl implements ParserApiClient {

    private static final String FAILED_TO_PARSE_CONTROLLER_INSTANCE_FILE = "Failed to parse controller instance file";

    private static final String FAILED_TO_PARSE_CONTROLLER_INSTANCE_UPLOAD =
            "Failed to parse controller instance upload";

    private final Logger log = Logger.getLogger("ParserApiClientImpl");

    /*
     * (non-Javadoc)
     * @see
     * org.openo.sdno.lcm.ariaclient.ParserApiClient#parseControllerInstanceFile
     * (java.lang.String, java.lang.String)
     */
    @Override
    public Object parseControllerInstanceFile(String path, String inputs) {

        // It's recommended to create an instance of `ApiClient` per thread in a
        // multithreaded environment
        ParserApi parserApi = new ParserApi();
        try {
            return parserApi.parseControllerInstanceFile(path, inputs);
        } catch(ApiException e) {
            log.severe(FAILED_TO_PARSE_CONTROLLER_INSTANCE_FILE);
            throw new RuntimeException(FAILED_TO_PARSE_CONTROLLER_INSTANCE_FILE);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.openo.sdno.lcm.ariaclient.ParserApiClient#
     * parseControllerInstanceUpload(java.lang.Object, java.lang.String)
     */
    @Override
    public Object parseControllerInstanceUpload(Object uploadContent, String inputs) {

        // It's recommended to create an instance of `ApiClient` per thread in a
        // multithreaded environment
        ParserApi parserApi = new ParserApi();
        try {
            return parserApi.parseControllerInstanceUpload(uploadContent, inputs);
        } catch(ApiException e) {
            log.severe(FAILED_TO_PARSE_CONTROLLER_INSTANCE_UPLOAD);
            throw new RuntimeException(FAILED_TO_PARSE_CONTROLLER_INSTANCE_UPLOAD);
        }
    }

    /*
     * (non-Javadoc)
     * @see
     * org.openo.sdno.lcm.ariaclient.ParserApiClient#parseControllerModelFile(
     * java.lang.String)
     */
    @Override
    public Object parseControllerModelFile(String path) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.openo.sdno.lcm.ariaclient.ParserApiClient#parseControllerModelUpload(
     * java.lang.Object, java.lang.String)
     */
    @Override
    public Object parseControllerModelUpload(Object uploadContent, String inputs) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.openo.sdno.lcm.ariaclient.ParserApiClient#parseControllerValidateFile
     * (java.lang.String)
     */
    @Override
    public Object parseControllerValidateFile(String path) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.openo.sdno.lcm.ariaclient.ParserApiClient#
     * parseControllerValidateUpload(java.lang.Object, java.lang.String)
     */
    @Override
    public Object parseControllerValidateUpload(Object uploadContent, String inputs) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.openo.sdno.lcm.ariaclient.ParserApiClient#
     * parseControllerValidateUpload(java.lang.Object, java.lang.String)
     */
    @Override
    public Object parseControllerValidateIndirect(IndirectData data) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.openo.sdno.lcm.ariaclient.ParserApiClient#
     * parseControllerValidateUpload(java.lang.Object, java.lang.String)
     */
    @Override
    public Object parseControllerModelIndirect(IndirectData indirectData) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.openo.sdno.lcm.ariaclient.ParserApiClient#
     * parseControllerValidateUpload(java.lang.Object, java.lang.String)
     */
    @Override
    public Object parseControllerInstanceIndirect(IndirectData indirectData) {
        // TODO Auto-generated method stub
        return null;
    }
}
