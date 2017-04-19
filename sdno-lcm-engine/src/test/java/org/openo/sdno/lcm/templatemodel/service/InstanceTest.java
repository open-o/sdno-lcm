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

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openo.sdno.lcm.templateinstanceparser.impl.TemplateInstanceParserImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Test(groups = { "sdno-lcm-unit" })
public class InstanceTest {

    Instance instance;

    @BeforeMethod
    public void beforeMethod() throws Exception {
        TemplateInstanceParserImpl templateInstanceParserImpl = new TemplateInstanceParserImpl();
        instance = templateInstanceParserImpl.parse(FileUtils.readFileToString(
                FileUtils.getFile("src", "test", "resources", "instance.json"), Charset.defaultCharset()));
    }

    @Test
    public void replacePropertyValueInAllNodes() {

        int expectedChecks = 2;
        int performedChecks = 0;

        String propertyName = "operStatus";

        List<Node> nodes = instance.getNodes();
        for (Node node : nodes) {

            if (node.getId().startsWith("site_") || node.getId().startsWith("vlan_")) {
                performedChecks++;
                Assert.assertEquals(node.getPropertyValue(propertyName), "none", "failed the precondition check");
            }
        }
        Assert.assertEquals(performedChecks, expectedChecks,
                "Did not check the expected number of nodes for precondition");
        expectedChecks = 2;
        performedChecks = 0;
        String newValue = "IH-AH!";

        instance.replacePropertyValueInAllNodes(propertyName, newValue);

        for (Node node : nodes) {

            if (node.getId().startsWith("site_") || node.getId().startsWith("vlan_")) {
                performedChecks++;
                Assert.assertEquals(node.getPropertyValue(propertyName), newValue);
            }
        }
        Assert.assertEquals(performedChecks, expectedChecks, "Did not check the expected number of nodes");
    }

    @Test
    public void fillIdPropertiesFromInstanceTest() throws JsonProcessingException, IOException {

        ObjectMapper mapper = new ObjectMapper();

        Node aNode = new Node();
        aNode.setProperties(mapper.readTree("{}"));
        aNode.setTemplateName("itsanodejim");
        aNode.setProperty("id", "bones", "string");
        Instance anInstance = new Instance();
        List<Node> nodes = new ArrayList<>();
        nodes.add(aNode);
        anInstance.setNodes(nodes);
        Assert.assertEquals(aNode.getPropertyValue("id"), "bones");

        Instance idInstance = new Instance();
        Node idNode = new Node();
        idNode.setProperties(mapper.readTree("{}"));
        idNode.setTemplateName("itsanodejim");
        idNode.setProperty("id", "spock", "string");
        List<Node> idNodes = new ArrayList<>();
        idNodes.add(idNode);
        idInstance.setNodes(idNodes);

        anInstance.fillIdPropertiesFromInstance(idInstance);
        Assert.assertEquals(aNode.getPropertyValue("id"), "spock");
    }
}
