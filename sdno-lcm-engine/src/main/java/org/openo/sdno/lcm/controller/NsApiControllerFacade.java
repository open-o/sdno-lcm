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

package org.openo.sdno.lcm.controller;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openo.sdno.lcm.engine.LcmStateEngine;
import org.springframework.stereotype.Component;

@Component
public class NsApiControllerFacade {

	private final Logger log = Logger.getLogger("NsApiControllerFacade");

	public void nsCreationPost(Map<String, String> params) {

		log.info("~~~~~ NsApiControllerFacade - nsCreationPost ~~~~~");
		log.severe("info log should appear next... isInfoEnabled:" + log.isLoggable(Level.INFO));
		log.info(params.toString());
		LcmStateEngine.execute(params);
		return;
	}

}
