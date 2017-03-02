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

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.openo.sdno.lcm.csarhandler.CsarHandler;
import org.openo.sdno.lcm.engine.GenericWorkflowId;
import org.openo.sdno.lcm.restclient.serviceinventory.model.ConnectivityService;
import org.openo.sdno.lcm.templatemodel.service.Instance;
import org.openo.sdno.lcm.templatemodel.service.Node;
import org.openo.sdno.lcm.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GwfCreate extends GenericWorkflowImpl {

    private static final Logger log = Logger.getLogger("GenericWorkflowCreate");

    private CsarHandler csarHandler;

    /*
     * (non-Javadoc)
     * @see org.openo.sdno.lcm.engine.Workflow#execute(java.util.Map)
     */
    @Override
    public Map<String, Object> execute(Map<String, Object> params) {

        log.fine("Execute GwfCreate workflow");

        String csarName = (String)params.get(Constants.LCM_NBI_CSAR_NAME);
        if(csarName == null || csarName.isEmpty()) {

            throw new InvalidParameterException("csarName may not be empty or null");
        }
        String csarId = csarHandler.getCsarByName(csarName).getCsarId();

        // get raw template instance from catalog
        String serviceTemplate = modelResourceApiClient.getServiceTemplateRawData(csarId);
        Instance templateInstance = templateInstanceParser.parse(serviceTemplate);
        Node connectivityServiceNode = templateInstance.getRootNode();

        // create the Connectivity Service in DB
        ConnectivityService connectivityService = new ConnectivityService();
        connectivityService.setId(connectivityServiceNode.getPropertyValue("id"));
        connectivityService.setName(connectivityServiceNode.getPropertyValue("name"));
        connectivityService.setActionState(connectivityServiceNode.getPropertyValue("actionState"));
        connectivityService.setAdminStatus(connectivityServiceNode.getPropertyValue("adminStatus"));
        connectivityService.setDescription(connectivityServiceNode.getPropertyValue("description"));
        connectivityService.setLocation(connectivityServiceNode.getPropertyValue("location"));
        connectivityService.setOperStatus(connectivityServiceNode.getPropertyValue("operStatus"));
        connectivityService.setOwnerID(connectivityServiceNode.getPropertyValue("ownerID"));
        connectivityService.setStatusReason(connectivityServiceNode.getPropertyValue("statusReason"));
        connectivityService.setTemplateId(connectivityServiceNode.getTemplateName()); // TODO is
                                                                                      // this right?
        connectivityService.setTenantID(connectivityServiceNode.getPropertyValue("tenantID"));
        connectivityService.setVersion(connectivityServiceNode.getPropertyValue("version"));
        connectivityService.setLifecycleState("created");
        defaultMssApiClient.createConnectivityService(connectivityService);

        return new HashMap<String, Object>();
    }

    /*
     * (non-Javadoc)
     * @see org.openo.sdno.lcm.engine.RegisterWorkflow#getWorkflowId()
     */
    @Override
    public String getWorkflowId() {
        return GenericWorkflowId.CREATE.toString();
    }

    @Autowired
    public void setCsarHandler(CsarHandler csarHandler) {
        this.csarHandler = csarHandler;
    }

}
