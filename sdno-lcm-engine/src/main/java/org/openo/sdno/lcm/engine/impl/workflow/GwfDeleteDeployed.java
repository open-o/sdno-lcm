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
import org.openo.sdno.lcm.util.Constants;
import org.springframework.stereotype.Component;

/**
 * This workflow corresponds to the following state table row:
 * "apiOperation": "delete",
 * "currentState": "deployed",
 * "newState": "none",
 * "transitionWorkflow": "deleteDeployed"
 */
@Component
public class GwfDeleteDeployed extends CompositeWorkflow {

    private static final Logger log = Logger.getLogger("GwfDeleteDeployed");

    /*
     * (non-Javadoc)
     * @see org.openo.sdno.lcm.engine.GenericWorkflow#execute(java.util.Map)
     */
    @Override
    public Map<String, Object> execute(Map<String, Object> params) {

        log.info("Beginning composite workflow GwfDeleteDeployed");

        log.info("Composite workflow step 1: UNDEPLOY");
        params.put(Constants.LCM_NBI_API_OPERATION, Constants.LCM_NBI_API_OPERATION_UNDEPLOY);
        workflowRegistry.executeWorkflow(GenericWorkflowId.UNDEPLOY.toString(), params);

        log.info("Composite workflow step 2: DELETECREATED");
        params.put(Constants.LCM_NBI_API_OPERATION, Constants.LCM_NBI_API_OPERATION_DELETE);
        return workflowRegistry.executeWorkflow(GenericWorkflowId.DELETECREATED.toString(), params);
    }

    /*
     * (non-Javadoc)
     * @see org.openo.sdno.lcm.engine.RegisterWorkflow#getWorkflowId()
     */
    @Override
    public String getWorkflowId() {

        return GenericWorkflowId.DELETEDEPLOYED.toString();
    }

    @Override
    protected Logger getLogger() {
        return log;
    }

}
