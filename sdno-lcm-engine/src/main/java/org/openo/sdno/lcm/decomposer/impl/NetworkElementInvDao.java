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

//package org.openo.sdno.overlayvpn.brs.invdao;
package org.openo.sdno.lcm.decomposer.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.type.TypeReference;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.baseservice.roa.util.restclient.RestfulResponse;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import net.sf.json.JSONObject;

import org.openo.sdno.lcm.restclient.catalog.ApiException;
import org.openo.sdno.lcm.util.Constants;

import org.openo.sdno.lcm.util.NetworkElementMO;

import org.springframework.core.env.Environment;

/**
 * NetworkElement data DAO class.<br>
 * 
 * @author
 * @version SDNO 0.5 2016-5-5
 */
@PropertySource("classpath:config.properties")
@Repository
public class NetworkElementInvDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(NetworkElementInvDao.class);

    private static final String NEURI = "/openoapi/sdnobrs/v1/managed-elements";

    private static final String NES_KEY = "managedElements";

    private static final String NE_KEY = "managedElement";

    @Autowired
    private Environment env;

    /**
     * Add NetworkElement object.<br>
     * 
     * @param curMO
     *            NetworkElement need to be added
     * @throws ServiceException
     *             when operation failed
     * @since SDNO 0.5
     */
    // public void addMO(ManagedElement curMO) throws ServiceException {
    // RestfulResponse response = BrsRestconfProxy.post(NEURI,
    // curMO.toJsonBody());
    // if(!HttpCode.isSucess(response.getStatus())) {
    // LOGGER.error("Add NetworkElement failed, NetworkElement Name: " +
    // curMO.getName());
    // throw new ServiceException("Add NetworkElement failed");
    // }
    // }

    /**
     * Delete NetworkElement object.<br>
     * 
     * @param uuid
     *            NetworkElement id
     * @throws ServiceException
     *             when delete model object failed
     * @since SDNO 0.5
     */
    // public void deleteMO(String uuid) throws ServiceException {
    // if(!UuidUtil.validate(uuid)) {
    // LOGGER.error("delete uuid is ilegal!");
    // SvcExcptUtil.throwBadRequestException("delete uuid is ilegal!");
    // }
    //
    // RestfulResponse response = BrsRestconfProxy.delete(NEURI + "/" + uuid,
    // null);
    // if(!HttpCode.isSucess(response.getStatus())) {
    // LOGGER.error("Delete Network Element failed");
    // throw new ServiceException(response.getStatus(), "Delete Network Element
    // failed");
    // }
    // }

    /**
     * Update NetworkElement object.<br>
     * 
     * @param curNeMO
     *            NetworkElement need to updated
     * @throws ServiceException
     * @since SDNO 0.5
     */
    // public void updateMO(NetworkElementMO curNeMO) throws ServiceException {
    // StringBuilder curID = new StringBuilder(curNeMO.getId());
    // curNeMO.setId(null);
    // RestfulResponse response = BrsRestconfProxy.put(NEURI + "/" +
    // curID.toString(), curNeMO.toJsonBody());
    // if(!HttpCode.isSucess(response.getStatus())) {
    // LOGGER.error("Update Network Element failed");
    // throw new ServiceException("Update Network Element failed");
    // }
    // }

    /**
     * Query all NetworkElements.<br>
     * 
     * @return all NetworkElement queried out
     * @throws ServiceException
     *             when query failed.
     * @since SDNO 0.5
     */
    // @SuppressWarnings("unchecked")
    // public List<ManagedElement> getAllMO() throws ServiceException {
    // RestfulResponse response =
    // BrsRestconfProxy.get(Constants.SDNO_BRS_DOCKER_IP + NEURI, "");
    // if(!Constants.isSucess(response.getStatus())) {
    // LOGGER.error("Query all Network Element failed");
    // throw new ServiceException("Query Network Element failed");
    // }
    //
    // Map<String, Object> contentMap =
    // JsonUtil.fromJson(response.getResponseContent(), Map.class);
    // String data = JsonUtil.toJson(contentMap.get(NES_KEY));
    // return JsonUtil.fromJson(data, new TypeReference<List<ManagedElement>>()
    // {});
    // }

    /**
     * Get NetworkElementMOs by Uuids.<br>
     * 
     * @param ids
     *            List of Uuids
     * @return List of NetworkElementMO queried out
     * @throws ServiceException
     *             when query failed
     * @since SDNO 0.5
     */
    // public List<NetworkElementMO> getMOByIds(List<String> ids) throws
    // ServiceException {
    // if(CollectionUtils.isEmpty(ids)) {
    // return new ArrayList<>();
    // }
    //
    // List<NetworkElementMO> networkElementList = new ArrayList<>();
    // for(String curNeUuid : ids) {
    // networkElementList.add(query(curNeUuid));
    // }
    //
    // return networkElementList;
    // }

    /**
     * Query NetworkElement by Controller.<br>
     * 
     * @param controllerID
     *            controller id
     * @return NetworkElement queried out
     * @throws ServiceException
     *             when query failed.
     * @since SDNO 0.5
     */
    // public List<NetworkElementMO> queryMOByConntrollerId(String controllerID)
    // throws ServiceException {
    // Map<String, String> conditionMap = new HashMap<>();
    // conditionMap.put("controllerID", controllerID);
    // return query(conditionMap);
    // }

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
    public List<JSONObject> query(Map<String, String> condition) throws ServiceException {

        try {
            checkFilterData(condition);
            RestfulResponse response = new RestfulResponse();
            response = BrsRestconfProxy.get(Constants.SDNO_BRS_ADDR + NEURI, "", condition);

            if (!Constants.isSucess(response.getStatus())) {
                LOGGER.error("Query Network Element by condition failed");
                throw new ServiceException("Query Network Element through condition failed");
            }

            Map<String, Object> contentMap = JsonUtil.fromJson(response.getResponseContent(), Map.class);
            String data = JsonUtil.toJson(contentMap.get(NES_KEY));
            System.out.println("Data = " + data);

            List<JSONObject> listJsonObjectResponse;
            listJsonObjectResponse = JsonUtil.fromJson(data, new TypeReference<List<JSONObject>>() {
            });

            System.out.println(listJsonObjectResponse);
            // System.out.println (listJsonObjectResponse.get(0).get("id"));

            return listJsonObjectResponse;
        } catch (ServiceException se) {
            System.out.println("ServiceException");
            throw new ServiceException(400, "Query Failed");
        }

        // List<NetworkElementMO> lme = JsonUtil.fromJson(data, new
        // TypeReference <List<NetworkElementMO>>() {} );
        // return JsonUtil.fromJson(data, new
        // TypeReference<List<NetworkElementMO>>() {});
    }

    /**
     * Query NetworkElement by id.<br>
     * 
     * @param id
     *            NetworkElement id
     * @return NE NetworkElement queried out
     * @throws ServiceException
     *             when query failed.
     * @since SDNO 0.5
     */
    // @SuppressWarnings("unchecked")
    // public ManagedElement query(String id) throws ServiceException {
    // RestfulResponse response =
    // BrsRestconfProxy.get(Constants.SDNO_BRS_DOCKER_IP + NEURI + "/" + id,
    // null);
    // if(!Constants.isSucess(response.getStatus())) {
    // LOGGER.error("Query Network Element by uuid failed,the uuid is :" + id);
    // throw new ServiceException("Query Network Element by uuid failed");
    // }
    //
    // Map<String, Object> contentMap =
    // JsonUtil.fromJson(response.getResponseContent(), Map.class);
    // Map<String, Object> objectMap = (Map<String,
    // Object>)contentMap.get(NE_KEY);
    // if(objectMap == null || objectMap.isEmpty()) {
    // LOGGER.error("Network Element does not exist");
    // throw new ServiceException("Network Element does not exist");
    // }
    //
    // String data = JsonUtil.toJson(objectMap);
    // return JsonUtil.fromJson(data, ManagedElement.class);
    // }

    /**
     * Query NetworkElement info with site ID.<br>
     * 
     * @param siteId
     *            site id
     * @return NetworkElement queried out
     * @throws ServiceException
     *             when query failed.
     * @since SDNO 0.5
     */
    // public List<NetworkElementMO> getNeBySiteId(String siteId) throws
    // ServiceException {
    // Map<String, String> conditionMap = new HashMap<>();
    // conditionMap.put("siteID", siteId);
    // return query(conditionMap);
    // }

    /**
     * Query NetworkElement info with site ID.<br>
     * 
     * @param name
     *            NetworkElement name
     * @return NetworkElement queried out
     * @throws ServiceException
     *             when query failed.
     * @since SDNO 0.5
     */
    // public List<NetworkElementMO> getNeByName(String name) throws
    // ServiceException {
    // Map<String, String> conditionMap = new HashMap<>();
    // conditionMap.put("name", name);
    // return query(conditionMap);
    // }

    /**
     * Query site list by NetworkElement id.<br>
     * 
     * @param neId
     *            NetworkElement id
     * @return list of site queried out
     * @throws ServiceException
     *             when query failed.
     * @since SDNO 0.5
     */
    // public List<SiteMO> getSiteByNeId(String neId) throws ServiceException {
    // List<SiteMO> siteList = new ArrayList<>();
    //
    // SiteInvDao siteDao = new SiteInvDao();
    //
    // NetworkElementMO ne = query(neId);
    //
    // if((ne != null) && (ne.getSiteID() != null)) {
    // for(String siteId : ne.getSiteID()) {
    // SiteMO site = siteDao.query(siteId);
    // if(site != null) {
    // siteList.add(site);
    // }
    // }
    // }
    //
    // return siteList;
    // }

    /**
     * Query site list with tenant ID. <br>
     * 
     * @param tenantId
     *            tenant ID
     * @return list of site queried out
     * @throws ServiceException
     *             when query failed.
     * @since SDNO 0.5
     */
    // public List<NetworkElementMO> getNeByTenantId(String tenantId) throws
    // ServiceException {
    // SiteInvDao siteDao = new SiteInvDao();
    //
    // List<NetworkElementMO> neList = new ArrayList<>();
    // List<SiteMO> siteList = siteDao.getSiteByTenantId(tenantId);
    //
    // for(SiteMO site : siteList) {
    // neList.addAll(getNeBySiteId(site.getId()));
    // }
    //
    // return neList;
    // }

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
        Set<String> suportKeys = new HashSet<>();

        suportKeys.add("name");
        suportKeys.add("productName");
        suportKeys.add("ipAddress");
        suportKeys.add("adminState");
        suportKeys.add("source");
        suportKeys.add("owner");
        suportKeys.add("managementDomainID");
        suportKeys.add("controllerID");
        suportKeys.add("siteID");
        suportKeys.add("serialNumber");
        suportKeys.add("nativeID");
        suportKeys.add("neRole");

        for (Map.Entry<String, String> entry : condition.entrySet()) {
            System.out.println(entry.getKey());
            if (!suportKeys.contains(entry.getKey())) {
                LOGGER.error("ne is not support filter feild with " + entry.getKey());
                throw new ServiceException(Constants.DB_FILLTER_NOT_SUPPORT);
            }
        }
    }

}