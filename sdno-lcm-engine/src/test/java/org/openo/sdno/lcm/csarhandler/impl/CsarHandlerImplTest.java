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

package org.openo.sdno.lcm.csarhandler.impl;

import static org.easymock.EasyMock.anyString;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.replay;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openo.sdno.lcm.catalogclient.PackageResourceApiClient;
import org.openo.sdno.lcm.restclient.catalog.model.PackageMeta;
import org.openo.sdno.lcm.util.Mapper;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;

import io.swagger.models.Swagger;

@Test(groups = {"sdno-lcm-unit"})
public class CsarHandlerImplTest {

    CsarHandlerImpl csarHandler;

    FileHandler mockFileHandler;

    PackageResourceApiClient mockPackageResourceApiClient;

    Mapper mapper;

    @BeforeMethod
    public void beforeMethod() throws IOException {

        mockFileHandler = mock(FileHandler.class);
        File csarFile = FileUtils.getFile("src", "test", "resources", "enterprise2DC.csar");
        expect(mockFileHandler.getFile(anyString(), anyString(), anyString())).andReturn(csarFile).once();
        replay(mockFileHandler);

        mockPackageResourceApiClient = mock(PackageResourceApiClient.class);
        PackageMeta packageMeta = new PackageMeta();
        packageMeta.setCsarId("myCsarId");
        packageMeta.setName("myCsarName");
        expect(mockPackageResourceApiClient.queryPackageById(anyString())).andReturn(packageMeta).once();
        replay(mockPackageResourceApiClient);

        csarHandler = new CsarHandlerImpl();
        csarHandler.setFileHandler(mockFileHandler);
        csarHandler.setPackageResourceApiClient(mockPackageResourceApiClient);

        mapper = new Mapper();
        csarHandler.setMapper(mapper);
    }

    @DataProvider
    public Object[][] SwaggerTestDataProvider() {
        return new Object[][] {{"swagger/overlay.jaml", "/openoapi/sdnooverlay/v1"},
                        {"swagger/sfc.jaml", "/openoapi/sdnoservicechain/v1"}, {"swagger/site.jaml", "/openoapi/sdnolocalsite/v1"},
                        {"swagger/vpc.jaml", "/openoapi/sdnovpc/v1"}};
    }

    @Test(dataProvider = "SwaggerTestDataProvider")
    public void getSwaggerSpecTest(String specPath, String expectedBasePath) {
        Swagger swaggerSpec = csarHandler.getSwaggerSpec("anyCsarId", specPath);
        Assert.assertNotNull(swaggerSpec);
        Assert.assertEquals(swaggerSpec.getBasePath(), expectedBasePath);
    }

    @DataProvider
    public Object[][] MapperTestDataProvider() {
        return new Object[][] {{"mapper/overlay.tsmap"}, {"mapper/sfc.tsmap"},
                        // {"mapper/site.tsmap"}, TODO Site file appears invalid, see SDNO-1430
                        {"mapper/vpc.tsmap"}};
    }

    @Test(dataProvider = "MapperTestDataProvider")
    public void getMapperSpecTest(String specPath) {
        JsonNode mapperSpec = csarHandler.getMapperSpec("anyCsarId", specPath);
        Assert.assertNotNull(mapperSpec);
    }

}
