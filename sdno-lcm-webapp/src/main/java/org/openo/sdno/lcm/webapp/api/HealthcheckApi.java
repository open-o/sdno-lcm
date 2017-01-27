package org.openo.sdno.lcm.webapp.api;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2017-01-11T11:14:05.407Z")

@Api(value = "healthcheck", description = "the healthcheck API")
public interface HealthcheckApi {

    @ApiOperation(value = "", notes = "healthcheck returns OK", response = Void.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "query SDN-O service instance success.", response = Void.class) })
    @RequestMapping(value = "/healthcheck",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<Void> nsHealthcheck();

}
