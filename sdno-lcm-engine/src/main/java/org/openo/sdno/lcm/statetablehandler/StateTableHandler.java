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

package org.openo.sdno.lcm.statetablehandler;

import org.openo.sdno.lcm.exception.InvalidInputException;
import org.openo.sdno.lcm.exception.InvalidStateTableException;
import org.openo.sdno.lcm.exception.InvalidTransitionException;
import org.openo.sdno.lcm.statetablehandler.model.StateTable;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public interface StateTableHandler {

    /**
     * Check if the transitionId is in the allowedTransitions of the current
     * state.
     *
     * @param currentStateId current state id
     * @param apiOperation api operation
     * @param stateTable allowed state table
     *
     * @exception if
     *                check fails (we can print the allowed transitions for the
     *                user)
     * @exception if
     *                currentStateId or transitionId are not in the
     *                stateMachineModel
     * @exception if
     *                the stateMachineModel is invalid
     *
     * @return the the transitionWorkflow of the transition for success
     * @throws InvalidInputException
     * @throws InvalidTransitionException
     */
    String validateServiceTransition(String currentStateId, String apiOperation, StateTable stateTable);

    /**
     * Return the allowed api operations for the state
     *
     * @param currentState current state
     * @param stateTable allowed state table
     *
     * @exception if
     *                the state is not in the stateTable
     * @return the list of allowed api operations on success
     * @throws InvalidInputException
     */
    List<String> getAllowedApiOperations(String currentState, StateTable stateTable);

    /**
     * Return the state table.
     *
     * @param stateTableObject json object contains allowed state table
     *
     * @exception if
     *                the parsing of the stateTable encounters any issue.
     * @return the list of allowed transaction on success
     * @throws InvalidStateTableException
     */
    StateTable marshallStateTable(JsonNode stateTableObject);
}
