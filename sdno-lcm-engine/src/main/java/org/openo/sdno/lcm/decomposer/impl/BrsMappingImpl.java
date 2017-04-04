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

package org.openo.sdno.lcm.decomposer.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdno.lcm.decomposer.BrsMapping;
import org.openo.sdno.lcm.exception.ExternalComponentException;
import org.openo.sdno.lcm.exception.LcmInternalException;
import org.openo.sdno.lcm.templatemodel.service.Node;
import org.openo.sdno.lcm.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;

@Component
public class BrsMappingImpl implements BrsMapping {

    private static final String NONE = "none";
    private static final String IP_ADDRESS = "ipAddress";
    private static final String SITE_NAME = "siteName";
    private final Logger log = Logger.getLogger("BrsMappingImpl");

    @Autowired
    private SiteInvDao siteInvDao;

    @Autowired
    private NetworkElementInvDao neInvDao;

    private JsonNode retrieveResourceFromBrs(String nodeName, Map<String, String> condition) {

        log.info(String.format("Node name %s ", nodeName));
        log.info("Retrieveing Resource info from BRS");

        Map<String, String> querycondition = new HashMap();

        for (String conditionKey : condition.keySet()) {
            if (condition.get(conditionKey) != "0") {
                querycondition.put(conditionKey, condition.get(conditionKey));
            }
        }

        // qeury BRS for managed-elements, such as Fw, Lb, Gw
        // input is FwIp, LbIp, GwIp, and we need the ID of these Managed
        // Elements by quering the BRS
        if ((!nodeName.equals(Constants.THIN_CPE)) && (!nodeName.equals(Constants.vCPE))) {
            try {
                JsonNode managedElementJsonObject = retrieveManagedElement(querycondition);
                log.info("managedElementJsonObject" + managedElementJsonObject.toString());
                return managedElementJsonObject;
            } catch (Exception se) {
                throw new LcmInternalException("");

            }

        }

        // for ThinCPE and vCPE elements, we know the siteName
        // Step1. get SiteId based on siteName (using /sites)
        // Step2. get ThinCPE Id and vCPE Id, using the siteId to query the BRS
        // (using /manageg-elements)
        else {

            // Step1 - get SiteId

            JsonNode managedElementJsonObject = null;
            JsonNode siteElementJsonObject = retrieveSiteNode(querycondition);

            String siteId = siteElementJsonObject.get("id").toString();
            log.info("ID of the queried Site is " + siteId);

            Map<String, String> conditionQueryBrsBySiteId = new HashMap<>();
            conditionQueryBrsBySiteId.put("siteID", siteId.substring(1, siteId.length() - 1));

            // Step2 - retrieve all the ME associated with this siteID -
            // query /managed-elements
            try {
                managedElementJsonObject = retrieveManagedElement(conditionQueryBrsBySiteId);
                log.info("ID of the queried ME is " + managedElementJsonObject.get("id").toString());
                return managedElementJsonObject;
            } catch (Exception se) {

                throw new LcmInternalException("Retrieve Managed Element Exception: ", se);
            }

        }
    }

    private JsonNode retrieveManagedElement(Map<String, String> condition) throws ServiceException {

        try {

            return neInvDao.query(condition);

        } catch (Exception e) {
            throw new ExternalComponentException("Failed to retrieve ManagedElement", e);
        }
    }

    private JsonNode retrieveSiteNode(Map<String, String> condition) {

        try {
            Map<String, String> conditionQuerySiteBySiteName = new HashMap<>();
            for (String key : condition.keySet()) {
                if (key == SITE_NAME) {
                    conditionQuerySiteBySiteName.put("name", condition.get(key));
                } else {
                    conditionQuerySiteBySiteName.put(key, condition.get(key));
                }
            }
            JsonNode site = siteInvDao.query(conditionQuerySiteBySiteName);

            return site;

        } catch (Exception e) {

            throw new ExternalComponentException(e.getMessage(), e);
        }
    }

    @Override
    public void enrichResourceNode(Node node) {

        String templateName = node.getTemplateName();
        Map<String, String> condition = new HashMap<>();
        String siteName = node.getPropertyValue(SITE_NAME);
        if (null != siteName && !siteName.equals(NONE)) {

            condition.put(SITE_NAME, siteName);
        }
        String ipAddress = node.getPropertyValue(IP_ADDRESS);
        if (null != ipAddress && !ipAddress.equals(NONE) && !ipAddress.equals("0.0.0.0")) {

            condition.put(IP_ADDRESS, ipAddress);
        }
        if (condition.isEmpty()) {
            throw new LcmInternalException(
                    "Cannot enrich resource node with empty conditions - maybe input parameter values are missing?");
        }
        JsonNode retrieveManagedElement = this.retrieveResourceFromBrs(templateName, condition);
        for (String nameString : node.getNamesOfPropertiesWithValue("0")) {

            log.info(String.format("set a value for the resource node field %s", nameString));
            String value = retrieveManagedElement.get(nameString).asText();
            if (null == value) {
                throw new LcmInternalException("Null value for " + nameString);

            }
            node.replacePropertyValue(nameString, value);

        }
    }

    public void setSiteInvDao(SiteInvDao siteInvDao) {
        this.siteInvDao = siteInvDao;
    }

    public void setNeInvDao(NetworkElementInvDao neInvDao) {
        this.neInvDao = neInvDao;
    }
}
