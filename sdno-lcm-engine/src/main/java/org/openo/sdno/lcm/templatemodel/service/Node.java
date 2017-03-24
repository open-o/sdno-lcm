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

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.openo.sdno.lcm.exception.LcmInternalException;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.swagger.models.HttpMethod;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"id", "type_name", "template_name", "properties", "interfaces", "capabilities", "relationships"})
public class Node {

    private static final String SDNO_NODE_CONNECTION_ENDPOINT = "sdno.node.ConnectionEndPoint.";

    private static final String SDNO_NODE_CONNECTION = "sdno.node.Connection.";

    private final Logger log = Logger.getLogger("Node");

    private JsonNode propertiesJson;

    @JsonProperty("id")
    private String id;

    @JsonProperty("type_name")
    private String typeName;

    @JsonProperty("template_name")
    private String templateName;

    @JsonProperty("artifacts")
    private List<Artifact> artifacts = new ArrayList<Artifact>();

    @JsonProperty("interfaces")
    private List<Interface> interfaces = new ArrayList<Interface>();

    @JsonProperty("capabilities")
    private List<Capability> capabilities = new ArrayList<Capability>();

    @JsonProperty("relationships")
    private List<Relationship> relationships = new ArrayList<Relationship>();

    /**
     * Flag to set in the decomposer when a Node has been examined during tree traversal.
     */
    private boolean examined = false;

    /**
     * Set the examined flag to true.
     */
    public void setExamined() {

        if(examined) {
            throw new LcmInternalException("Tried to set an examined Node to examined again");
        }
        examined = true;
    }

    /**
     * @return true if the examined flag is set to true
     */
    public boolean isExamined() {

        return examined;
    }

    /**
     * Set the examined flag to false.
     */
    public void clearExamined() {

        if(!examined) {
            throw new LcmInternalException("Tried to clear a Node that was not examined");
        }
        examined = false;
    }

    /**
     * Get the dependencies of this Node for the provided operation name
     * 
     * @param operationName the name of the operation
     * @return List of Dependency
     */
    public List<Dependency> getDependencies(final String operationName) {

        return this.getOperation(operationName).getDependencyList();
    }

    /**
     * Get the Operation identified by the provided name.
     * 
     * @param operationName
     * @return the Operation ot null if not found
     */
    public Operation getOperation(final String operationName) {

        List<Operation> operations = interfaces.get(1).getOperations();
        for(Operation operation : operations) {

            if(operation.getName().equals(operationName)) {
                return operation;
            }
        }
        log.warning("Failed to get operation with name " + operationName);
        return null;
    }

    /**
     * Check if this Node defines an implementation for the operation
     * 
     * @param operationName the name of the operation
     * @return true it the implementation is present for the operation; false if operation doesn't
     *         exist or there is no implementation in it
     */
    public boolean hasOperationImplementation(final String operationName) {

        Operation operation = this.getOperation(operationName);
        if(null == operation) {

            return false;
        } else {

            return null != operation.getImplementation();
        }
    }

    /**
     * Get an artifact element of the given name
     * 
     * @param artifactName the name
     * @return the artifact
     * @throws LcmInternalException if artifact not found
     */
    public Artifact getArtifact(final String artifactName) {

        List<Artifact> artifacts = this.getArtifacts();
        for(Artifact artifact : artifacts) {

            if(artifact.getName().equals(artifactName)) {
                return artifact;
            }
        }
        String errStr = String.format("Failed to get artifact with name %s in node %s", artifactName, this.getId());
        log.severe(errStr);
        throw new LcmInternalException(errStr);
    }

    /**
     * Get all the child Nodes that are identified in the relationships of this Node and are the
     * same type as the dependency prefix eg 'sdno.node.Connection.VpcSubnet'
     * 
     * @param type the type of Nodes to get
     * @param instance the instance reference to get the Nodes
     * @return the list of related Nodes of the specified type
     */
    public List<Node> getRelatedNodesOfType(final String type, final Instance instance) {

        List<Node> childrenOfType = new ArrayList<>();
        for(Relationship relationship : this.getRelationships()) {

            Node relatedNode = instance.getNode(relationship.getTargetNodeId());
            if(relatedNode.getTypeName().equals(type)) {

                childrenOfType.add(relatedNode);
            }
        }
        return childrenOfType;
    }

    /**
     * Check if this Node is a connection Node, ie if it's type begins with 'sdno.node.Connection.'
     * or 'sdno.node.ConnectionEndpoint.'
     * 
     * @throws LcmInternalException if the typeName of this Node is null or empty
     * @return true if this is a connection Node
     */
    public boolean isConnectionNode() {
        final String typeName = this.getTypeName();
        if(null == typeName || typeName.isEmpty()) {
            String err = String.format(
                    "Failed to determine if Node %s is a connection Node because it's typeName is null or empty",
                    this.getId());
            log.severe(err);
            throw new LcmInternalException(err);
        }
        return typeName.startsWith(SDNO_NODE_CONNECTION) || typeName.startsWith(SDNO_NODE_CONNECTION_ENDPOINT);
    }

