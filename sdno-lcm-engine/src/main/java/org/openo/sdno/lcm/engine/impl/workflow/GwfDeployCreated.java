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
import org.openo.sdno.lcm.restclient.serviceinventory.model.UpdateConnectivityServiceRequest;
import org.openo.sdno.lcm.templatemodel.service.Instance;
import org.openo.sdno.lcm.templatemodel.service.Node;
import org.openo.sdno.lcm.util.Constants;
import org.springframework.stereotype.Component;

@Component
public class GwfDeployCreated extends GenericWorkflowImpl {

    private static final Logger log = Logger.getLogger("GwfDeployCreated");

    /*
     * (non-Javadoc)
     * @see org.openo.sdno.lcm.engine.GenericWorkflow#execute(java.util.Map)
     */
    @Override
    public Map<String, Object> execute(Map<String, Object> params) {

        log.fine("Execute GwfDeployCreated workflow");

        String csarId = (String)super.getParam(Constants.LCM_NBI_CSAR_ID, params);
        String apiOperation = (String)super.getParam(Constants.LCM_NBI_API_OPERATION, params);
        String serviceTemplateId = (String)super.getParam(Constants.LCM_NBI_TEMPLATE_ID, params);
        String serviceId = (String)super.getParam(Constants.LCM_NBI_SERVICE_ID, params);
        Instance templateInstance = (Instance)super.getParam(Constants.LCM_TEMPLATE_INSTANCE, params);
        Node connectivityServiceNode = templateInstance.getRootNode();

        // get the service from the inventory
        // GetConnectivityServiceResponseSample connectivityService =
        // defaultMssApiClient.readConnectivityService(serviceId).getObject();
        // log.fine("Connectivity service: " + connectivityService.toString());

        // executeWorkplan(csarId, apiOperation, templateInstance);

        UpdateConnectivityServiceRequest body = new UpdateConnectivityServiceRequest();
        body.setLifecycleState(Constants.LCM_STATE_DEPLOYED);
        defaultMssApiClient.updateConnectivityServiceRequest(serviceId, body);

        HashMap<String, Object> responseMap = new HashMap<String, Object>();
        // we can put the service ID here instead of job ID as the task is synchronous for now
        responseMap.put(Constants.LCM_NBI_JOB_ID, serviceId);
        return responseMap;
    }

    /*
     * (non-Javadoc)
     * @see org.openo.sdno.lcm.engine.RegisterWorkflow#getWorkflowId()
     */
    @Override
    public String getWorkflowId() {

        return GenericWorkflowId.DEPLOYCREATED.toString();
    }

}
