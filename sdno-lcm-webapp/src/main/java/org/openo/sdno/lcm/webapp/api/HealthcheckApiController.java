/*
 * Copyright 2016-2017 Huawei Technologies Co., Ltd.
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

package org.openo.sdno.lcm.webapp.api;

import org.openo.sdno.lcm.controller.HealthcheckApiControllerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2017-01-11T11:14:05.407Z")

@Controller
public class HealthcheckApiController implements HealthcheckApi {
	
	HealthcheckApiControllerFacade healthcheckApiControllerFacade;

    public ResponseEntity<Void> nsHealthcheck() {

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Autowired
	public void setHealthcheckApiControllerFacade(HealthcheckApiControllerFacade healthcheckApiControllerFacade) {
		this.healthcheckApiControllerFacade = healthcheckApiControllerFacade;
	}

}
