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

import org.openo.sdno.lcm.webapp.model.PackageManagementResponse;
import org.openo.sdno.lcm.webapp.model.PackageOnboardRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.annotations.ApiParam;


@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2017-01-11T11:14:05.407Z")

@Controller
public class NspackageApiController implements NspackageApi {

    public ResponseEntity<PackageManagementResponse> packageDeletionDelete(@ApiParam(value = "ID of the NS package to be deleted from SDN-O",required=true ) @PathVariable("csarid") String csarid) {
        // do some magic!
        return new ResponseEntity<PackageManagementResponse>(HttpStatus.OK);
    }

    public ResponseEntity<PackageManagementResponse> packageOnboardingPost(@ApiParam(value = "the request used to onboard a NS package to SDN-O" ,required=true ) @RequestBody PackageOnboardRequest nsRequest) {
        // do some magic!
        return new ResponseEntity<PackageManagementResponse>(HttpStatus.OK);
    }

}
