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

package org.openo.sdno.lcm.dispatcher.impl;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import static org.testng.Assert.*;

/**
 * Unit tests for SwaggerUtils
 */
@Test(groups = {"sdno-lcm-unit"})
@ContextConfiguration(locations = {"classpath:spring-test-config.xml"})
public class RequestBodyMappingRuleTest extends AbstractTestNGSpringContextTests {
    @Test
    public void testRequiredScalar() {
        RequestBodyMappingRule rule = new RequestBodyMappingRule("*name", "name");

        assertTrue(rule.isRequired(), "Required is not parsed correctly!");
        assertEquals(rule.getKeyType(), RequestBodyMappingRuleType.SCALAR, "Type is not parsed correctly!");
        assertEquals(rule.getValueType(), RequestBodyMappingRuleType.SCALAR, "Type is not parsed correctly!");
        assertEquals(rule.getKeyName(), "name", "Name is not parsed correctly!");
        assertEquals(rule.getValueName(), "name", "Name is not parsed correctly!");
    }

    @Test
    public void testNotRequiredScalar() {
        RequestBodyMappingRule rule = new RequestBodyMappingRule("name", "name");

        assertFalse(rule.isRequired(), "Required is not parsed correctly!");
        assertEquals(rule.getKeyType(), RequestBodyMappingRuleType.SCALAR, "Type is not parsed correctly!");
        assertEquals(rule.getValueType(), RequestBodyMappingRuleType.SCALAR, "Type is not parsed correctly!");
        assertEquals(rule.getKeyName(), "name", "Name is not parsed correctly!");
        assertEquals(rule.getValueName(), "name", "Name is not parsed correctly!");
    }

    @Test
    public void testScalarArray() {
        RequestBodyMappingRule rule = new RequestBodyMappingRule("*classifiers[]", "classifiers[]");

        assertTrue(rule.isRequired(), "Required is not parsed correctly!");
        assertEquals(rule.getKeyType(), RequestBodyMappingRuleType.SCALAR_ARRAY, "Type is not parsed correctly!");
        assertEquals(rule.getValueType(), RequestBodyMappingRuleType.SCALAR_ARRAY, "Type is not parsed correctly!");
        assertEquals(rule.getKeyName(), "classifiers", "Name is not parsed correctly!");
        assertEquals(rule.getValueName(), "classifiers", "Name is not parsed correctly!");
    }

    @Test
    public void testObject() {
        RequestBodyMappingRule rule = new RequestBodyMappingRule("*classifiers#ServiceClassifier#", "classifiers#ServiceClassifierType#");

        assertTrue(rule.isRequired(), "Required is not parsed correctly!");
        assertEquals(rule.getKeyType(), RequestBodyMappingRuleType.OBJECT, "Type is not parsed correctly!");
        assertEquals(rule.getValueType(), RequestBodyMappingRuleType.OBJECT, "Type is not parsed correctly!");
        assertEquals(rule.getKeyName(), "classifiers", "Name is not parsed correctly!");
        assertEquals(rule.getValueName(), "classifiers", "Name is not parsed correctly!");
        assertEquals(rule.getKeyObjectModelName(), "ServiceClassifier", "Name is not parsed correctly!");
        assertEquals(rule.getValueObjectModelName(), "ServiceClassifierType", "Name is not parsed correctly!");
    }

    @Test
    public void testObjectArray() {
        RequestBodyMappingRule rule = new RequestBodyMappingRule("*classifiers[]#ServiceClassifier#", "classifiers[]#ServiceClassifierType#");

        assertTrue(rule.isRequired(), "Required is not parsed correctly!");
        assertEquals(rule.getKeyType(), RequestBodyMappingRuleType.OBJECT_ARRAY, "Type is not parsed correctly!");
        assertEquals(rule.getValueType(), RequestBodyMappingRuleType.OBJECT_ARRAY, "Type is not parsed correctly!");
        assertEquals(rule.getKeyName(), "classifiers", "Name is not parsed correctly!");
        assertEquals(rule.getValueName(), "classifiers", "Name is not parsed correctly!");
        assertEquals(rule.getKeyObjectModelName(), "ServiceClassifier", "Name is not parsed correctly!");
        assertEquals(rule.getValueObjectModelName(), "ServiceClassifierType", "Name is not parsed correctly!");
    }
}
