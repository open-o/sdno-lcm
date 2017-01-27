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

import java.util.List;

public interface StateTableHandler {

	/**
	 * Check if the transitionId is in the allowedTransitions of the current
	 * state.
	 * 
	 * @param currentStateId
	 * @param transitionId
	 * @param stateTable
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
	 * @return the stateId of the destination state of the transition for
	 *         success
	 */
	String validateServiceTransition(String currentStateId, String transitionId, StateTable stateTable);

	/**
	 * return the allowed transition IDs for the state
	 * 
	 * @param stateId
	 * @param stateTable
	 * 
	 * @exception if
	 *                the state is not in the stateTable
	 * @return
	 */
	List<String> getAllowedTransitions(String stateId, StateTable stateTable);

}

class StateTable {
}
