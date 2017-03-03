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
import org.openo.sdno.lcm.restclient.serviceinventory.model.ConnectivityService;
import org.openo.sdno.lcm.restclient.serviceinventory.model.CreateConnectivityServiceResponse;
import org.openo.sdno.lcm.restclient.serviceinventory.model.CreateConnectivityServiceResponseSample;
import org.openo.sdno.lcm.templatemodel.service.Instance;
import org.openo.sdno.lcm.templatemodel.service.Node;
import org.openo.sdno.lcm.util.Constants;
import org.springframework.stereotype.Component;

@Component
public class GwfCreate extends GenericWorkflowImpl {

    private static final Logger log = Logger.getLogger("GenericWorkflowCreate");

    /*
     * (non-Javadoc)
     * @see org.openo.sdno.lcm.engine.Workflow#execute(java.util.Map)
     */
    @Override
    public Map<String, Object> execute(Map<String, Object> params) {

        log.info("Execute GwfCreate workflow");

        String csarId = (String)super.getParam(Constants.LCM_NBI_CSAR_ID, params);
        String apiOperation = (String)super.getParam(Constants.LCM_NBI_API_OPERATION, params);
        String serviceTemplateId = (String)super.getParam(Constants.LCM_NBI_TEMPLATE_ID, params);
        Instance templateInstance = (Instance)super.getParam(Constants.LCM_TEMPLATE_INSTANCE, params);
        Node connectivityServiceNode = templateInstance.getRootNode();

        // create the Connectivity Service in DB
        ConnectivityService connectivityService = new ConnectivityService();
        connectivityService.setLifecycleState("created");
        connectivityService.setTemplateId(serviceTemplateId);
        connectivityService.setId(connectivityServiceNode.getPropertyValue("id"));
        connectivityService.setName(connectivityServiceNode.getPropertyValue("name"));
        connectivityService.setActionState(connectivityServiceNode.getPropertyValue("actionState"));
        connectivityService.setAdminStatus(connectivityServiceNode.getPropertyValue("adminStatus"));
        connectivityService.setDescription(connectivityServiceNode.getPropertyValue("description"));
        connectivityService.setLocation(connectivityServiceNode.getPropertyValue("location"));
        connectivityService.setOperStatus(connectivityServiceNode.getPropertyValue("operStatus"));
        connectivityService.setOwnerID(connectivityServiceNode.getPropertyValue("ownerID"));
        connectivityService.setStatusReason(connectivityServiceNode.getPropertyValue("statusReason"));
        connectivityService.setTenantID(connectivityServiceNode.getPropertyValue("tenantID"));
        connectivityService.setVersion(connectivityServiceNode.getPropertyValue("version"));

        CreateConnectivityServiceResponse createConnectivityService =
                defaultMssApiClient.createConnectivityService(connectivityService);
        CreateConnectivityServiceResponseSample createConnectivityServiceResponseSample =
                createConnectivityService.getObjects().get(0);
        String createdNsId = createConnectivityServiceResponseSample.getId();
        log.info("Created Connectivity Service ID is " + createdNsId);
        // put the id into the params in case we are passing on to another wfl eg deployCreated as
        // part of deploy
        params.put(Constants.LCM_NBI_SERVICE_ID, createdNsId);

//        executeWorkplan(csarId, apiOperation, templateInstance);
        
        HashMap<String, Object> responseMap = new HashMap<String, Object>();
        responseMap.put(Constants.LCM_NBI_SERVICE_ID, createdNsId);
        return responseMap;
    }

    /*
     * (non-Javadoc)
     * @see org.openo.sdno.lcm.engine.RegisterWorkflow#getWorkflowId()
     */
    @Override
    public String getWorkflowId() {
        return GenericWorkflowId.CREATE.toString();
    }

}
