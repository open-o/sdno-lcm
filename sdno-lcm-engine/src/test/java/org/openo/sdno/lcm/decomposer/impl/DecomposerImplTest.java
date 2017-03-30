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

import static org.easymock.EasyMock.anyString;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.replay;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openo.sdno.lcm.catalogclient.PackageResourceApiClient;
import org.openo.sdno.lcm.csarhandler.impl.CsarHandlerImpl;
import org.openo.sdno.lcm.csarhandler.impl.FileHandler;
import org.openo.sdno.lcm.model.workplan.WorkItem;
import org.openo.sdno.lcm.model.workplan.WorkPlan;
import org.openo.sdno.lcm.restclient.catalog.model.PackageMeta;
import org.openo.sdno.lcm.templateinstanceparser.TemplateInstanceParser;
import org.openo.sdno.lcm.templateinstanceparser.impl.TemplateInstanceParserImpl;
import org.openo.sdno.lcm.templatemodel.service.Instance;
import org.openo.sdno.lcm.templatemodel.service.Node;
import org.openo.sdno.lcm.util.Mapper;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test(groups = { "sdno-lcm-unit" })
public class DecomposerImplTest {

	DecomposerImpl decomposer;

	Instance instance;

	@BeforeMethod
	public void before() throws Exception {

		FileHandler mockFileHandler = mock(FileHandler.class);
		File csarFile = FileUtils.getFile("src", "test", "resources", "enterprise2DC.csar");
		expect(mockFileHandler.getFile(anyString(), anyString(), anyString())).andReturn(csarFile).anyTimes();
		replay(mockFileHandler);

		PackageResourceApiClient mockPackageResourceApiClient = mock(PackageResourceApiClient.class);
		PackageMeta packageMeta = new PackageMeta();
		packageMeta.setCsarId("myCsarId");
		packageMeta.setName("myCsarName");
		expect(mockPackageResourceApiClient.queryPackageById(anyString())).andReturn(packageMeta).anyTimes();
		replay(mockPackageResourceApiClient);

		TemplateInstanceParser templateInstanceParser = new TemplateInstanceParserImpl();
		decomposer = new DecomposerImpl();
		CsarHandlerImpl csarHandler = new CsarHandlerImpl();
		csarHandler.setMapper(new Mapper());
		csarHandler.setFileHandler(mockFileHandler);
		csarHandler.setPackageResourceApiClient(mockPackageResourceApiClient);

		decomposer.setCsarHandler(csarHandler);
		String instanceJson = FileUtils.readFileToString(FileUtils.getFile("src", "test", "resources", "instance.json"),
				Charset.defaultCharset());
		instance = templateInstanceParser.parse(instanceJson);
	}

	@Test
	public void decomposeDeployTest() {
		WorkPlan workplan = decomposer.decompose(instance, "deploy", "anyCsarId");
		this.checkWorkItem(0, workplan.getWorkItem(0), "vpc_");
		this.checkWorkItem(1, workplan.getWorkItem(1), "vpcSubnet_");
		this.checkWorkItem(4, workplan.getWorkItem(2), "site_");
		this.checkWorkItem(2, workplan.getWorkItem(3), "thinCpeConnectionEndPoint_");
		this.checkWorkItem(3, workplan.getWorkItem(4), "vCpeConnectionEndPoint_");
		this.checkWorkItem(5, workplan.getWorkItem(5), "vlan_");
		this.checkWorkItem(6, workplan.getWorkItem(6), "siteSubnet_");
		this.checkWorkItem(7, workplan.getWorkItem(7), "vpn_");
		this.checkWorkItem(8, workplan.getWorkItem(8), "siteGateway_");
		this.checkWorkItem(9, workplan.getWorkItem(9), "vpcGateway_");
		this.checkWorkItem(10, workplan.getWorkItem(10), "vpnConnection_");
		this.checkWorkItem(11, workplan.getWorkItem(11), "sfc_");
	}

	private void checkWorkItem(int index, WorkItem workItem, String prefix) {
		String workItemId = workItem.getNode().getId();
		Assert.assertTrue(workItemId.startsWith(prefix),
				String.format(
						"The Node in the WorkItem at index %s did not have the expected id prefix %s. The Node id is: %s",
						index, prefix, workItemId));
		// check the id has been added
		Assert.assertNotNull(workItem.getNode().getPropertyValue("id"));
	}
}
