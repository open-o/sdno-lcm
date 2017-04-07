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

import java.util.Map;

/**
 * Identifies a component that can be executed to perform a workflow action.
 */
public interface Workflow {

    /**
     * Execute a workflow. The implementation can differ depending on the workflow, but will always
     * include creating or updating the Connectivity Service in the service inventory, decomposing
     * the workflow into an ordered set of actions per node and dispatching messages to perform
     * these actions.
     * 
     * @param params Map<String, Object> of parameters needed to execute the workflow
     * @return Map<String, Object> of return values
     */
    Map<String, Object> execute(Map<String, Object> params);

}
