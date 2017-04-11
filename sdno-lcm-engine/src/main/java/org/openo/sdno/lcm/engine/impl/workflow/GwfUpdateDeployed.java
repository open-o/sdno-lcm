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
import org.openo.sdno.lcm.exception.LcmInternalException;
import org.openo.sdno.lcm.templatemodel.service.Instance;
import org.openo.sdno.lcm.util.Constants;
import org.springframework.stereotype.Component;

@Component
public class GwfUpdateDeployed extends AtomicWorkflow {

    private static final Logger log = Logger.getLogger("GwfUpdateDeployed");

    /*
     * (non-Javadoc)
     * @see org.openo.sdno.lcm.engine.GenericWorkflow#execute(java.util.Map)
     */
    @Override
    public Map<String, Object> execute(Map<String, Object> params) {
        throw new LcmInternalException("WFL NOT IMPLEMENTED!");
    }

    /*
     * (non-Javadoc)
     * @see org.openo.sdno.lcm.engine.RegisterWorkflow#getWorkflowId()
     */
    @Override
    public String getWorkflowId() {

        return GenericWorkflowId.UPDATEDEPLOYED.toString();
    }

    @Override
    protected Logger getLogger() {
        return log;
    }

    @Override
    protected String getNewState() {
        return Constants.LCM_LIFECYCLESTATE_DEPLOYED;
    }

    @Override
    protected void updateTemplateInstancePreDispatch(Instance templateInstance) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void updateTemplateInstancePostDispatch(Instance templateInstance) {
        // TODO Auto-generated method stub
    }

}
