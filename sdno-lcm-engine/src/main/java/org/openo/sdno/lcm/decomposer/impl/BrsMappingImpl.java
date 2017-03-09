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

import java.util.List;
import java.util.Stack;
import java.util.UUID;
import java.util.logging.Logger;

import org.openo.sdno.lcm.decomposer.BrsMapping;

import org.openo.sdno.lcm.templatemodel.csar.Csar;
import org.openo.sdno.lcm.templatemodel.service.Dependency;
import org.openo.sdno.lcm.templatemodel.service.Instance;
import org.openo.sdno.lcm.templatemodel.service.Node;
import org.openo.sdno.lcm.model.workplan.WorkPlan;
import org.springframework.stereotype.Component;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.openo.baseservice.remoteservice.exception.ServiceException;
//import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.baseservice.roa.util.restclient.RestfulParametes;
import org.openo.baseservice.roa.util.restclient.RestfulResponse;
import org.openo.sdno.framework.container.resthelper.RestfulProxy;

import org.openo.sdno.lcm.decomposer.impl.NetworkElementInvDao;
import org.openo.sdno.lcm.exception.ExternalComponentException;

import java.util.Map;
import java.util.HashMap;

import org.openo.sdno.lcm.util.Constants;
import org.openo.sdno.lcm.util.NetworkElementMO;
import org.slf4j.LoggerFactory;

@Component
public class BrsMappingImpl implements BrsMapping {

    private final Logger log = Logger.getLogger("BrsMappingImpl");
    
    @Override
    public JSONObject retrieveResourceFromBrs(String nodeName, Map<String, String> condition) {

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
        if ((nodeName != Constants.THIN_CPE) && (nodeName != Constants.vCPE)) {
            try {
                JSONObject managedElementJsonObject = retrieveManagedElement(querycondition);
                return managedElementJsonObject;
            } catch (ServiceException se) {
                log.info("Retrieve Managed Element Exception: " + se);
                return null;

            }

        }

        // for ThinCPE and vCPE elements, we know the siteName
        // Step1. get SiteId based on siteName (using /sites)
        // Step2. get ThinCPE Id and vCPE Id, using the siteId to query the BRS
        // (using /manageg-elements)
        else {

            // Step1 - get SiteId

            JSONObject managedElementJsonObject = new JSONObject();
            JSONObject siteElementJsonObject = retrieveSiteNode(querycondition);

            if (siteElementJsonObject != null && !siteElementJsonObject.isEmpty()) {
                String siteId = siteElementJsonObject.get("id").toString();
                log.info("ID of the queried Site is " + siteId);

                Map<String, String> conditionQueryBrsBySiteId = new HashMap<>();
                conditionQueryBrsBySiteId.put("siteID", siteId);

                // Step2 - retrieve all the ME associated with this siteID -
                // query /managed-elements
                try {
                    managedElementJsonObject = retrieveManagedElement(conditionQueryBrsBySiteId);
                    log.info("ID of the queried ME is " + managedElementJsonObject.get("id").toString());
                    return managedElementJsonObject;
                } catch (ServiceException se) {
                    
                    log.info("Retrieve Managed Element Exception: " + se);
                    return null;
                }

            } else {
                return null;
            }
        }

    }

    JSONObject retrieveManagedElement(Map<String, String> condition) throws ServiceException {
        final NetworkElementInvDao neInvDao = new NetworkElementInvDao();

        try {
            List<JSONObject> managedElementJsonList = neInvDao.query(condition);

            JSONObject managedElementJsonObject = new JSONObject();

            if (managedElementJsonList.size() != 0) {
                managedElementJsonObject = managedElementJsonList.get(0);
            }
            return managedElementJsonObject;

        } catch (ServiceException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            System.err.println("Exception when calling DefaultApi#queryManagedElement");
            throw new ServiceException("Exception", e);
            // return null;
        }
    }

    public JSONObject retrieveSiteNode(Map<String, String> condition) {

        final SiteInvDao siteInvDao = new SiteInvDao();

        try {
            Map<String, String> conditionQuerySiteBySiteName = new HashMap<>();
            for (String key : condition.keySet())
                if (key == "siteName") {
                    conditionQuerySiteBySiteName.put("name", condition.get(key));
                } else {
                    conditionQuerySiteBySiteName.put(key, condition.get(key));
                }
            List<JSONObject> siteList = siteInvDao.query(conditionQuerySiteBySiteName);

            JSONObject siteElementJsonObject = new JSONObject();

            if (siteList.size() != 0) {
                siteElementJsonObject = siteList.get(0);
            }

            return siteElementJsonObject;

        } catch (ServiceException e) {

            // e.printStackTrace();
            System.err.println("Exception when calling DefaultApi#queryManagedElement");
            // throw new ServiceException("Exception", e);
            return null;
        }
    }

}
