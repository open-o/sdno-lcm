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

package org.openo.sdno.lcm.filestorehandler;

import org.openo.sdno.lcm.templatemodel.service.Instance;

/**
 *
 */
public interface FileStoreHandler {

    /**
     * Stores JSON representation of an Instance to local temporary file.
     * 
     * @param instance
     *            the Instance to store
     * @param fileId
     *            the prefix of the filename
     */
    void storeInstanceToFile(Instance instance, String fileId);

    /**
     * Gets an Instance from a stored JSON file.
     * 
     * @param instanceId
     *            the prefix of the file storing the Instance
     * @return the retrieved Instance
     */
    Instance getInstanceFromFile(String fileId);
}
