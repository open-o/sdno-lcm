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
import com.fasterxml.jackson.databind.JsonNode;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"id", "type_name", "template_name", "properties", "interfaces", "capabilities", "relationships"})
public class Node {
    
    private JsonNode propertiesJson;

    @JsonProperty("id")
    private String id;

    @JsonProperty("type_name")
    private String typeName;

    @JsonProperty("template_name")
    private String templateName;

    @JsonProperty("interfaces")
    private List<Interface> interfaces = new ArrayList<Interface>();

    @JsonProperty("capabilities")
    private List<Capability> capabilities = new ArrayList<Capability>();

    @JsonProperty("relationships")
    private List<Relationship> relationships = new ArrayList<Relationship>();
    
    // eg swagger spec - added by decomposer
    private Object artifacts; 

    private final static long serialVersionUID = 2435488133886152314L;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("type_name")
    public String getTypeName() {
        return typeName;
    }

    @JsonProperty("type_name")
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @JsonProperty("template_name")
    public String getTemplateName() {
        return templateName;
    }

    @JsonProperty("template_name")
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    @JsonProperty("interfaces")
    public List<Interface> getInterfaces() {
        return interfaces;
    }

    @JsonProperty("interfaces")
    public void setInterfaces(List<Interface> interfaces) {
        this.interfaces = interfaces;
    }

    @JsonProperty("capabilities")
    public List<Capability> getCapabilities() {
        return capabilities;
    }

    @JsonProperty("capabilities")
    public void setCapabilities(List<Capability> capabilities) {
        this.capabilities = capabilities;
    }

    @JsonProperty("relationships")
    public List<Relationship> getRelationships() {
        return relationships;
    }

    @JsonProperty("relationships")
    public void setRelationships(List<Relationship> relationships) {
        this.relationships = relationships;
    }

    
    public JsonNode getProperties() {
        return propertiesJson;
    }

    
    public void setProperties(JsonNode propertiesJson) {
        this.propertiesJson = propertiesJson;
    }

}
