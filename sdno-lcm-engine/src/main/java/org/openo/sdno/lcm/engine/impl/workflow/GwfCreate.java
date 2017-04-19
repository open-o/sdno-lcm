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
import org.openo.sdno.lcm.exception.SouthboundExecutionException;
import org.openo.sdno.lcm.model.workplan.WorkPlan;
import org.openo.sdno.lcm.model.workplan.WorkPlanExecutionResult;
import org.openo.sdno.lcm.model.workplan.WorkPlanExecutionStrategy;
import org.openo.sdno.lcm.restclient.serviceinventory.model.ConnectivityService;
import org.openo.sdno.lcm.restclient.serviceinventory.model.CreateConnectivityServiceResponse;
import org.openo.sdno.lcm.restclient.serviceinventory.model.CreateConnectivityServiceResponseSample;
import org.openo.sdno.lcm.templatemodel.service.Instance;
import org.openo.sdno.lcm.templatemodel.service.Node;
import org.openo.sdno.lcm.util.Constants;
import org.springframework.stereotype.Component;

/**
 * Create workflow is a special case, it is not composed of other workflows but
 * neither can it share the generic implementation of execute() in
 * AtomicWorkflow. This workflow corresponds to the following state table row:
 * "apiOperation": "create", "currentState": "none", "newState": "created",
 * "transitionWorkflow": "create"
 */
@Component
public class GwfCreate extends GenericWorkflowImpl {

    /*
     */

    private static final Logger log = Logger.getLogger("GwfCreate");

    /*
     * (non-Javadoc)
     * 
     * @see org.openo.sdno.lcm.engine.Workflow#execute(java.util.Map)
     */
    @Override
    public Map<String, Object> execute(Map<String, Object> params) {

        log.info("Execute GwfCreate workflow");

        String csarId = (String) super.getParam(Constants.LCM_NBI_CSAR_ID, params);
        String apiOperation = (String) super.getParam(Constants.LCM_NBI_API_OPERATION, params);
        String serviceTemplateId = (String) super.getParam(Constants.LCM_NBI_TEMPLATE_ID, params);
        Instance templateInstance = (Instance) super.getParam(Constants.LCM_TEMPLATE_INSTANCE, params);
        String serviceName = (String) super.getParam(Constants.LCM_NBI_SERVICE_NAME, params);
        String serviceDescription = (String) super.getParam(Constants.LCM_NBI_SERVICE_DESCRIPTION, params);
        Node connectivityServiceNode = templateInstance.getRootNode();

        // create the Connectivity Service in DB
        ConnectivityService connectivityService = new ConnectivityService();
        connectivityService.setLifecycleState(Constants.LCM_LIFECYCLESTATE_CREATED);
        connectivityService.setTemplateId(serviceTemplateId);
        connectivityService.setName(serviceName);
        // we don't set 'id' so it will be assigned a generated uuid value by
        // the MSS
        if (null != connectivityServiceNode.getPropertyValue("actionState")) {
            connectivityService.setActionState(connectivityServiceNode.getPropertyValue("actionState"));
        }
        if (null != connectivityServiceNode.getPropertyValue("adminStatus")) {
            connectivityService.setAdminStatus(connectivityServiceNode.getPropertyValue("adminStatus"));
        }
        connectivityService.setDescription(serviceDescription);
        if (null != connectivityServiceNode.getPropertyValue("location")) {
            connectivityService.setLocation(connectivityServiceNode.getPropertyValue("location"));
        }
        if (null != connectivityServiceNode.getPropertyValue("operStatus")) {
            connectivityService.setOperStatus(connectivityServiceNode.getPropertyValue("operStatus"));
        }
        if (null != connectivityServiceNode.getPropertyValue("ownerID")) {
            connectivityService.setOwnerID(connectivityServiceNode.getPropertyValue("ownerID"));
        }
        if (null != connectivityServiceNode.getPropertyValue("statusReason")) {
            connectivityService.setStatusReason(connectivityServiceNode.getPropertyValue("statusReason"));
        }
        if (null != connectivityServiceNode.getPropertyValue("version")) {
            connectivityService.setVersion(connectivityServiceNode.getPropertyValue("version"));
        }

        CreateConnectivityServiceResponse createConnectivityService = defaultMssApiClient
                .createConnectivityService(connectivityService);
        CreateConnectivityServiceResponseSample createConnectivityServiceResponseSample = createConnectivityService
                .getObjects().get(0);
        String createdNsId = createConnectivityServiceResponseSample.getId();
        log.info("Created Connectivity Service ID is " + createdNsId);
        log.info(
                String.format("Created connectivity service:%n%s", createConnectivityServiceResponseSample.toString()));
        // put the id into the params in case we are passing on to another wfl
        // eg deployCreated as
        // part of deploy
        params.put(Constants.LCM_NBI_SERVICE_ID, createdNsId);

        executeWorkplan(csarId, apiOperation, templateInstance, createdNsId);

        HashMap<String, Object> responseMap = new HashMap<>();
        responseMap.put(Constants.LCM_NBI_SERVICE_ID, createdNsId);
        return responseMap;
    }

    protected void executeWorkplan(String csarId, String apiOperation, Instance templateInstance, String nsId)
            throws SouthboundExecutionException {
        this.getLogger().fine("no file store action required for create workflow");

        WorkPlan workPlan = this.decomposer.decompose(templateInstance, apiOperation, csarId);
        WorkPlanExecutionResult dispatchResult = dispatcher.dispatch(workPlan, WorkPlanExecutionStrategy.FAIL_FAST);
        if (!dispatchResult.getOverallResult()) {
            throw new SouthboundExecutionException("Execution failed in the SBI:\n" + dispatchResult.toString());
        }
    }

    @Override
    protected Logger getLogger() {
        return log;
    }

    @Override
    public String getWorkflowId() {

        return GenericWorkflowId.CREATE.toString();
    }

}
