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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"name", "source_requirement_index", "target_node_id", "target_capability_name"})
public class Relationship {

    @JsonProperty("name")
    private String name;

    @JsonProperty("source_requirement_index")
    private Integer sourceRequirementIndex;

    @JsonProperty("target_node_id")
    private String targetNodeId;

    @JsonProperty("target_capability_name")
    private String targetCapabilityName;

    private final static long serialVersionUID = 5039803224619352269L;

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("source_requirement_index")
    public Integer getSourceRequirementIndex() {
        return sourceRequirementIndex;
    }

    @JsonProperty("source_requirement_index")
    public void setSourceRequirementIndex(Integer sourceRequirementIndex) {
        this.sourceRequirementIndex = sourceRequirementIndex;
    }

    @JsonProperty("target_node_id")
    public String getTargetNodeId() {
        return targetNodeId;
    }

    @JsonProperty("target_node_id")
    public void setTargetNodeId(String targetNodeId) {
        this.targetNodeId = targetNodeId;
    }

    @JsonProperty("target_capability_name")
    public String getTargetCapabilityName() {
        return targetCapabilityName;
    }

    @JsonProperty("target_capability_name")
    public void setTargetCapabilityName(String targetCapabilityName) {
        this.targetCapabilityName = targetCapabilityName;
    }

}