    /**
     * Get the value for a property within the Properties JsonNode
     * 
     * @param propertyName the name of the property to get
     * @return the value of the property as a String
     */
    public String getPropertyValue(final String propertyName) {

        JsonNode propertyNode = getPropertyJsonNode(propertyName);
        if(null != propertyNode) {
            JsonNode jsonNode = propertyNode.get("value");
            if(null != jsonNode && !jsonNode.isNull()) {

                if(jsonNode.isLong()) {
                    return Long.toString(jsonNode.asLong());
                }
                if(jsonNode.isFloatingPointNumber()) {
                    return Double.toString(jsonNode.asDouble());
                }
                return jsonNode.asText();
            }
        }
        return null;
    }

    /**
     * Get the JsonNode for a property within the Properties JsonNode
     * 
     * @param propertyName the name of the property to get
     * @return the whole JsonNode for the property, not just the value
     */
    public JsonNode getPropertyJsonNode(final String propertyName) {

        // TODO check error handling logic
        JsonNode propertiesNode = this.getProperties();
        JsonNode propertyNode = propertiesNode.get(propertyName);
        return propertyNode;
    }

    /**
     * Returns true if the named property exists in the Node.
     * 
     * @param propertyName
     * @return
     */
    public boolean hasProperty(final String propertyName) {

        JsonNode propertyJsonNode = getPropertyJsonNode(propertyName);
        if(null == propertyJsonNode) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Add a property to the Node or overwrite if already present.
     * 
     * @param propertyName the name of the property
     * @param value the value of the property
     * @param typeName the typeName of the property
     */
    public void setProperty(final String propertyName, final String value, final String typeName) {

        try {
            if(propertyName.isEmpty() || value.isEmpty() || typeName.isEmpty()) {

                throw new InvalidParameterException();
            }
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode propertiesNode = this.getProperties();

            String newProperty = String.format("{\"type_name\": \"%s\", \"value\": \"%s\"}", typeName, value);
            JsonNode newPropertyNode = objectMapper.readTree(newProperty);

            ((ObjectNode)propertiesNode).put(propertyName, newPropertyNode);

        } catch(Exception e) {
            throw new LcmInternalException(String.format("Failed to set property: %s, %s, %s due to %s", propertyName,
                    typeName, value, e.getMessage()), e);
        }
    }

    /**
     * Replaces the value of the named property if it exists, throws LcmInternalException if the
     * named property doesn't exist.
     * 
     * @param propertyName the property name
     * @param newValue the value to assign to the named property
     */
    public void replacePropertyValue(final String propertyName, final String newValue) {

        if(!this.hasProperty(propertyName)) {

            throw new LcmInternalException(String
                    .format("can't replace the value of property %s as it doesn't exist in the Node", propertyName));
        }
        String typeName = this.getPropertyTypeName(propertyName);
        this.setProperty(propertyName, newValue, typeName);
        log.info(String.format("Replaced the value of property %s with the new value %s", propertyName, newValue));
    }

    /**
     * Gets the type_name of the named property, or throws LcmInternalException if not found
     * 
     * @param propertyName the property name
     * @return the type_name
     */
    private String getPropertyTypeName(final String propertyName) {

        String theTypeName = null;
        JsonNode propertyNode = getPropertyJsonNode(propertyName);
        if(null != propertyNode) {
            JsonNode jsonNode = propertyNode.get("type_name");
            if(null != jsonNode && !jsonNode.isNull()) {

                theTypeName = jsonNode.asText();
            }
        }
        if(null == theTypeName) {

            throw new LcmInternalException(String.format("failed to get the type_name for property %s", propertyName));
        }
        return theTypeName;
    }

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

    @JsonProperty("artifacts")
    public List<Artifact> getArtifacts() {
        return artifacts;
    }

    public void setArtifacts(List<Artifact> artifacts) {
        this.artifacts = artifacts;
    }

    public HttpMethod getOperationHttpMethod(String operationStr) {
        Operation operation = this.getOperation(operationStr);
        String implStr = "";
        try {
            implStr = operation.getImplementation();
            String[] split = implStr.split(" ");
            return HttpMethod.valueOf(split[0]);

        } catch(Exception ex) {
            String errStr = "Failed to get operation HTTP method from operation " + implStr;
            log.severe(errStr + "\n correct format is <HTTP VERB><space><PATH> eg \""
                    + "POST /openoapi/sdnolocalsite/v1/cloud-cpes\"");
            throw new LcmInternalException(errStr, ex);
        }
    }

    public String getOperationPath(String operationStr) {
        Operation operation = this.getOperation(operationStr);
        String implStr = "";
        try {
            implStr = operation.getImplementation();
            String[] split = implStr.split(" ");
            return split[1];

        } catch(Exception ex) {
            String errStr = "Failed to get operation path from operation " + implStr;
            log.severe(errStr + "\n correct format is <HTTP VERB><space><PATH> eg \""
                    + "POST /openoapi/sdnolocalsite/v1/cloud-cpes\"");
            throw new LcmInternalException(errStr, ex);
        }
    }

    public boolean isRootNode() {
        return getTypeName().startsWith("sdno.node.ConnectivityService");
    }

}
