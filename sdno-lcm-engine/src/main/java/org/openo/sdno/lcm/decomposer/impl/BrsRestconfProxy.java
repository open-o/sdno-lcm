/*
 * Copyright 2016 Huawei Technologies Co., Ltd.
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

import java.util.Map;
import java.util.logging.Logger;

import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.baseservice.roa.util.restclient.RestfulParametes;
import org.openo.baseservice.roa.util.restclient.RestfulResponse;
import org.openo.sdno.framework.container.resthelper.RestfulProxy;
import org.openo.sdno.lcm.exception.ExternalComponentException;

public class BrsRestconfProxy {

    private static final String BRS_REQUEST_FAILED_RESPONSE_WAS_NULL = "Brs request Failed - response was null!!";

    private static final String BRS_REQUEST_FAILED = "brs request Failed!!";

    /**
     * Server Failed Code.
     */
    public static final int ERR_FAILED = 500;

    /**
     * OK Code.
     */
    public static final int RESPOND_OK = 200;

    /**
     * CREATED OK Code.
     */
    public static final int CREATE_OK = 201;

    /**
     * Not Found Code.
     */
    public static final int NOT_FOUND = 404;

    /**
     * BAD REQUEST CODE.
     */
    public static final int BAD_REQUEST = 400;

    public static boolean isSuccess(int httpCode) {
        return httpCode / 100 == 2;
    }

    private static final Logger log = Logger.getLogger("DecomposerImpl");

    private static final String GET_ACTION = "get";

    private static final String POST_ACTION = "post";

    private static final String DELETE_ACTION = "delete";

    private static final String PUT_ACTION = "put";

    private BrsRestconfProxy() {

    }

    /**
     * BRS get interface.<br>
     * 
     * @param url
     *            request URL
     * @param body
     *            request body
     * @return response body
     * @throws ServiceException
     *             when failed
     * @since SDNO 0.5
     */
    public static RestfulResponse get(String url, String body) throws ServiceException {
        return restInvoke(url, GET_ACTION, body, null);
    }

    /**
     * BRS post interface.<br>
     * 
     * @param url
     *            request URL
     * @param body
     *            request body
     * @return response body
     * @throws ServiceException
     *             when failed
     * @since SDNO 0.5
     */
    public static RestfulResponse post(String url, String body) throws ServiceException {
        return restInvoke(url, POST_ACTION, body, null);
    }

    /**
     * BRS delete interface.<br>
     * 
     * @param url
     *            request URL
     * @param body
     *            request body
     * @return response body
     * @throws ServiceException
     *             when failed
     * @since SDNO 0.5
     */
    public static RestfulResponse delete(String url, String body) throws ServiceException {
        return restInvoke(url, DELETE_ACTION, body, null);
    }

    /**
     * BRS put interface.<br>
     * 
     * @param url
     *            request URL
     * @param body
     *            request body
     * @return response body
     * @throws ServiceException
     *             when failed
     * @since SDNO 0.5
     */
    public static RestfulResponse put(String url, String body) throws ServiceException {
        return restInvoke(url, PUT_ACTION, body, null);
    }

    /**
     * BRS get interface with paramMap.<br>
     * 
     * @param url
     *            request URL
     * @param body
     *            request body
     * @param paramMap
     *            parameter map
     * @return response body
     * @throws ServiceException
     *             when failed
     * @since SDNO 0.5
     */
    public static RestfulResponse get(String url, String body, Map<String, String> paramMap) throws ServiceException {
        return restInvoke(url, GET_ACTION, body, paramMap);
    }

    private static RestfulResponse restInvoke(String url, String action, String body, Map<String, String> paramMap)
            throws ServiceException {

        final RestfulParametes restfulParametes = new RestfulParametes();
        restfulParametes.putHttpContextHeader("Content-Type", MediaType.APPLICATION_JSON);
        restfulParametes.putHttpContextHeader("Accept", MediaType.APPLICATION_JSON);

        if (null != paramMap) {
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                restfulParametes.put(entry.getKey(), entry.getValue());
            }
        }

        if (StringUtils.isNotEmpty(body)) {
            restfulParametes.setRawData(body);
        }

        RestfulResponse response = doRestfulInvoke(url, action, restfulParametes);

        if (null == response) {
            log.severe(BRS_REQUEST_FAILED_RESPONSE_WAS_NULL);
            throw new ExternalComponentException(BRS_REQUEST_FAILED_RESPONSE_WAS_NULL);
        }
        log.info("BRS response: " + response.getStatus());

        if (!isSuccess(response.getStatus())) {
            // if code is 404,then not found
            if (GET_ACTION.equals(action) && (NOT_FOUND == response.getStatus())) {
                return response;
            }

            log.severe(BRS_REQUEST_FAILED);
            throw new ExternalComponentException(BRS_REQUEST_FAILED);
        }

        return response;
    }

    private static RestfulResponse doRestfulInvoke(String url, String action, RestfulParametes restfulParametes)
            throws ServiceException {

        RestfulResponse response = null;

        if (GET_ACTION.equals(action)) {
            response = RestfulProxy.get(url, restfulParametes);
        } else if (PUT_ACTION.equals(action)) {
            response = RestfulProxy.put(url, restfulParametes);
        } else if (POST_ACTION.equals(action)) {
            response = RestfulProxy.post(url, restfulParametes);
        } else if (DELETE_ACTION.equals(action)) {
            response = RestfulProxy.delete(url, restfulParametes);
        }

        return response;
    }
}