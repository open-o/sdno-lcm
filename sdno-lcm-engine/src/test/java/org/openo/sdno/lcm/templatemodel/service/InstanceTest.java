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

import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openo.sdno.lcm.templateinstanceparser.impl.TemplateInstanceParserImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(groups = { "sdno-lcm-unit" })
public class InstanceTest {

	Instance instance;

	@BeforeClass
	public void beforeClass() throws Exception {
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
	public void fillResourceNodesTest() {

		int expectedChecks = 1;
		int performedChecks = 0;
		// case of adding a property that is not present
		List<Node> nodes = instance.getNodes();
		for (Node node : nodes) {

			if (node.getId().startsWith("thinCpe_")) {
				performedChecks++;
				Assert.assertEquals(node.getPropertyValue("id"), "0");
				instance.fillResourceNodes();
				// a generated uuid should be filled for now - the real value
				// should come from BRS
				Assert.assertNotEquals(node.getPropertyValue("id"), "0");
			}
		}
		Assert.assertEquals(performedChecks, expectedChecks, "Did not check the expected number of nodes");
	}
}
