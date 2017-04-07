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

package org.openo.sdno.lcm.engine.impl.workflow;

import java.util.Map;
import java.util.logging.Logger;

import org.openo.sdno.lcm.engine.GenericWorkflowId;
import org.openo.sdno.lcm.exception.ExternalComponentException;
import org.openo.sdno.lcm.restclient.serviceinventory.model.GetConnectivityServiceResponse;
import org.openo.sdno.lcm.restclient.serviceinventory.model.GetConnectivityServiceResponseSample;
import org.openo.sdno.lcm.util.Constants;
import org.springframework.stereotype.Component;

/**
 * Get workflow is a special case, it is not composed of other workflows but neither can it share
 * the generic implementation of execute() in AtomicWorkflow as we need to return the whole object.
 */
@Component
public class GwfGet extends GenericWorkflowImpl {

    private static final Logger log = Logger.getLogger("GwfGet");

    /*
     * (non-Javadoc)
     * @see org.openo.sdno.lcm.engine.GenericWorkflow#execute(java.util.Map)
     */
    @Override
    public Map<String, Object> execute(Map<String, Object> params) {

        String serviceId = (String)this.getParam(Constants.LCM_NBI_SERVICE_ID, params);

        GetConnectivityServiceResponse readConnectivityService;
        Map<String, Object> beanToMap = null;
        try {
            readConnectivityService = defaultMssApiClient.readConnectivityService(serviceId);
            GetConnectivityServiceResponseSample object = readConnectivityService.getObject();
            beanToMap = mapper.beanToMap(object);

        } catch(Exception e1) {
            throw new ExternalComponentException("Failed to read connectivity service due to " + e1.getMessage(), e1);
        }
        return beanToMap;
    }

    /*
     * (non-Javadoc)
     * @see org.openo.sdno.lcm.engine.RegisterWorkflow#getWorkflowId()
     */
    @Override
    public String getWorkflowId() {

        return GenericWorkflowId.GET.toString();
    }

    @Override
    protected Logger getLogger() {
        return log;
    }

}
