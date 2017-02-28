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

package org.openo.sdno.lcm.decomposer.impl;

import java.util.List;
import java.util.Stack;
import java.util.UUID;
import java.util.logging.Logger;

import org.openo.sdno.lcm.decomposer.Decomposer;
import org.openo.sdno.lcm.model.workplan.WorkItem;
import org.openo.sdno.lcm.model.workplan.WorkPlan;
import org.openo.sdno.lcm.templatemodel.csar.Csar;
import org.openo.sdno.lcm.templatemodel.service.Dependency;
import org.openo.sdno.lcm.templatemodel.service.Instance;
import org.openo.sdno.lcm.templatemodel.service.Node;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;

import io.swagger.models.HttpMethod;
import io.swagger.models.Swagger;

/**
 *
 */
@Component
public class DecomposerImpl implements Decomposer {

    private final Logger log = Logger.getLogger("DecomposerImpl");

    /*
     * (non-Javadoc)
     * @see
     * org.openo.sdno.lcm.decomposer.Decomposer#decompose(org.openo.sdno.lcm.templatemodel.service.
     * Instance, java.lang.String, org.openo.sdno.lcm.templatemodel.csar.Csar)
     */
    @Override
    public WorkPlan decompose(final Instance serviceTemplateInstance, final String operation, final Csar csar) {

        // PSEUDO:
        // TODO
        // retrieve resources from BRS and fill required data as defined by the template
        // TODO

        WorkPlan workplan = new WorkPlan();
        // determine node order (required input: instance, operation)
        // determine root node (find the Node that has type_name prefix
        // sdno.node.ConnectivityService)
        Node rootNode = serviceTemplateInstance.getRootNode();
        // starting from the root node, proceed in a depth-first traversal of the Node tree:
        Stack<Node> nodeStack = new Stack<Node>();
        nodeStack.push(rootNode);
        // while stack not empty:
        while(!nodeStack.isEmpty()) {
            // pop the top Node from the stack
            Node node = nodeStack.pop();
            String nodeType = node.getTypeName();
            // get the dependencies for the Node for the operation (assume operation is the
            // same for every node)
            List<Dependency> nodeDependencies = node.getDependencies(operation);
            if(nodeDependencies.isEmpty()) {
                // if there are no dependencies, add the Node to the WorkItem list as it is a leaf
                // Node
                node.setExamined();
                this.addWorkItem(workplan, node);
            } else if(node.isExamined()) {
                // or if there are dependencies but we have added them to the Node stack already add
                // the Node to the WorkItem list
                this.addWorkItem(workplan, node);
            } else {
                // else mark the Node as examined and add its related nodes (maybe including
                // itself) to the stack
                node.setExamined();
                // for each dependency, examined in reverse order we want to pop them:
                for(int d = nodeDependencies.size() - 1; d >= 0; d--) {
                    Dependency nodeDependency = nodeDependencies.get(d);
                    // get all the related Nodes that are identified in the relationships of
                    // this Node and are the same type as the dependency
                    List<Node> relatedNodesOfType =
                            node.getRelatedNodesOfType(nodeDependency.getType(), serviceTemplateInstance);
                    // need to add the Node as its own child as it may be listed in the
                    // dependencies but will not be in the relationships
                    if(nodeDependency.getType().equals(nodeType)) {
                        relatedNodesOfType.add(node);
                    }
                    // for each related Node, add to stack in reverse order we want to pop them
                    for(int n = relatedNodesOfType.size() - 1; n >= 0; n--) {
                        Node relatedNode = relatedNodesOfType.get(n);
                        nodeStack.push(relatedNode);
                    }
                }
            }
        }

        return workplan;
    }

    private void decorateNode(final Node node) {

        // generate UUIDs for Nodes that will be created by atomic services
        if(node.isConnectionNode()) {
            String uuid = UUID.randomUUID().toString();
            node.setProperty("id", uuid, "string");
            log.info(String.format("Generated random ID property %s for Node %s", uuid, node.getId()));
        }
        // attach artifacts to WorkItems
        //// swagger spec
        //// TODO
        //// mapper spec
        //// TODO
    }

    private void addWorkItem(WorkPlan workplan, final Node node) {

        // add required extras to the node itself eg ID property
        this.decorateNode(node);
        // clean examined flag from all Nodes that are added to WorkPlan
        node.clearExamined();
        Swagger swaggerSpec = new Swagger();
        JsonNode mapperSpec = node.getProperties();
        String apiUrl = "/blah/v1";
        HttpMethod method = HttpMethod.GET;
        workplan.insert(new WorkItem(node, swaggerSpec, mapperSpec, apiUrl, method));
        log.info(node.getTypeName() + " " + node.getId());
    }
}
