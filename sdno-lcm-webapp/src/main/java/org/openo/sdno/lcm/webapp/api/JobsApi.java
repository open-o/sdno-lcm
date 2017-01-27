package org.openo.sdno.lcm.webapp.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.openo.sdno.lcm.webapp.model.JobQueryResponse;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2017-01-11T11:14:05.407Z")

@Api(value = "jobs", description = "the jobs API")
public interface JobsApi {

    @ApiOperation(value = "query one job that terminates or instantiates one SDN-O service", notes = "it will return the status of the job", response = JobQueryResponse.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "query job success.", response = JobQueryResponse.class),
        @ApiResponse(code = 400, message = "query job failure as parameters invalid.", response = JobQueryResponse.class),
        @ApiResponse(code = 401, message = "unauthorized.", response = JobQueryResponse.class),
        @ApiResponse(code = 404, message = "query job failure as can't reach server.", response = JobQueryResponse.class),
        @ApiResponse(code = 500, message = "query job failure as inner error.", response = JobQueryResponse.class) })
    @RequestMapping(value = "/jobs/{jobid}",
        produces = { "application/json;charset=UTF-8" }, 
        consumes = { "application/json;charset=UTF-8" },
        method = RequestMethod.GET)
    ResponseEntity<JobQueryResponse> jobQueryGet(@ApiParam(value = "ID of the job to be queried",required=true ) @PathVariable("jobid") String jobid);

}
