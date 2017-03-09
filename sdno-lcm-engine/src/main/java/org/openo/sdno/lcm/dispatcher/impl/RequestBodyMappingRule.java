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

import static com.google.common.base.Preconditions.*;
/**
 * this class represents a mapping rule.
 */
public class RequestBodyMappingRule {
    public static final String NO_STEP_INTO_STR = "NO-STEP-INTO";

    /**
     * one internal class to represent the key or value of a mapping rule.
     * It parses either key or value of one rule in mapping file inside its constructor.
     * One required field/property starts with "*" and follows with its name.
     * For one array field/property, "[]" will be appended immediately after its name.
     * If the field/property or its array item is one object, its type will be specified
     * at the end with the format "#TypeName#"
     *
     * For example:
     *    1. "*mode" represents one REQUIRED field/property whose name is "mode".
     *    2. "classifiers[]" represents one optional array whose name is "classifiers"
     *        and its array item is a scalar type.
     *    3. "path#SfcPath#" represents one optional field/property whose name is "path" and
     *        it is a complex data defined in "SfcPath"
     *    4. "flows[]#TrafficFlow#" represents one optional  field/property whose name is "flows",
     *        it is an array, and its item is a complex data defined in "TrafficFlow".
     */
    class RequestBodyMappingRuleItem {
        boolean required; //whether this field/property is required in swagger API.
        RequestBodyMappingRuleType itemType;  //the type of this field/property
        String name;  //the name of this field/property
        String objectModelName; //the model name of this field/property if it is a complex data type.

        //constructor
        public RequestBodyMappingRuleItem(final String itemStr) {
            checkArgument((itemStr!=null)&&(itemStr.length()>0), "ItemStr cannot be null or empty.");

            int nameStartIndex = 0;
            int nameEndIndex = itemStr.length();

            if(itemStr.startsWith("*")) {
                required = true;
                nameStartIndex = 1;
            } else required = false;

            if(itemStr.matches("(.*)#(.*)#")) {
                int objectModelNameStartIndex = 0;
                int objectModelNameEndIndex = itemStr.length() - 1;

                if(itemStr.matches("(.*)\\[\\]#(.*)#")) {
                    itemType = RequestBodyMappingRuleType.OBJECT_ARRAY;
                    nameEndIndex = itemStr.indexOf("[]");
                    name = itemStr.substring(nameStartIndex, nameEndIndex);
                } else {
                    itemType = RequestBodyMappingRuleType.OBJECT;
                    nameEndIndex = itemStr.indexOf("#");
                    name = itemStr.substring(nameStartIndex, nameEndIndex);
                }
                objectModelNameStartIndex = itemStr.indexOf("#");
                objectModelName = itemStr.substring(objectModelNameStartIndex+1, objectModelNameEndIndex);
            } else if(itemStr.matches("(.*)\\[\\]")) {
                itemType = RequestBodyMappingRuleType.SCALAR_ARRAY;
                name = itemStr.substring(nameStartIndex, itemStr.length()-2);
            } else {
                itemType = RequestBodyMappingRuleType.SCALAR;
                name = itemStr.substring(nameStartIndex, nameEndIndex);
            }
        }
    }

    private RequestBodyMappingRuleItem key;  //key of the mapping rule
    private RequestBodyMappingRuleItem value;  //value of the mapping rule

    /**
     * constructor
     * @param keyStr the key of a rule specified in the mapping file
     * @param valueStr the value of a rule specified in the mapping file
     */
    public RequestBodyMappingRule(final String keyStr, final String valueStr) {
        key = new RequestBodyMappingRuleItem(keyStr);
        value = new RequestBodyMappingRuleItem(valueStr);
    }

    /**
     * whether the API parameter is required.
     * @return required or not
     */
    public boolean isRequired() {
        return key.required;
    }

    /**
     * get the type of the field/property specified in key
     * @return the type of the field/property specified in key
     */
    public RequestBodyMappingRuleType getKeyType() {
        return key.itemType;
    }

    /**
     * get the type of the field/property specified in value
     * @return the type of the field/property specified in value
     */
    public RequestBodyMappingRuleType getValueType() {
        return value.itemType;
    }

    /**
     * get the name of the field/property specified in key
     * @return the name of the field/property specified in key
     */
    public String getKeyName() {
        return key.name;
    }

    /**
     * get the name of the field/property specified in value
     * @return the name of the field/property specified in value
     */
    public String getValueName() {
        return value.name;
    }

    /**
     * get the model name of the complex field/property specified in key
     * @return the model name of the complex field/property specified in key
     */
    public String getKeyObjectModelName() {
        return key.objectModelName;
    }

    /**
     * get the model name of the complex field/property specified in value
     * @return the model name of the complex field/property specified in value
     */
    public String getValueObjectModelName() {
        return value.objectModelName;
    }
}