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

package org.openo.sdno.lcm.decomposer;

import java.util.List;

//import org.openo.sdno.lcm.templatemodel.service.Instance;
//import org.openo.sdno.lcm.templatemodel.service.Node;

public interface Decomposer {

    /**
     * Decompose the Instance of service template, ie determine the correct order to visit the Nodes
     * for the operation.
     * Notes:
     * We receive the Instance with all input values already included.
     * We don't address any parallel processing.
     * We assume the operation is the same (eg 'deploy') for all nodes?
     * We don't include swagger for SBIs - assume this is passed directly to Dispatcher.
     * We don't do any traffic engineering yet.
     * We don't allow to hook custom behaviour into the generic workflows yet.
     * 
     * @param serviceTemplateInstance
     * @param operation
     * @return the list of Node in the order that they need to be visited to perform the given
     *         operation
     */
    List<Object> decompose(Object serviceTemplateInstance, String operation);

}
