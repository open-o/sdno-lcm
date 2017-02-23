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

package org.openo.sdno.lcm.templatemodel.service;

import org.openo.sdno.lcm.exception.LcmInternalException;

public class Dependency {

    private static final String FAILED_TO_PARSE_ERR = "Failed to parse dependency for string ";

    private String type;

    private String operation;

    private static final String SPLITTER = "/";

    /**
     * Constructor
     * 
     * @param depString eg "sdno.node.Connection.Site/deploy"
     */
    public Dependency(String depString) {

        try {
            String[] split = depString.split(SPLITTER);
            type = split[0];
            operation = split[1];
            if(null == type || type.isEmpty() || null == operation || operation.isEmpty()) {
                throw new LcmInternalException(FAILED_TO_PARSE_ERR + depString);
            }
        } catch(Exception e) {
            throw new LcmInternalException(FAILED_TO_PARSE_ERR + depString);
        }
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @return the operation
     */
    public String getOperation() {
        return operation;
    }

}
