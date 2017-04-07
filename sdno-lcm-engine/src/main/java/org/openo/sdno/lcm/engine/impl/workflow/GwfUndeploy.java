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

import java.util.logging.Logger;

import org.openo.sdno.lcm.engine.GenericWorkflowId;
import org.openo.sdno.lcm.templatemodel.service.Instance;
import org.openo.sdno.lcm.util.Constants;
import org.springframework.stereotype.Component;

/**
 * This workflow corresponds to the following state table row:
 * "apiOperation": "undeploy",
 * "currentState": "deployed",
 * "newState": "created",
 * "transitionWorkflow": "undeploy"
 */
@Component
public class GwfUndeploy extends AtomicWorkflow {

    private static final Logger log = Logger.getLogger("GwfUndeploy");

    /*
     * (non-Javadoc)
     * @see
     * org.openo.sdno.lcm.engine.impl.workflow.GenericWorkflowImpl#updateTemplateInstance(org.openo.
     * sdno.lcm.templatemodel.service.Instance, java.lang.String)
     */
    protected void updateTemplateInstance(Instance templateInstance, String serviceCreateTime) {
        // update the nodes with correct values for this workflow
        templateInstance.replacePropertyValueInAllNodes(Constants.LCM_ADMINSTATUS,
                Constants.LCM_ADMINISTRATIONSTATE_NONE);
        templateInstance.replacePropertyValueInAllNodes(Constants.LCM_ACTIONSTATE,
                Constants.LCM_ACTIONSTATE_UNDEPLOYING);
    }

    /*
     * (non-Javadoc)
     * @see org.openo.sdno.lcm.engine.impl.workflow.GenericWorkflowImpl#getNewState()
     */
    protected String getNewState() {

        return Constants.LCM_LIFECYCLESTATE_CREATED;
    }

    /*
     * (non-Javadoc)
     * @see org.openo.sdno.lcm.engine.RegisterWorkflow#getWorkflowId()
     */
    @Override
    public String getWorkflowId() {

        return GenericWorkflowId.UNDEPLOY.toString();
    }

    @Override
    public Logger getLogger() {

        return log;
    }

}
