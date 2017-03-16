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

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openo.sdno.lcm.exception.LcmInternalException;
import org.openo.sdno.lcm.templateinstanceparser.impl.TemplateInstanceParserImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.models.HttpMethod;

@Test(groups = {"sdno-lcm-unit"})
public class NodeTest {

    Node node;

    Node nodeForOperationTests;

    Instance instance;

    @BeforeMethod
    public void beforeMethod() {

        node = new Node();
    }

    // needed to test setProperty
    @BeforeClass
    public void beforeClass() throws Exception {
        TemplateInstanceParserImpl templateInstanceParserImpl = new TemplateInstanceParserImpl();
        instance = templateInstanceParserImpl.parse(FileUtils.readFileToString(
                FileUtils.getFile("src", "test", "resources", "instance.json"), Charset.defaultCharset()));

        File nodeFile = FileUtils.getFile("src", "test", "resources", "nodetest", "node1.json");
        ObjectMapper objectMapper = new ObjectMapper();
        nodeForOperationTests = objectMapper.readValue(nodeFile, Node.class);
    }

    @Test(expectedExceptions = LcmInternalException.class)
    public void clearExaminedTestError() {
        Assert.assertFalse(node.isExamined());
        node.clearExamined();
    }

    @Test
    public void isExaminedTestSuccess() {
        Assert.assertFalse(node.isExamined());
        node.setExamined();
        Assert.assertTrue(node.isExamined());
        node.clearExamined();
        Assert.assertFalse(node.isExamined());
    }

    @Test(expectedExceptions = LcmInternalException.class)
    public void setExaminedTestError() {
        Assert.assertFalse(node.isExamined());
        node.setExamined();
        node.setExamined();
    }

    public void isConnectionNodeTrueSuccess() {

        node.setTypeName("sdno.node.Connection.Site");
        Assert.assertTrue(node.isConnectionNode());
        node.setTypeName("sdno.node.ConnectionEndPoint.ThinCpe");
        Assert.assertTrue(node.isConnectionNode());
    }

    public void isConnectionNodeFalseSuccess() {

        node.setTypeName("xxx.node.Connection.Site");
        Assert.assertFalse(node.isConnectionNode());
        node.setTypeName("sdno.xxx.Connection.Site");
        Assert.assertFalse(node.isConnectionNode());
        node.setTypeName("sdno.node.xxx.Site");
        Assert.assertFalse(node.isConnectionNode());
        node.setTypeName("xxx.sdno.node.Connection.Site");
        Assert.assertFalse(node.isConnectionNode());
        node.setTypeName("sdno.node.ConnectionXxx.Site");
        Assert.assertFalse(node.isConnectionNode());

        node.setTypeName("sdno.node.Node");
        Assert.assertFalse(node.isConnectionNode());
        node.setTypeName("sdno.node.ConnectivityService.Enterprise2Dc");
        Assert.assertFalse(node.isConnectionNode());
        node.setTypeName("sdno.node.ServiceEndPoint.ThinCpe");
        Assert.assertFalse(node.isConnectionNode());
    }

    @Test(expectedExceptions = LcmInternalException.class)
    public void isConnectionNodeError1() {
        node.setTypeName("");
        node.isConnectionNode();
    }

    @Test(expectedExceptions = LcmInternalException.class)
    public void isConnectionNodeError2() {
        node.setTypeName(null);
        node.isConnectionNode();
    }

    @Test
    public void setPropertyTestSuccess() {

        int expectedChecks = 1;
        int performedChecks = 0;
        // case of adding a property that is not present
        List<Node> nodes = instance.getNodes();
        for(Node n : nodes) {

            if(n.getId().startsWith("site_")) {
                performedChecks++;
                node = n;
                String propertyName = "banana";
                String value = "BANANA!!!";
                String typeName = "string";
                node.setProperty(propertyName, value, typeName);
                JsonNode bananode = node.getProperties().get(propertyName);
                Assert.assertNotNull(bananode, "The property banana was not found!");
                Assert.assertEquals(bananode.findValue("type_name").asText(), typeName);
                Assert.assertEquals(bananode.findValue("value").asText(), value);

                // case of updating a property value
                propertyName = "id";
                node.setProperty(propertyName, value, typeName);
                JsonNode idNode = node.getProperties().get(propertyName);
                Assert.assertNotNull(idNode, "The property id was not found!");
                Assert.assertEquals(idNode.findValue("type_name").asText(), typeName);
                Assert.assertEquals(idNode.findValue("value").asText(), value);
            }
        }
        Assert.assertEquals(performedChecks, expectedChecks, "Did not check the expected number of nodes");
    }

    @DataProvider
    public Object[][] badImplementationHttpMethodProvider() {
        return new Object[][] {{"deploy"}, {"create"}, {"delete"}, {"get"}};
    }

    @Test(dataProvider = "badImplementationHttpMethodProvider", expectedExceptions = LcmInternalException.class)
    public void getOperationHttpMethodTestError(String operationName) throws IOException {

        nodeForOperationTests.getOperationHttpMethod(operationName);
    }

    @DataProvider
    public Object[][] successHttpMethodProvider() {
        return new Object[][] {{"update", HttpMethod.GET}, {"undeploy", HttpMethod.DELETE}};
    }

    @Test(dataProvider = "successHttpMethodProvider")
    public void getOperationHttpMethodTestSuccess(String operationName, HttpMethod expectedHttpMethod) {

        Assert.assertEquals(nodeForOperationTests.getOperationHttpMethod(operationName), expectedHttpMethod);
    }

}
