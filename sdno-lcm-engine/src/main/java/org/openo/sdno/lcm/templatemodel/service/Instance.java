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
import java.util.logging.Logger;

import org.openo.sdno.lcm.exception.LcmInternalException;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonNode;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "description", "metadata", "nodes", "inputs" })
public class Instance {

    private static final String UNABLE_TO_DETERMINE_ROOT_ERR = "Unable to determine root Node";

    private static final String UNABLE_TO_FIND_NODE_ERR = "Unable to find Node with ID ";

    private final Logger log = Logger.getLogger("Instance");

    private JsonNode inputsJson;

    @JsonProperty("description")
    private String description;

    @JsonProperty("metadata")
    private Metadata metadata;

    @JsonProperty("nodes")
    private List<Node> nodes = new ArrayList<Node>();

    private final static long serialVersionUID = 6264243172150130363L;

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("metadata")
    public Metadata getMetadata() {
        return metadata;
    }

    @JsonProperty("metadata")
    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    @JsonProperty("nodes")
    public List<Node> getNodes() {
        return nodes;
    }

    @JsonProperty("nodes")
    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public JsonNode getInputs() {
        return inputsJson;
    }

    public void setInputs(JsonNode inputs) {
        this.inputsJson = inputs;
    }

    /**
     * Get the root Node of the hierarchy in this instance, identified by the
     * type prefix sdno.node.ConnectivityService.
     * 
     * @throws LcmInternalException
     *             if the root cannot be determined
     * @return the root Node
     */
    public Node getRootNode() {

        for (Node n : this.getNodes()) {

            if (n.isRootNode()) {
                return n;
            }
        }
        log.severe(UNABLE_TO_DETERMINE_ROOT_ERR);
        throw new LcmInternalException(UNABLE_TO_DETERMINE_ROOT_ERR);
    }

    /**
     * Get the node with the ID provided.
     * 
     * @param nodeId
     *            the ID
     * @throws LcmInternalException
     *             if no matching Node is found
     * @return the Node
     */
    public Node getNode(String nodeId) {

        for (Node n : this.getNodes()) {

            if (n.getId().equals(nodeId)) {
                return n;
            }
        }
        log.severe(UNABLE_TO_FIND_NODE_ERR + nodeId);
        throw new LcmInternalException(UNABLE_TO_FIND_NODE_ERR + nodeId);
    }

    /**
     * Replace a property value if such a property is present in any Node in the
     * Instance
     * 
     * @param propertyName
     *            the name of the property to modify
     * @param newValue
     *            the value to assign to the named property
     */
    public void replacePropertyValueInAllNodes(String propertyName, String newValue) {

        for (Node node : this.nodes) {
            if (node.hasProperty(propertyName)) {
                node.replacePropertyValue(propertyName, newValue);
            }
        }
    }

    /**
     * @return the state table JsonNode
     */
    public JsonNode getStateTableDefinition() {
        Node connectivityServiceNode = this.getRootNode();
        return connectivityServiceNode.getPropertyJsonNode("state_machine");
    }

    @Override
    public String toString() {
        return String.format("Instance [log=%s, inputsJson=%s, description=%s, metadata=%s, nodes=%s]", log, inputsJson,
                description, metadata, nodes.toString());
    }

    /**
     * Get the value of a given field in a node with a given template_name
     * 
     * @param templateName
     * @param attributeName
     * @return
     */
    public String getAttributeValue(String templateName, String attributeName) {

        Node node = this.getNodeByTemplateName(templateName);
        if (null == node) {
            throw new LcmInternalException(String.format("Failed to find a node with template name %s", templateName));
        }
        String propertyValue = node.getPropertyValue(attributeName);
        if (null == propertyValue) {
            throw new LcmInternalException(
                    String.format("Failed to find a value for attribute %s in node %s", attributeName, node.getId()));
        }
        return propertyValue;
    }

    private Node getNodeByTemplateName(String templateName) {

        for (Node n : this.getNodes()) {

            if (n.getTemplateName().equals(templateName)) {
                return n;
            }
        }
        throw new LcmInternalException("Unable to find node with template_name " + templateName);
    }

    public void fillNodeReferences() {

        for (Node node : this.getNodes()) {

            node.fillNodeReferences(this);
        }
    }

}
