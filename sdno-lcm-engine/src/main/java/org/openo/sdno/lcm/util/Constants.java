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

package org.openo.sdno.lcm.util;

public class Constants {

    // property names for config.properties file
    public static final String COMMON_TOSCA_CATALOG_BASE_PATH = "common-tosca-catalog.base-path";

    public static final String COMMON_TOSCA_ARIA_BASE_PATH = "common-tosca-aria.base-path";

    public static final String COMMON_SERVICES_MSB_BASE_PATH = "common-services-msb.base-path";

    public static final String COMMON_SERVICES_MSB_BASE_URL = "common-services-msb.base4apicall";

    public static final String SDNO_BRS_SERVICEINVENTORY_BASE_PATH = "sdno-brs.serviceinventory.base-path";

    public static final String SDNO_LCM_DEFAULT_IP = "sdno-lcm.default-ip";

    // NBI input constants
    public static final String LCM_NBI_TEMPLATE_ID = "nsdId";

    public static final String LCM_NBI_SERVICE_NAME = "nsName";

    public static final String LCM_NBI_SERVICE_DESCRIPTION = "description";

    public static final String LCM_NBI_SERVICE_ID = "nsInstanceId";

    public static final String LCM_NBI_API_OPERATION = "apiOperation";

    public static final String LCM_NBI_ADDITIONAL_PARAMS = "additionalParamForNs";

    public static final String LCM_TEMPLATE_INSTANCE = "sdnoLcmTemplateInstance";

    public static final String LCM_NBI_CSAR_ID = "csarId";

    public static final String LCM_NBI_JOB_ID = "jobId";

    // state machine constants
    public static final String LCM_LIFECYCLESTATE_NONE = "none";

    public static final String LCM_LIFECYCLESTATE_DEPLOYED = "deployed";

    public static final String LCM_LIFECYCLESTATE_CREATED = "created";

    // per-workflow assigned property values
    public static final String LCM_ADMINISTRATIONSTATE_ACTIVE = "active";

    public static final String LCM_ACTIONSTATE_DEPLOYING = "deploying";

    public static final String LCM_ACTIONSTATE = "actionState";

    public static final String LCM_ADMINSTATUS = "adminStatus";

    public static final String LCM_CREATETIME = "createTime";

    // temporary
    public static final String SDNO_BRS_ADDR = "http://192.168.99.100:80";

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

    /**
     * EXCEED REQUEST CODE.
     */
    public static final int EXCEED_REQUEST = 413;

    public static final String DB_FILLTER_NOT_SUPPORT = "overlayvpn.db.fillter.error";

    public static boolean isSucess(int httpCode) {
        return httpCode / 100 == 2;
    }

    public static final String DB_RETURN_ERROR = "overlayvpn.db.return.error";

    public static final String THIN_CPE = "thinCPE";

    public static final String vCPE = "vCPE";

}
