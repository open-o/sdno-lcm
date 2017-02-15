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

package org.openo.sdno.lcm.templatemodel.statetable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"newState", "transitionWorkflow", "currentState", "apiOperation"})
public class Row {

    @JsonProperty("newState")
    private String newState;

    @JsonProperty("transitionWorkflow")
    private String transitionWorkflow;

    @JsonProperty("currentState")
    private String currentState;

    @JsonProperty("apiOperation")
    private String apiOperation;

    private final static long serialVersionUID = -5855374239203582878L;

    @JsonProperty("newState")
    public String getNewState() {
        return newState;
    }

    @JsonProperty("newState")
    public void setNewState(String newState) {
        this.newState = newState;
    }

    @JsonProperty("transitionWorkflow")
    public String getTransitionWorkflow() {
        return transitionWorkflow;
    }

    @JsonProperty("transitionWorkflow")
    public void setTransitionWorkflow(String transitionWorkflow) {
        this.transitionWorkflow = transitionWorkflow;
    }

    @JsonProperty("currentState")
    public String getCurrentState() {
        return currentState;
    }

    @JsonProperty("currentState")
    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    @JsonProperty("apiOperation")
    public String getApiOperation() {
        return apiOperation;
    }

    @JsonProperty("apiOperation")
    public void setApiOperation(String apiOperation) {
        this.apiOperation = apiOperation;
    }

}
