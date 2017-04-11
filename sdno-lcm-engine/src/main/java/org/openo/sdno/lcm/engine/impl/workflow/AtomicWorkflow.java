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

import org.openo.sdno.lcm.restclient.serviceinventory.model.GetConnectivityServiceResponse;
import org.openo.sdno.lcm.restclient.serviceinventory.model.UpdateConnectivityServiceRequest;
import org.openo.sdno.lcm.restclient.serviceinventory.model.UpdateResponse;
import org.openo.sdno.lcm.templatemodel.service.Instance;
import org.openo.sdno.lcm.templatemodel.service.Node;
import org.openo.sdno.lcm.util.Constants;

/**
 * Abstract class representing a workflow that is not composed of other
 * workflows.
 */
public abstract class AtomicWorkflow extends GenericWorkflowImpl {

    /*
     * (non-Javadoc)
     * 
     * @see org.openo.sdno.lcm.engine.Workflow#execute(java.util.Map)
     */
    @Override
    public Map<String, Object> execute(Map<String, Object> params) {

        this.getLogger().fine(String.format("Execute %s workflow", this.getWorkflowId()));
        // get the required params
        String csarId = (String) this.getParam(Constants.LCM_NBI_CSAR_ID, params);
        String apiOperation = (String) this.getParam(Constants.LCM_NBI_API_OPERATION, params);
        String serviceId = (String) this.getParam(Constants.LCM_NBI_SERVICE_ID, params);
        Instance templateInstance = (Instance) this.getParam(Constants.LCM_TEMPLATE_INSTANCE, params);

        this.updateTemplateInstancePreDispatch(templateInstance);
        updateServiceInventory(serviceId, templateInstance);

        // execute the workplan
        executeWorkplan(csarId, apiOperation, templateInstance);

        this.updateTemplateInstancePostDispatch(templateInstance);
        UpdateResponse updateServiceInventoryResponse = updateServiceInventory(serviceId, templateInstance);

        try {
            GetConnectivityServiceResponse readConnectivityService = defaultMssApiClient
                    .readConnectivityService(serviceId);
            this.getLogger()
                    .info(String.format("Updated connectivity service:%n%s", readConnectivityService.toString()));
        } catch (Exception e) {
            this.getLogger().fine(e.toString());
            this.getLogger()
                    .warning("Failed to read the updated connectivity service from inventory due to " + e.getMessage());
            this.getLogger().info(String.format("Update connectivity service response:%n%s",
                    updateServiceInventoryResponse.toString()));
        }

        // fill response map
        HashMap<String, Object> responseMap = new HashMap<>();
        // we can put the service ID here instead of job ID as the task is
        // synchronous for now
        responseMap.put(Constants.LCM_NBI_JOB_ID, serviceId);
        return responseMap;
    }

    /**
     * @param serviceId
     * @param templateInstance
     * @return
     */
    private UpdateResponse updateServiceInventory(String serviceId, Instance templateInstance) {
        // update the service in inventory
        UpdateConnectivityServiceRequest body = new UpdateConnectivityServiceRequest();
        Node rootNode = templateInstance.getRootNode();
        body.setLifecycleState(this.getNewState());
        body.setActionState(rootNode.getPropertyValue("actionState"));
        body.setAdminStatus(rootNode.getPropertyValue("adminStatus"));
        body.setStatusReason(rootNode.getPropertyValue("statusReason"));
        return defaultMssApiClient.updateConnectivityServiceRequest(serviceId, body);
    }

    /**
     * Gets the identifier of the state the service will be in when the workflow
     * is finished.
     * 
     * @return the new state string
     */
    protected abstract String getNewState();

    /**
     * Updates the Instance with values that are set per workflow, before
     * messages are sent to SBI
     * 
     * @param templateInstance
     *            the Instance of the TOSCA service blueprint
     * @param serviceCreateTime
     *            the time the Connectivity Service was created
     */
    protected abstract void updateTemplateInstancePreDispatch(Instance templateInstance);

    /**
     * Updates the Instance with values that are set per workflow, after
     * messages are sent to SBI.
     * 
     * @param templateInstance
     *            the Instance of the TOSCA service blueprint
     * @param serviceCreateTime
     *            the time the Connectivity Service was created
     */
    protected abstract void updateTemplateInstancePostDispatch(Instance templateInstance);

}
