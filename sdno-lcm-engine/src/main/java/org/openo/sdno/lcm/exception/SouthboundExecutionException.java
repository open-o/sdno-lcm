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

package org.openo.sdno.lcm.exception;


/**
 * Exception indicating a failure to execute some SBI of the LCM
 */
public class SouthboundExecutionException extends RuntimeException {

    /**
     * @param message
     */
    public SouthboundExecutionException(String message) {
        super(message);
    }


    /**
     * @param message
     * @param cause
     */
    public SouthboundExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

}
