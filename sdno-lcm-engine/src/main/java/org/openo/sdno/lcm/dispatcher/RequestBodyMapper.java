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

package org.openo.sdno.lcm.dispatcher;

import org.openo.sdno.lcm.workplan.WorkItem;
import com.fasterxml.jackson.databind.JsonNode;


public interface RequestBodyMapper {

    /**
     * Create the body of Http Request needed to fullfil a task (WorkItem) in the workplan.
     * Notes:
     * WorkItem contains node instance (properties), swagger specification, and mapper
     * specification that are needed to map node properties to Http Request body.
     * 
     * @param workItem the WorkItem to be executed
     * @return JsonNode whose serialization will be used as the body of Http Request
     *         that fullfils the task.
     */
    JsonNode map(final WorkItem workItem);
}
