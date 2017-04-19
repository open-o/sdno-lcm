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

package org.openo.sdno.lcm.filestorehandler.impl;

import java.nio.charset.Charset;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.openo.sdno.lcm.templateinstanceparser.impl.TemplateInstanceParserImpl;
import org.openo.sdno.lcm.templatemodel.service.Instance;
import org.openo.sdno.lcm.util.Mapper;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(groups = { "sdno-lcm-unit" })
public class FileStoreHandlerImplTest {

    TemplateInstanceParserImpl templateInstanceParserImpl;
    FileStoreHandlerImpl fileStoreHandler;
    Instance instance;

    @BeforeClass
    public void beforeClass() throws Exception {

        String instanceJson;

        templateInstanceParserImpl = new TemplateInstanceParserImpl();
        templateInstanceParserImpl.setMapper(new Mapper());
        fileStoreHandler = new FileStoreHandlerImpl();
        fileStoreHandler.setTemplateInstanceParser(templateInstanceParserImpl);

        instanceJson = FileUtils.readFileToString(FileUtils.getFile("src", "test", "resources", "instance.json"),
                Charset.defaultCharset());

        instance = templateInstanceParserImpl.parse(instanceJson);
    }

    @Test
    public void storeInstanceToFile() {
        String fileId = "zzz" + UUID.randomUUID().toString();
        fileStoreHandler.storeInstanceToFile(instance, fileId);
        Instance instanceFromFile = fileStoreHandler.getInstanceFromFile(fileId);
        Assert.assertEquals(instanceFromFile.getMetadata().getId(), "enterprise2Dc");
    }
}
