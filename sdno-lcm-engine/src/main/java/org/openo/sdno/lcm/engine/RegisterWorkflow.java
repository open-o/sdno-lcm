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

package org.openo.sdno.lcm.engine;

/**
 * Components implementing this can register Workflows to the WorkflowRegistry. Workflows could be
 * Generic Java or custom.
 */
public interface RegisterWorkflow {

    /**
     * Return the Workflow that can be registered by a callback from the WorkflowRegistry.
     * 
     * @return the List of registered Workflow
     */
    public Workflow getWorkflowToRegister();

    /**
     * Get the id for the registered workflow.
     * 
     * @return the id
     */
    public String getWorkflowId();

}
