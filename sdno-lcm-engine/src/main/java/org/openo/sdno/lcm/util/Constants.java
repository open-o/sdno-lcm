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

    public static final String SDNO_BRS_RESOURCEINVENTORY_BASE_PATH = "sdno-brs.resourceinventory.base-path";

    public static final String SDNO_LCM_DEFAULT_IP = "sdno-lcm.default-ip";

    // NBI input constants
    public static final String LCM_NBI_TEMPLATE_ID = "nsdId";

    public static final String LCM_NBI_SERVICE_NAME = "nsName";

    public static final String LCM_NBI_SERVICE_DESCRIPTION = "description";

    public static final String LCM_NBI_SERVICE_ID = "nsInstanceId";

    public static final String LCM_NBI_API_OPERATION = "apiOperation";
    
    public static final String LCM_NBI_API_OPERATION_CREATE = "create";

    public static final String LCM_NBI_API_OPERATION_DELETE = "delete";

    public static final String LCM_NBI_API_OPERATION_GET = "get";

    public static final String LCM_NBI_API_OPERATION_DEPLOY = "deploy";

    public static final String LCM_NBI_API_OPERATION_UNDEPLOY = "undeploy";

    public static final String LCM_NBI_API_OPERATION_UPDATE = "update";

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

    public static final String LCM_ADMINISTRATIONSTATE_INACTIVE = "inactive";

    public static final String LCM_ADMINISTRATIONSTATE_NONE = "none";

    public static final String LCM_ACTIONSTATE_UNDEPLOYING = "undeploying";

    public static final String LCM_ACTIONSTATE_CREATING = "creating";

    public static final String LCM_ACTIONSTATE_DELETING = "deleting";

    public static final String LCM_ACTIONSTATE_DEPLOYING = "deploying";

    public static final String LCM_ACTIONSTATE_NORMAL = "normal";

    public static final String LCM_ACTIONSTATE = "actionState";

    public static final String LCM_ADMINSTATUS = "adminStatus";

    public static final String LCM_CREATETIME = "createTime";

    public static final String THIN_CPE = "thinCpe";

    public static final String vCPE = "vCpe";

}
