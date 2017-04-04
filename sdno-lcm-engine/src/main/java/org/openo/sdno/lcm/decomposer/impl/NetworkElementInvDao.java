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
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

//import org.codehaus.jackson.type.TypeReference;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.baseservice.roa.util.restclient.RestfulResponse;
import org.openo.sdno.lcm.exception.ExternalComponentException;
import org.openo.sdno.lcm.exception.LcmInternalException;
import org.openo.sdno.lcm.util.Constants;
import org.openo.sdno.lcm.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

@Component
public class NetworkElementInvDao {

    private static final String SITE_ID = "siteID";

    private final Logger log = Logger.getLogger("NetworkElementInvDao");

    private static final String NEURI = "/openoapi/sdnobrs/v1/managed-elements";

    private static final String NES_KEY = "managedElements";

    private static final String NE_KEY = "managedElement";

    @Autowired
    private Environment env;

    @Autowired
    private Mapper mapper;

    /**
     * Query NetworkElement model object info with input condition.Filter
     * properties: name, productName, ipAddress, adminState, source, owner,
     * managementDomainID, controllerID, siteID.<br>
     * 
     * @param condition
     *            condition
     * @return NetworkElement queried out
     * @throws ServiceException
     *             when query failed.
     * @since SDNO 0.5
     */
    @SuppressWarnings("unchecked")
    public JsonNode query(Map<String, String> condition) throws ServiceException {

        String errStr = "NE Query Failed";
        try {
            checkFilterData(condition);
            RestfulResponse response = new RestfulResponse();
            // condition doesn't seem to work if it's siteID
            if (!condition.containsKey(SITE_ID)) {
                response = BrsRestconfProxy.get(Constants.SDNO_BRS_ADDR + NEURI, "", condition);
            } else {
                response = BrsRestconfProxy.get(Constants.SDNO_BRS_ADDR + NEURI, "");
            }
            if (!BrsRestconfProxy.isSucess(response.getStatus())) {
                log.severe("Query Network Element by condition failed");
                throw new ServiceException("Query Network Element through condition failed");
            }

            String responseContent = response.getResponseContent();

            JsonNode contentMap = mapper.stringToNode(responseContent);

            JsonNode contentArray = contentMap.get(NES_KEY);
            if (!contentArray.isArray()) {
                throw new LcmInternalException("content is not an array");
            }
            if (((ArrayNode) contentArray).size() < 1) {
                log.severe("Content array size is " + ((ArrayNode) contentArray).size());
                throw new LcmInternalException("Query returned zero NEs");
            } else if (((ArrayNode) contentArray).size() == 1) {
                return contentArray.get(0);
            } else if (condition.containsKey(SITE_ID)) {
                // workaround for the failure to filter by siteID
                Iterator<JsonNode> neIt = ((ArrayNode) contentArray).elements();
                while (neIt.hasNext()) {
                    JsonNode me = neIt.next();
                    String neId = me.get("id").asText();
                    JsonNode fullMe = this.query(neId);
                    JsonNode site = fullMe.get(SITE_ID);
                    if (site.isValueNode() && site.asText().equals(condition.get(SITE_ID))) {
                        return fullMe;
                    } else if (site.isArray()) {
                        String site0 = site.get(0).asText();
                        String conditionSite = condition.get(SITE_ID);
                        if (site0.equals(conditionSite)) {

                            return fullMe;
                        }
                    }
                }
            } else
                throw new ExternalComponentException(errStr);

        } catch (Exception e) {
            log.severe(errStr);
            throw new ExternalComponentException(errStr);
        }
        return null;
    }

    public JsonNode query(String id) throws ServiceException {

        RestfulResponse response = BrsRestconfProxy.get(Constants.SDNO_BRS_ADDR + NEURI + "/" + id, "");
        JsonNode stringToNode = mapper.stringToNode(response.getResponseContent());
        return stringToNode.get(NE_KEY);
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
        Set<String> supportKeys = new HashSet<>();

        supportKeys.add("name");
        supportKeys.add("productName");
        supportKeys.add("ipAddress");
        supportKeys.add("adminState");
        supportKeys.add("source");
        supportKeys.add("owner");
        supportKeys.add("managementDomainID");
        supportKeys.add("controllerID");
        supportKeys.add(SITE_ID);
        supportKeys.add("serialNumber");
        supportKeys.add("nativeID");
        supportKeys.add("neRole");

        for (Map.Entry<String, String> entry : condition.entrySet()) {
            if (!supportKeys.contains(entry.getKey())) {
                String errString = "NetworkElement resource does not support filter field " + entry.getKey();
                log.severe(errString);
                throw new LcmInternalException(errString);
            }
        }
    }

    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }

    public void setEnv(Environment env) {
        this.env = env;
    }

}