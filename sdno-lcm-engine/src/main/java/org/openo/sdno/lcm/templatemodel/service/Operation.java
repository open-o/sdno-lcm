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

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"name", "description", "implementation", "dependencies"})
public class Operation {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("implementation")
    private String implementation;

    @JsonProperty("dependencies")
    private List<String> dependencies = new ArrayList<String>();

    private List<Dependency> dependencyList = null;

    private final static long serialVersionUID = 35592049828049386L;

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("implementation")
    public String getImplementation() {
        return implementation;
    }

    @JsonProperty("implementation")
    public void setImplementation(String implementation) {
        this.implementation = implementation;
    }

    @JsonProperty("dependencies")
    public List<String> getDependencies() {
        return dependencies;
    }

    @JsonProperty("dependencies")
    public void setDependencies(List<String> dependencies) {
        dependencyList = null;
        this.dependencies = dependencies;
    }

    /**
     * Get the list of Dependency objects of this Operation
     * 
     * @return List of Dependency
     */
    public List<Dependency> getDependencyList() {

        if(null == dependencyList) {
            dependencyList = new ArrayList<>();
            for(String depStr : this.getDependencies()) {
                dependencyList.add(new Dependency(depStr));
            }
        }
        return dependencyList;
    }

}
