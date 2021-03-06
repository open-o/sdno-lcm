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

import java.util.Map;
import java.util.logging.Logger;

import org.openo.sdno.lcm.ariaclient.ParserApiClient;
import org.openo.sdno.lcm.exception.ExternalComponentException;
import org.openo.sdno.lcm.restclient.aria.api.ParserApi;
import org.openo.sdno.lcm.restclient.aria.model.IndirectData;
import org.openo.sdno.lcm.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author mark
 */
@Component
public class ParserApiClientImpl implements ParserApiClient {

    private static final String FAILED_TO_PARSE_INSTANCE_INDIRECT = "Failed to parse instance (indirect)";

    private final Logger log = Logger.getLogger("ParserApiClientImpl");

    @Autowired
    private Environment env;

    private ParserApi getParserApi() {
        // It's recommended to create an instance of `ApiClient` per thread in a multithreaded
        // environment
        ParserApi parserApi = new ParserApi();
        parserApi.getApiClient().setBasePath(env.getRequiredProperty(Constants.COMMON_TOSCA_ARIA_BASE_PATH));
        return parserApi;
    }

    /*
     * (non-Javadoc)
     * @see org.openo.sdno.lcm.ariaclient.ParserApiClient#
     * parseControllerInstanceUpload(java.lang.Object, java.lang.String)
     */
    @Override
    public Map<String, Object> parseControllerInstanceIndirect(String uri, Map<String, String> inputs) {

        IndirectData indirectData = new IndirectData();
        indirectData.setUri(uri);
        indirectData.setInputs(inputs);
        ParserApi parserApi = this.getParserApi();
        try {
            return (Map<String, Object>)parserApi.parseControllerInstanceIndirect(indirectData);
        } catch(Exception e) {
            log.severe(FAILED_TO_PARSE_INSTANCE_INDIRECT);
            throw new ExternalComponentException(FAILED_TO_PARSE_INSTANCE_INDIRECT, e);
        }
    }

    public void setEnv(Environment env) {
        this.env = env;
    }

}
