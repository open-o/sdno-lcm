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

package org.openo.sdno.lcm.model.workplan;

/**
 * This class defines the strategy adopted by the dispatcher when one node fails.
 * In the future, we need investigate whether to add retry logic here.
 */
public enum WorkPlanExecutionStrategy {
    FAIL_FAST,  //stop and return immediately if one work item fails. The remaining work items won't be executed.
    CONTINUE_EXECUTE  //continue to execute the following work items.
}
