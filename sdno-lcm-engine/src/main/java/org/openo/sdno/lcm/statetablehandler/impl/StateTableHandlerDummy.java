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

import java.util.Arrays;
import java.util.List;

import org.openo.sdno.lcm.statetablehandler.StateTableHandler;
import org.openo.sdno.lcm.statetablehandler.model.StateTable;
import org.springframework.stereotype.Component;

// TODO replace this with a proper implementation
@Component
public class StateTableHandlerDummy implements StateTableHandler {

	@Override
	public String validateServiceTransition(String currentStateId, String transitionId, StateTable stateTable) {

		String targetStateId = "provisioned";

		switch (transitionId) {
		case "create":
			targetStateId = "created";
			break;

		default:
			break;
		}

		return targetStateId;
	}

	@Override
	public List<String> getAllowedTransitions(String stateId, StateTable stateTable) {

		return Arrays.asList(new String[] { "This is just the dummy table handler" });
	}

}
