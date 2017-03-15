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

package org.openo.sdno.lcm.templateinstanceparser;

import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openo.sdno.lcm.templateinstanceparser.impl.TemplateInstanceParserImpl;
import org.openo.sdno.lcm.templatemodel.service.Capability;
import org.openo.sdno.lcm.templatemodel.service.Dependency;
import org.openo.sdno.lcm.templatemodel.service.Instance;
import org.openo.sdno.lcm.templatemodel.service.Interface;
import org.openo.sdno.lcm.templatemodel.service.Metadata;
import org.openo.sdno.lcm.templatemodel.service.Node;
import org.openo.sdno.lcm.templatemodel.service.Operation;
import org.openo.sdno.lcm.templatemodel.service.Relationship;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(groups = {"sdno-lcm-unit"})
public class TemplateInstanceParserImplTest {

    TemplateInstanceParserImpl templateInstanceParserImpl;

    String instanceJson;

    Metadata expectedMetadata;

    /**
     * Test the template instance JSON is parsed as expected.
     */
    @Test
    public void testParse() {
        Instance instance = templateInstanceParserImpl.parse(instanceJson);
        Assert.assertNotNull(instance);
        Assert.assertEquals(instance.getDescription(), "sdno basic types");
        Assert.assertEquals(instance.getNodes().size(), 20);
        Assert.assertEquals(instance.getMetadata(), expectedMetadata);
        Assert.assertNotNull(instance.getInputs());

        int checkedNodes = 0;

        for(Node node : instance.getNodes()) {
            Assert.assertNotNull(node.getProperties());
            Assert.assertNotNull(node.getCapabilities());
            Assert.assertFalse(node.getCapabilities().isEmpty());
            Assert.assertNotNull(node.getId());
            Assert.assertNotNull(node.getInterfaces());
            Assert.assertFalse(node.getInterfaces().isEmpty());
            Assert.assertNotNull(node.getRelationships());
            Assert.assertNotNull(node.getTemplateName());
            Assert.assertNotNull(node.getTypeName());

            if(node.getId().startsWith("thinCpe_")) {

                checkedNodes++;
                Assert.assertEquals(node.getTypeName(), "sdno.node.Node");
                Assert.assertEquals(node.getTemplateName(), "thinCpe");
                Assert.assertEquals(node.getCapabilities().size(), 3);

                Capability capability = node.getCapabilities().get(0);
                Assert.assertEquals(capability.getName(), "feature");
                Assert.assertEquals(capability.getTypeName(), "tosca.capabilities.Node");

                capability = node.getCapabilities().get(1);
                Assert.assertEquals(capability.getName(), "host");
                Assert.assertEquals(capability.getTypeName(), "sdno.capability.Host");

                Assert.assertEquals(node.getInterfaces().size(), 1);
                Interface interfac = node.getInterfaces().get(0);
                Assert.assertEquals(interfac.getName(), "Standard");
                Assert.assertEquals(interfac.getTypeName(), "tosca.interfaces.node.lifecycle.Standard");
                Assert.assertEquals(interfac.getOperations().size(), 5);

                Assert.assertNotNull(node.getProperties());
                checkNodeProperty(node, "siteName", "none");
                checkNodeProperty(node, "version", "1.0");
                checkNodeProperty(node, "ipAddress", "0.0.0.0");
            }

            if(node.getId().startsWith("enterprise2Dc_")) {

                checkedNodes++;
                Assert.assertEquals(node.getTypeName(), "sdno.node.ConnectivityService.Enterprise2Dc");
                Assert.assertEquals(node.getTemplateName(), "enterprise2Dc");
                Assert.assertEquals(node.getCapabilities().size(), 1);

                Capability capability = node.getCapabilities().get(0);
                Assert.assertEquals(capability.getName(), "feature");
                Assert.assertEquals(capability.getTypeName(), "tosca.capabilities.Node");

                Assert.assertEquals(node.getRelationships().size(), 6);
                Relationship actualRelationship = node.getRelationships().get(2);
                Assert.assertEquals(actualRelationship.getName(), "realizes");
                Assert.assertEquals(actualRelationship.getSourceRequirementIndex(), new Integer(2));
                Assert.assertTrue(actualRelationship.getTargetNodeId().startsWith("siteSubnet_"));

                Assert.assertEquals(node.getInterfaces().size(), 2);
                Interface interfac = node.getInterfaces().get(0);
                Assert.assertEquals(interfac.getName(), "Standard");
                Assert.assertEquals(interfac.getTypeName(), "tosca.interfaces.node.lifecycle.Standard");
                Assert.assertEquals(interfac.getOperations().size(), 5);
                interfac = node.getInterfaces().get(1);
                Assert.assertEquals(interfac.getName(), "standard");
                Assert.assertEquals(interfac.getTypeName(), "sdno.interfaces.lifecycle.Standard");
                Assert.assertEquals(interfac.getOperations().size(), 6);

                Operation deployOp = interfac.getOperations().get(3);
                Assert.assertEquals(deployOp.getName(), "deploy");
                Assert.assertEquals(deployOp.getDescription(), "Deploy Enterprise2Dc");
                Assert.assertEquals(deployOp.getImplementation(),
                        "POST /openoapi/sdnonslcm/v1/ns/{instanceid}/instantiate");
                List<Dependency> dependencyList = deployOp.getDependencyList();
                Assert.assertEquals(dependencyList.size(), 4);
                Assert.assertEquals(dependencyList.get(0).getOperation(), "deploy");
                Assert.assertEquals(dependencyList.get(0).getType(), "sdno.node.Connection.VpcSubnet");
                Assert.assertEquals(dependencyList.get(1).getOperation(), "deploy");
                Assert.assertEquals(dependencyList.get(1).getType(), "sdno.node.Connection.SiteSubnet");
                Assert.assertEquals(dependencyList.get(2).getOperation(), "deploy");
                Assert.assertEquals(dependencyList.get(2).getType(), "sdno.node.Connection.Vpn");
                Assert.assertEquals(dependencyList.get(3).getOperation(), "deploy");
                Assert.assertEquals(dependencyList.get(3).getType(), "sdno.node.Connection.Sfc");

                Assert.assertNotNull(node.getProperties());
                checkNodeProperty(node, "lifecycleState", "none");
                checkNodeProperty(node, "version", "1.0");
            }
        }
        Assert.assertEquals(checkedNodes, 2,
                "the number of nodes checked was not correct - must be a problem in the test");
    }

    private void checkNodeProperty(Node node, String propertyName, String expectedValue) {
        Assert.assertNotNull(node.getPropertyJsonNode(propertyName));
        Assert.assertEquals(node.getPropertyValue(propertyName), expectedValue);
    }

    @BeforeClass
    public void before() throws Exception {
        templateInstanceParserImpl = new TemplateInstanceParserImpl();
        instanceJson = FileUtils.readFileToString(FileUtils.getFile("src", "test", "resources", "instance.json"),
                Charset.defaultCharset());
        expectedMetadata = new Metadata();
        expectedMetadata.setTemplateName("enterprise2Dc");
        expectedMetadata.setTemplateAuthor("Huawei");
        expectedMetadata.setVersion(0.1d);
        expectedMetadata.setId("enterprise2Dc");
        expectedMetadata.setVendor("sdno");
    }

}
