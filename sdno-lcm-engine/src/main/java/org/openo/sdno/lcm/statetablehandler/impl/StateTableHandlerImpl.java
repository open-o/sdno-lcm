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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.openo.sdno.lcm.exception.InvalidInputException;
import org.openo.sdno.lcm.exception.InvalidStateTableException;
import org.openo.sdno.lcm.exception.InvalidTransitionException;
import org.openo.sdno.lcm.statetablehandler.StateTableHandler;
import org.openo.sdno.lcm.statetablehandler.model.StateTable;
import org.openo.sdno.lcm.templatemodel.statetable.Row;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Class implements the state table operations.
 */
@Component
public class StateTableHandlerImpl implements StateTableHandler {

    /*
     * (non-Javadoc)
     * 
     * @see org.openo.sdno.lcm.statetablehandler.StateTableHandler#
     * validateServiceTransition(java.lang. String, java.lang.String,
     * org.openo.sdno.lcm.statetablehandler.model.StateTable)
     */
    @Override
    public String validateServiceTransition(String currentStateId, String apiOperation, StateTable stateTable) {
        List<Row> rowList = stateTable.getRows();
        List<String> allowedState = new ArrayList<>();

        // validate the currentState
        if (!validateCurrentState(currentStateId, stateTable)) {
            throw new InvalidInputException("Input params must contain valid current state id ");
        }

        // validate the apiOperation
        if (!validateApiOperation(apiOperation, stateTable)) {
            throw new InvalidInputException(
                    String.format("Input params must contain valid apiOperation;  apiOperation: %s", apiOperation));
        }

        Optional<Row> serviceTransition = rowList.stream().filter(
                row -> currentStateId.equals(row.getCurrentState()) && apiOperation.equals(row.getApiOperation()))
                .findFirst();

        if (serviceTransition.isPresent()) {
            String transitionWorkflow = serviceTransition.get().getTransitionWorkflow();
            if (transitionWorkflow.equalsIgnoreCase("ERR") || transitionWorkflow.equalsIgnoreCase("error")) {
                throw new InvalidTransitionException("The input corresponds to an error transition");
            }
            return transitionWorkflow;
        }

        // if check fails (we can print the allowed api operations for the
        // state)
        allowedState = getAllowedApiOperations(currentStateId, stateTable);

        throw new InvalidTransitionException("Input params must contain valid transition id"
                + "Allowed api operations are: " + allowedState.toString());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.openo.sdno.lcm.statetablehandler.StateTableHandler#
     * getAllowedApiOperations(java.lang. String,
     * org.openo.sdno.lcm.statetablehandler.model.StateTable)
     */
    @Override
    public List<String> getAllowedApiOperations(String currentState, StateTable stateTable) {

        List<Row> rowList = stateTable.getRows();
        List<String> apiOperation = new ArrayList<>();

        for (Row row : rowList) {
            if (currentState.equals(row.getCurrentState())) {
                apiOperation.add(row.getApiOperation());
            }
        }

        if (apiOperation.size() == 0) {
            throw new InvalidInputException("Input params must contain valid current state ");
        }

        return apiOperation;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.openo.sdno.lcm.statetablehandler.StateTableHandler#marshallStateTable
     * (com.fasterxml. jackson.databind.JsonNode)
     */
    @Override
    public StateTable marshallStateTable(JsonNode stateTableObject) {

        String type = stateTableObject.path("type_name").asText();

        // assert if type is not StateMachineType.
        if (type.isEmpty() || !type.equals("StateMachineType")) {
            throw new InvalidStateTableException("Input params must contain StateMachineType ");
        }

        JsonNode value = (JsonNode) stateTableObject.get("value");
        if (value == null || value.isMissingNode()) {
            throw new InvalidStateTableException("Input params must contain StateMachine value ");
        }

        JsonNode rows = (JsonNode) value.get("rows");
        if (rows == null || rows.isMissingNode()) {
            throw new InvalidStateTableException("Input params must contain StateMachine table ");
        }

        StateTable table = new StateTable();
        List<Row> rowList = new ArrayList<>();
        Set<String> stateSet = new HashSet<>();

        for (JsonNode node : rows) {
            Row rowValue = new Row();
            rowValue.setNewState(node.path("newState").asText());
            rowValue.setTransitionWorkflow(node.path("transitionWorkflow").asText());
            rowValue.setCurrentState(node.path("currentState").asText());
            rowValue.setApiOperation(node.path("apiOperation").asText());

            if (rowValue.getNewState().isEmpty() || rowValue.getTransitionWorkflow().isEmpty()
                    || rowValue.getCurrentState().isEmpty() || rowValue.getApiOperation().isEmpty()) {
                throw new InvalidStateTableException("Input params must contain non empty value ");
            }

            stateSet.add(rowValue.getNewState());
            stateSet.add(rowValue.getCurrentState());

            rowList.add(rowValue);
        }

        if (!validateStateTable(rowList, stateSet)) {
            throw new InvalidStateTableException("Input params must contain valid state machine ");
        }

        table.setRows(rowList);
        return table;
    }

    /**
     * Validate the state table
     *
     * @param rowList
     *            list of all the status table entries
     * @param stateSet
     *            list of states
     * @return true if the state table is valid else false
     */
    private boolean validateStateTable(final List<Row> rowList, Set<String> stateSet) {

        // Atlease 2 states are expected.
        if (stateSet.size() < 2) {
            return false;
        }

        for (Row rowValue : rowList) {
            // choose the transition which covers 2 states
            if ((!rowValue.getNewState().equals(rowValue.getCurrentState())) && !stateSet.isEmpty()) {
                stateSet.remove(rowValue.getNewState());
            }
        }

        // if all the states are involved in then no state is redundant
        if ((stateSet.size() == 0) || ((stateSet.size() == 1) && stateSet.contains("none"))) {
            return true;
        }

        return false;
    }

    /**
     * Validate the current state from the state table
     *
     * @param currentStateId
     *            current state id
     * @param stateTable
     *            allowed state table
     * @return true if current state is valid else false
     */
    private boolean validateCurrentState(final String currentStateId, final StateTable stateTable) {
        List<Row> rowList = stateTable.getRows();
        for (Row row : rowList) {
            if (currentStateId.equals(row.getCurrentState())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Validate the allowed api operation from the state table
     *
     * @param apiOperation
     *            given api operation
     * @param stateTable
     *            allowed state table
     * @return true if api operation is valid else false
     */
    private boolean validateApiOperation(final String apiOperation, final StateTable stateTable) {
        List<Row> rowList = stateTable.getRows();
        for (Row row : rowList) {
            if (apiOperation.equals(row.getApiOperation())) {
                return true;
            }
        }

        return false;
    }
}
