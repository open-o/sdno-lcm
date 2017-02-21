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

package org.openo.sdno.lcm.statetablehandler.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openo.sdno.lcm.exception.InvalidInputException;
import org.openo.sdno.lcm.exception.InvalidStateTableException;
import org.openo.sdno.lcm.exception.InvalidTransitionException;
import org.openo.sdno.lcm.statetablehandler.model.StateTable;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.charset.Charset;

@Test(groups = {"sdno-lcm-unit"})
public class StateTableHandlerImplTest {

    StateTableHandlerImpl defaultStateTableHandlerImpl;
    String instanceJson;

    @BeforeMethod
    public void beforeMethod() throws Exception {

        defaultStateTableHandlerImpl = new StateTableHandlerImpl();
    }

    /**
     * Test expected exception is throw when there is a empty json
     */
    @Test(expectedExceptions = InvalidStateTableException.class)
    public void marshallStateTableTest1() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode stateTableObject = objectMapper.readTree("{}");

        defaultStateTableHandlerImpl.marshallStateTable(stateTableObject);
    }

    /**
     * Test expected exception is throw when type_name is wrong
     */
    @Test(expectedExceptions = InvalidStateTableException.class)
    public void marshallStateTableTest2() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        instanceJson = FileUtils.readFileToString(FileUtils.getFile(
                "src","test", "resources", "marshallStateTableTest2.json"), Charset.defaultCharset());
        JsonNode stateTableObject = objectMapper.readTree(instanceJson);

        defaultStateTableHandlerImpl.marshallStateTable(stateTableObject);
    }

    /**
     * Test expected exception is throw when StateMachineType is wrong
     */
    @Test(expectedExceptions = InvalidStateTableException.class)
    public void marshallStateTableTest3() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        instanceJson = FileUtils.readFileToString(FileUtils.getFile(
                "src","test", "resources", "marshallStateTableTest3.json"), Charset.defaultCharset());
        JsonNode stateTableObject = objectMapper.readTree(instanceJson);

        defaultStateTableHandlerImpl.marshallStateTable(stateTableObject);
    }

    /**
     * Test expected exception is throw when value is wrong
     */
    @Test(expectedExceptions = InvalidStateTableException.class)
    public void marshallStateTableTest4() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        instanceJson = FileUtils.readFileToString(FileUtils.getFile(
                "src","test", "resources", "marshallStateTableTest4.json"), Charset.defaultCharset());
        JsonNode stateTableObject = objectMapper.readTree(instanceJson);

        defaultStateTableHandlerImpl.marshallStateTable(stateTableObject);
    }

    /**
     * Test expected exception is throw when rows is wrong
     */
    @Test(expectedExceptions = InvalidStateTableException.class)
    public void marshallStateTableTest5() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        instanceJson = FileUtils.readFileToString(FileUtils.getFile(
                "src","test", "resources", "marshallStateTableTest5.json"), Charset.defaultCharset());
        JsonNode stateTableObject = objectMapper.readTree(instanceJson);

        defaultStateTableHandlerImpl.marshallStateTable(stateTableObject);
    }

    /**
     * Test expected exception is throw when newState is wrong
     */
    @Test(expectedExceptions = InvalidStateTableException.class)
    public void marshallStateTableTest6() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        instanceJson = FileUtils.readFileToString(FileUtils.getFile(
                "src","test", "resources", "marshallStateTableTest6.json"), Charset.defaultCharset());
        JsonNode stateTableObject = objectMapper.readTree(instanceJson);

        defaultStateTableHandlerImpl.marshallStateTable(stateTableObject);
    }

    /**
     * Test expected exception is throw when transitionWorkflow is wrong
     */
    @Test(expectedExceptions = InvalidStateTableException.class)
    public void marshallStateTableTest7() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        instanceJson = FileUtils.readFileToString(FileUtils.getFile(
                "src","test", "resources", "marshallStateTableTest7.json"), Charset.defaultCharset());
        JsonNode stateTableObject = objectMapper.readTree(instanceJson);

        defaultStateTableHandlerImpl.marshallStateTable(stateTableObject);
    }

    /**
     * Test expected exception is throw when currentState is wrong
     */
    @Test(expectedExceptions = InvalidStateTableException.class)
    public void marshallStateTableTest8() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        instanceJson = FileUtils.readFileToString(FileUtils.getFile(
                "src","test", "resources", "marshallStateTableTest8.json"), Charset.defaultCharset());
        JsonNode stateTableObject = objectMapper.readTree(instanceJson);

        defaultStateTableHandlerImpl.marshallStateTable(stateTableObject);
    }

    /**
     * Test expected exception is throw when apiOperation is wrong
     */
    @Test(expectedExceptions = InvalidStateTableException.class)
    public void marshallStateTableTest9() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        instanceJson = FileUtils.readFileToString(FileUtils.getFile(
                "src","test", "resources", "marshallStateTableTest9.json"), Charset.defaultCharset());
        JsonNode stateTableObject = objectMapper.readTree(instanceJson);

        defaultStateTableHandlerImpl.marshallStateTable(stateTableObject);
    }


    /**
     * Test expected exception is throw when newState is null
     */
    @Test(expectedExceptions = InvalidStateTableException.class)
    public void marshallStateTableTest10() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        instanceJson = FileUtils.readFileToString(FileUtils.getFile(
                "src","test", "resources", "marshallStateTableTest10.json"), Charset.defaultCharset());
        JsonNode stateTableObject = objectMapper.readTree(instanceJson);

        defaultStateTableHandlerImpl.marshallStateTable(stateTableObject);
    }

    /**
     * Test expected exception is throw when transitionWorkflow is null
     */
    @Test(expectedExceptions = InvalidStateTableException.class)
    public void marshallStateTableTest11() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        instanceJson = FileUtils.readFileToString(FileUtils.getFile(
                "src","test", "resources", "marshallStateTableTest11.json"), Charset.defaultCharset());
        JsonNode stateTableObject = objectMapper.readTree(instanceJson);

        defaultStateTableHandlerImpl.marshallStateTable(stateTableObject);
    }

    /**
     * Test expected exception is throw when currentState is null
     */
    @Test(expectedExceptions = InvalidStateTableException.class)
    public void marshallStateTableTest12() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        instanceJson = FileUtils.readFileToString(FileUtils.getFile(
                "src","test", "resources", "marshallStateTableTest12.json"), Charset.defaultCharset());
        JsonNode stateTableObject = objectMapper.readTree(instanceJson);

        defaultStateTableHandlerImpl.marshallStateTable(stateTableObject);
    }

    /**
     * Test expected exception is throw when apiOperation is null
     */
    @Test(expectedExceptions = InvalidStateTableException.class)
    public void marshallStateTableTest13() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        instanceJson = FileUtils.readFileToString(FileUtils.getFile(
                "src","test", "resources", "marshallStateTableTest13.json"), Charset.defaultCharset());
        JsonNode stateTableObject = objectMapper.readTree(instanceJson);

        defaultStateTableHandlerImpl.marshallStateTable(stateTableObject);
    }

    /**
     * Test expected to succeed
     */
    @Test
    public void marshallStateTableTestSuccess1() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        instanceJson = FileUtils.readFileToString(FileUtils.getFile(
                "src","test", "resources", "marshallStateTableTestSuccess1.json"), Charset.defaultCharset());
        JsonNode stateTableObject = objectMapper.readTree(instanceJson);

        defaultStateTableHandlerImpl.marshallStateTable(stateTableObject);
    }

    /**
     * Test expected to succeed
     */
    @Test
    public void marshallStateTableTestSuccess2() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        instanceJson = FileUtils.readFileToString(FileUtils.getFile(
                "src","test", "resources", "marshallStateTableTestSuccess2.json"), Charset.defaultCharset());
        JsonNode stateTableObject = objectMapper.readTree(instanceJson);

        defaultStateTableHandlerImpl.marshallStateTable(stateTableObject);
    }

    /**
     * Test expected exception because of the redundant state machine(none is a dangling state)
     */
    @Test(expectedExceptions = InvalidStateTableException.class)
    public void marshallStateTableTestFsmFail1() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        instanceJson = FileUtils.readFileToString(FileUtils.getFile(
                "src","test", "resources", "marshallStateTableTestFsmFail1.json"), Charset.defaultCharset());
        JsonNode stateTableObject = objectMapper.readTree(instanceJson);

        defaultStateTableHandlerImpl.marshallStateTable(stateTableObject);
    }

    /**
     * Test expected to succeed, state machine A---1--->A, B---2---->C---3--->B
     */
    @Test
    public void marshallStateTableTestSuccess3() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        instanceJson = FileUtils.readFileToString(FileUtils.getFile(
                "src","test", "resources", "marshallStateTableTestSuccess4.json"), Charset.defaultCharset());
        JsonNode stateTableObject = objectMapper.readTree(instanceJson);

        defaultStateTableHandlerImpl.marshallStateTable(stateTableObject);
    }

    /**
     * Test expected to succeed
     */
    @Test
    public void validateServiceTransitionSuccess1() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        instanceJson = FileUtils.readFileToString(FileUtils.getFile(
                "src","test", "resources", "marshallStateTableTestSuccess1.json"), Charset.defaultCharset());
        JsonNode stateTableObject = objectMapper.readTree(instanceJson);

        StateTable table = defaultStateTableHandlerImpl.marshallStateTable(stateTableObject);

        String st = defaultStateTableHandlerImpl.validateServiceTransition("none", "create", table);
        Assert.assertEquals(st, "create");
    }

    /**
     * Test expected to succeed
     */
    @Test
    public void validateServiceTransitionSuccess2() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        instanceJson = FileUtils.readFileToString(FileUtils.getFile(
                "src","test", "resources", "marshallStateTableTestSuccess1.json"), Charset.defaultCharset());
        JsonNode stateTableObject = objectMapper.readTree(instanceJson);

        StateTable table = defaultStateTableHandlerImpl.marshallStateTable(stateTableObject);

        String st = defaultStateTableHandlerImpl.validateServiceTransition("created", "deploy", table);
        Assert.assertEquals(st, "deployCreated");
    }

    /**
     * Test expected to succeed
     */
    @Test
    public void validateServiceTransitionSuccess3() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        instanceJson = FileUtils.readFileToString(FileUtils.getFile(
                "src","test", "resources", "marshallStateTableTestSuccess4.json"), Charset.defaultCharset());
        JsonNode stateTableObject = objectMapper.readTree(instanceJson);

        StateTable table = defaultStateTableHandlerImpl.marshallStateTable(stateTableObject);

        String st = defaultStateTableHandlerImpl.validateServiceTransition("created", "deploy", table);
        Assert.assertEquals(st, "deployCreated");
    }

    /**
     * Test expected exception
     */
    @Test(expectedExceptions = InvalidTransitionException.class)
    public void validateServiceTransitionFail1() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        instanceJson = FileUtils.readFileToString(FileUtils.getFile(
                "src","test", "resources", "marshallStateTableTestSuccess1.json"), Charset.defaultCharset());
        JsonNode stateTableObject = objectMapper.readTree(instanceJson);

        StateTable table = defaultStateTableHandlerImpl.marshallStateTable(stateTableObject);

        defaultStateTableHandlerImpl.validateServiceTransition("none", "deploy", table);
    }

    /**
     * Test expected exception
     */
    @Test(expectedExceptions = InvalidTransitionException.class)
    public void validateServiceTransitionFail2() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        instanceJson = FileUtils.readFileToString(FileUtils.getFile(
                "src","test", "resources", "marshallStateTableTestSuccess1.json"), Charset.defaultCharset());
        JsonNode stateTableObject = objectMapper.readTree(instanceJson);

        StateTable table = defaultStateTableHandlerImpl.marshallStateTable(stateTableObject);

        defaultStateTableHandlerImpl.validateServiceTransition("created", "create", table);
    }

    /**
     * Test expected exception because of the wrong api operation
     */
    @Test(expectedExceptions = InvalidInputException.class)
    public void validateServiceTransitionFail3() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        instanceJson = FileUtils.readFileToString(FileUtils.getFile(
                "src","test", "resources", "marshallStateTableTestSuccess1.json"), Charset.defaultCharset());
        JsonNode stateTableObject = objectMapper.readTree(instanceJson);

        StateTable table = defaultStateTableHandlerImpl.marshallStateTable(stateTableObject);

        defaultStateTableHandlerImpl.validateServiceTransition("none", "AAAA", table);
    }

    /**
     * Test expected exception because of the wrong state
     */
    @Test(expectedExceptions = InvalidInputException.class)
    public void validateServiceTransitionFail4() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        instanceJson = FileUtils.readFileToString(FileUtils.getFile(
                "src","test", "resources", "marshallStateTableTestSuccess1.json"), Charset.defaultCharset());
        JsonNode stateTableObject = objectMapper.readTree(instanceJson);

        StateTable table = defaultStateTableHandlerImpl.marshallStateTable(stateTableObject);

        defaultStateTableHandlerImpl.validateServiceTransition("AAAA", "deploy", table);
    }

}
