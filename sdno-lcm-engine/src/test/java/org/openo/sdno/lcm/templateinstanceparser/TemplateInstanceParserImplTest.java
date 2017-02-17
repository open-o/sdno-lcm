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

import org.apache.commons.io.FileUtils;
import org.openo.sdno.lcm.templateinstanceparser.impl.TemplateInstanceParserImpl;
import org.openo.sdno.lcm.templatemodel.service.Capability;
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

    @Test
    public void testParse() {
        Instance instance = templateInstanceParserImpl.parse(instanceJson);
        Assert.assertNotNull(instance);
        Assert.assertEquals(instance.getDescription(), "sdno basic types");
        Assert.assertEquals(instance.getNodes().size(), 14);
        Assert.assertEquals(instance.getMetadata(), expectedMetadata);
        Assert.assertNotNull(instance.getInputs());
        
        for(Node node:instance.getNodes()) {
            Assert.assertNotNull(node.getProperties());
            Assert.assertNotNull(node.getCapabilities());
            Assert.assertFalse(node.getCapabilities().isEmpty());
            Assert.assertNotNull(node.getId());
            Assert.assertNotNull(node.getInterfaces());
            Assert.assertFalse(node.getInterfaces().isEmpty());
            Assert.assertNotNull(node.getRelationships());
            Assert.assertNotNull(node.getTemplateName());
            Assert.assertNotNull(node.getTypeName());
            
            if (node.getId().equals("thinCpe_lxfwggrxi2hh7pk21lcrm3j0b")) {
                
                Assert.assertEquals(node.getTypeName(), "sdno.node.ConnectionEndPoint.ThinCpe");
                Assert.assertEquals(node.getTemplateName(), "thinCpe");
                Assert.assertEquals(node.getCapabilities().size(), 2);
                
                Capability capability = node.getCapabilities().get(0);
                Assert.assertEquals(capability.getName(), "feature");
                Assert.assertEquals(capability.getTypeName(), "tosca.capabilities.Node");
                
                capability = node.getCapabilities().get(1);
                Assert.assertEquals(capability.getName(), "endPoint");
                Assert.assertEquals(capability.getTypeName(), "sdno.capability.ConnectionEndPoint");
                
                Assert.assertEquals(node.getRelationships().size(), 1);
                Relationship actualRelationship = node.getRelationships().get(0);
                Assert.assertEquals(actualRelationship.getName(), "realizes");
                Assert.assertEquals(actualRelationship.getSourceRequirementIndex(), new Integer(0));
                Assert.assertEquals(actualRelationship.getTargetNodeId(), "site_fkuvg8p00gy6fz97vavm0qhkd");
                Assert.assertEquals(actualRelationship.getTargetCapabilityName(), "feature");
                
                Assert.assertEquals(node.getInterfaces().size(), 2);
                Interface interfac = node.getInterfaces().get(1);
                Assert.assertEquals(interfac.getName(), "standard");
                Assert.assertEquals(interfac.getTypeName(), "sdno.interfaces.lifecycle.Standard");
                Assert.assertEquals(interfac.getOperations().size(), 6);
                
                Operation deployOp = interfac.getOperations().get(3);
                Assert.assertEquals(deployOp.getName(), "deploy");
                Assert.assertEquals(deployOp.getDescription(), "Standard lifecycle start operation.");
                Assert.assertEquals(deployOp.getImplementation(), "POST /openoapi/sdnolocalsite/v1/local-cpes");
                
            }
        }
    }

    @BeforeClass
    public void before() throws Exception {
        templateInstanceParserImpl = new TemplateInstanceParserImpl();
        instanceJson = FileUtils.readFileToString(FileUtils.getFile("src","test", "resources", "instance.json"), Charset.defaultCharset());
        expectedMetadata = new Metadata();
        expectedMetadata.setTemplateName("enterprise2Dc");
        expectedMetadata.setTemplateAuthor("Huawei");
        expectedMetadata.setVersion(0.1d);
        expectedMetadata.setId("enterprise2Dc");
        expectedMetadata.setVendor("sdno");
    }

}
