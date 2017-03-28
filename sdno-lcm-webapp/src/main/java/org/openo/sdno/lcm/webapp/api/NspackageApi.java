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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2017-01-11T11:14:05.407Z")

@Api(value = "nspackage", description = "the nspackage API")
public interface NspackageApi {

    @ApiOperation(value = "delete a NS package from SDN-O", notes = "it will delete a (onboarded) NS package from SDN-O.", response = PackageManagementResponse.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "delete SDN-O NS package success.", response = PackageManagementResponse.class),
        @ApiResponse(code = 400, message = "delete SDN-O NS package failure as parameters invalid.", response = PackageManagementResponse.class),
        @ApiResponse(code = 401, message = "unauthorized.", response = PackageManagementResponse.class),
        @ApiResponse(code = 404, message = "delete SDN-O NS package failure as can't reach server.", response = PackageManagementResponse.class),
        @ApiResponse(code = 500, message = "delete SDN-O NS package failure as inner error.", response = PackageManagementResponse.class) })
    @RequestMapping(value = "/nspackage/{csarid}",
        produces = { "application/json;charset=UTF-8" }, 
        consumes = { "application/json;charset=UTF-8" },
        method = RequestMethod.DELETE)
    ResponseEntity<PackageManagementResponse> packageDeletionDelete(@ApiParam(value = "ID of the NS package to be deleted from SDN-O",required=true ) @PathVariable("csarid") String csarid);


    @ApiOperation(value = "onboarding a NS package", notes = "it will onboarding a NS package to SDN-O.", response = PackageManagementResponse.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "onboarding successfully.", response = PackageManagementResponse.class),
        @ApiResponse(code = 400, message = "onboarding SDN-O NS package failure as parameters invalid.", response = PackageManagementResponse.class),
        @ApiResponse(code = 401, message = "unauthorized.", response = PackageManagementResponse.class),
        @ApiResponse(code = 404, message = "onboarding SDN-O NS package failure as can't reach server.", response = PackageManagementResponse.class),
        @ApiResponse(code = 500, message = "onboarding SDN-O NS package failure as internal error.", response = PackageManagementResponse.class) })
    @RequestMapping(value = "/nspackage",
        produces = { "application/json;charset=UTF-8" }, 
        consumes = { "application/json;charset=UTF-8" },
        method = RequestMethod.POST)
    ResponseEntity<PackageManagementResponse> packageOnboardingPost(@ApiParam(value = "the request used to onboard a NS package to SDN-O" ,required=true ) @RequestBody PackageOnboardRequest nsRequest);

}
