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

package org.openo.sdno.lcm.catalogclient;

import org.openo.sdno.lcm.restclient.catalog.model.Parameters;
import org.openo.sdno.lcm.restclient.catalog.model.ServiceTemplate;

public interface ModelResourceApiClient {


    /**
     * Query service template by service template id
     * 
     * @param servicetemplateid
     *            service template id (required)
     * @return ServiceTemplate
     */
    ServiceTemplate getServiceTemplateById(String servicetemplateid);

    /**
     * Query input parameters of service template
     * 
     * @param servicetemplateid
     *            service template id (required)
     * @return Parameters
     */
    Parameters getServiceTemplateParameters(String servicetemplateid);

    /**
     * Query raw data of a service template by csar id
     * 
     * @param csarId
     *            Query Service Template Raw Data Condition (required)
     * @return ServiceTemplate ServiceTemplateRawData
     */
    String getServiceTemplateRawData(String csarId);

}
