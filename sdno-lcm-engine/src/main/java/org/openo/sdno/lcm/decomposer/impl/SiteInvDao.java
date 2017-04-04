/*
 * Copyright 2016-2017 Huawei Technologies Co., Ltd.
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

package org.openo.sdno.lcm.decomposer.impl;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.baseservice.roa.util.restclient.RestfulResponse;
import org.openo.sdno.lcm.exception.LcmInternalException;
import org.openo.sdno.lcm.util.Constants;
import org.openo.sdno.lcm.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

@Component
public class SiteInvDao {

    private final Logger log = Logger.getLogger("SiteInvDao");

    private static final String SITEURI = "/openoapi/sdnobrs/v1/sites";

    private static final String SITES_KEY = "sites";

    private static final String SITE_KEY = "site";

    @Autowired
    private Mapper mapper;

    /**
     * Query site model object info with input condition.Filter properties:
     * name, type, tenantID.<br>
     * 
     * @param condition
     *            condition
     * @return site model object info
     * @throws ServiceException
     *             when query site info failed.
     * @since SDNO 0.5
     */
    @SuppressWarnings("unchecked")
    public JsonNode query(Map<String, String> condition) throws ServiceException {

        checkFilterData(condition);

        RestfulResponse response = BrsRestconfProxy.get(Constants.SDNO_BRS_ADDR + SITEURI, "", condition);
        JsonNode listJsonObjectResponse = mapper.stringToNode(response.getResponseContent());
        ArrayNode sitesArray = (ArrayNode) listJsonObjectResponse.get(SITES_KEY);
        JsonNode site = sitesArray.get(0);
        if (null == site) {
            throw new LcmInternalException("No site matched BRS query");
        }
        return site;
    }

    /**
     * Check whether the filter field is supported.<br>
     *
     * @param condition
     *            condition
     * @throws ServiceException
     *             when filter field is not supported.
     * @since SDNO 0.5
     */
    private void checkFilterData(Map<String, String> condition) throws ServiceException {
        Set<String> suportKeys = new HashSet<String>();

        suportKeys.add("name");
        suportKeys.add("type");
        suportKeys.add("tenantID");
        suportKeys.add("siteID");

        for (Map.Entry<String, String> entry : condition.entrySet()) {
            if (!suportKeys.contains(entry.getKey())) {
                String errString = "Site resource does not support filter field " + entry.getKey();
                log.severe(errString);
                throw new LcmInternalException(errString);
            }
        }
    }

    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }
}