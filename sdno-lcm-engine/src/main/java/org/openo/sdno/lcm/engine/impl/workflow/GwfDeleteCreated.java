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

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.openo.sdno.lcm.engine.GenericWorkflowId;
import org.openo.sdno.lcm.templatemodel.service.Instance;
import org.openo.sdno.lcm.util.Constants;
import org.springframework.stereotype.Component;

/**
 * Delete workflow is a special case, it is not composed of other workflows but
 * neither can it share the generic implementation of execute() in
 * AtomicWorkflow.
 */
@Component
public class GwfDeleteCreated extends GenericWorkflowImpl {

    private static final Logger log = Logger.getLogger("GwfDeleteCreated");

    /*
     * (non-Javadoc)
     * 
     * @see org.openo.sdno.lcm.engine.GenericWorkflow#execute(java.util.Map)
     */
    @Override
    public Map<String, Object> execute(Map<String, Object> params) {

        this.getLogger().fine(String.format("Execute %s workflow", this.getWorkflowId()));
        // get the required params
        String csarId = (String) this.getParam(Constants.LCM_NBI_CSAR_ID, params);
        String apiOperation = (String) this.getParam(Constants.LCM_NBI_API_OPERATION, params);
        String serviceId = (String) this.getParam(Constants.LCM_NBI_SERVICE_ID, params);
        Instance templateInstance = (Instance) this.getParam(Constants.LCM_TEMPLATE_INSTANCE, params);

        // execute the workplan
        executeWorkplan(csarId, apiOperation, templateInstance, serviceId);

        // delete the service in inventory
        defaultMssApiClient.deleteConnectivityService(serviceId);

        // fill response map
        HashMap<String, Object> responseMap = new HashMap<>();
        // we can put the service ID here instead of job ID as the task is
        // synchronous for now
        responseMap.put(Constants.LCM_NBI_JOB_ID, serviceId);
        return responseMap;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.openo.sdno.lcm.engine.RegisterWorkflow#getWorkflowId()
     */
    @Override
    public String getWorkflowId() {

        return GenericWorkflowId.DELETECREATED.toString();
    }

    @Override
    protected Logger getLogger() {
        return log;
    }

}
