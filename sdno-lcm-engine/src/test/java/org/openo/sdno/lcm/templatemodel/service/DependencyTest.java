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

import org.openo.sdno.lcm.exception.LcmInternalException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(groups = {"sdno-lcm-unit"})
public class DependencyTest {

    @DataProvider(name = "failProvider")
    public Object[][] failProvider() {
        return new Object[][] {{"/deploy"}, {"sdno.node.Connection.Site/"}, {""},
                        {"sdno.node.Connection.Site//deploy"}};
    }

    @Test
    public void success() {
        Dependency d = new Dependency("sdno.node.Connection.Site/deploy");
        Assert.assertEquals(d.getOperation(), "deploy");
        Assert.assertEquals(d.getType(), "sdno.node.Connection.Site");
    }

    @Test(dataProvider = "failProvider", expectedExceptions = LcmInternalException.class)
    public void fail(String dep) {

        Dependency d = new Dependency(dep);
    }
}
