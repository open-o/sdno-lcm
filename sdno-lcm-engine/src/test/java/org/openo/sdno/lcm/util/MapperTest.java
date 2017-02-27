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

package org.openo.sdno.lcm.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@SuppressWarnings("unused")
public class MapperTest {

    // private Map<String,String> map;

    private Mapper mapper = new Mapper();

    @DataProvider(name = "successProvider")
    public Object[][] successProvider() {
        return new Object[][] {{new Bean1()}, {new Bean2()}, {new Bean4()}, {new Bean5()}};
    }

    @Test(dataProvider = "successProvider", groups = {"sdno-lcm-unit"})
    public void beanToMap(Beany bean) {

        Map<String, Object> mapperResultMap = mapper.beanToMap(bean);
        Map<String, String> expectMap = bean.obtainExpectMap();
        for(String expectKey : expectMap.keySet()) {

            if(null == expectMap.get(expectKey)) {
                Assert.isNull(mapperResultMap.get(expectKey));
            } else {
                Assert.isTrue((null != mapperResultMap.get(expectKey))
                        && mapperResultMap.get(expectKey).equals(expectMap.get(expectKey)));
            }
        }
    }

    // @Test(dataProvider = "dp")
    // public void beanToMapException]() {
    //
    // throw new RuntimeException("Test not implemented");
    // }
    //

    private interface Beany {

        /**
         * Should be ignored by the mapper as it is a method not property
         * 
         * @return the expected map of values from mapping an instance of the
         *         bean
         */
        Map<String, String> obtainExpectMap();
    }

    private class Bean1 implements Beany {

        private String stringA = "str";

        public Map<String, String> obtainExpectMap() {

            Map<String, String> expectMap = new HashMap<String, String>();
            expectMap.put("stringA", "str");
            return expectMap;
        }

        public String getStringA() {
            return stringA;
        }

        public void setStringA(String stringA) {
            this.stringA = stringA;
        }
    }

    private class Bean2 implements Beany {

        private String aString = "str";

        private String anotherString = "strrr";

        public Map<String, String> obtainExpectMap() {

            Map<String, String> expectMap = new HashMap<String, String>();
            expectMap.put("aString", "str");
            expectMap.put("anotherString", "strrr");
            return expectMap;
        }

        public String getaString() {
            return aString;
        }

        public void setaString(String aString) {
            this.aString = aString;
        }

        public String getAnotherString() {
            return anotherString;
        }

        void setAnotherString(String anotherString) {
            this.anotherString = anotherString;
        }
    }

    // mapping fails as there are no properties
    private class Bean3 implements Beany {

        public Map<String, String> obtainExpectMap() {

            Map<String, String> expectMap = new HashMap<String, String>();
            return expectMap;
        }
    }

    private class Bean4 implements Beany {

        private String nullStr = null;

        public Map<String, String> obtainExpectMap() {

            Map<String, String> expectMap = new HashMap<String, String>();
            expectMap.put("nullStr", null);
            return expectMap;
        }

        public String getNullStr() {
            return nullStr;
        }

        void setNullStr(String nullStr) {
            this.nullStr = nullStr;
        }
    }

    private class Bean5 implements Beany {

        private String emptyStr = "";

        public Map<String, String> obtainExpectMap() {

            Map<String, String> expectMap = new HashMap<String, String>();
            expectMap.put("emptyStr", "");
            return expectMap;
        }

        public String getEmptyStr() {
            return emptyStr;
        }

        void setEmptyStr(String emptyStr) {
            this.emptyStr = emptyStr;
        }
    }

    // mapping fails as Integer is not handled
    private class Bean6 implements Beany {

        private int anInt = 123;

        public Map<String, String> obtainExpectMap() {

            Map<String, String> expectMap = new HashMap<String, String>();
            expectMap.put("anInt", "123");
            return expectMap;
        }

        public int getAnInt() {
            return anInt;
        }

        void setAnInt(int anInt) {
            this.anInt = anInt;
        }
    }

}
