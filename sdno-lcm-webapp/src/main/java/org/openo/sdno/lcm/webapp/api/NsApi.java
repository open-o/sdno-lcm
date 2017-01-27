package org.openo.sdno.lcm.webapp.api;

import org.openo.sdno.lcm.webapp.model.LongOperationResponse;
import org.openo.sdno.lcm.webapp.model.NsCreationRequest;
import org.openo.sdno.lcm.webapp.model.NsCreationResponse;
import org.openo.sdno.lcm.webapp.model.NsInstanceQueryResponse;
import org.openo.sdno.lcm.webapp.model.NsInstantiationRequest;
import org.openo.sdno.lcm.webapp.model.NsTerminationRequest;
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

@Api(value = "ns", description = "the ns API")
public interface NsApi {

    @ApiOperation(value = "create SDN-O service instance based on a template", notes = "it will create a SDN-O service instance based on a template specified in the request.", response = NsCreationResponse.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "create SDN-O service instance success.", response = NsCreationResponse.class),
        @ApiResponse(code = 400, message = "create SDN-O service instance failure as parameters invalid.", response = NsCreationResponse.class),
        @ApiResponse(code = 401, message = "unauthorized.", response = NsCreationResponse.class),
        @ApiResponse(code = 404, message = "create SDN-O service instance failure as can't reach server.", response = NsCreationResponse.class),
        @ApiResponse(code = 500, message = "create SDN-O service instance failure as internal error.", response = NsCreationResponse.class) })
    @RequestMapping(value = "/ns",
        produces = { "application/json;charset=UTF-8" }, 
        consumes = { "application/json;charset=UTF-8" },
        method = RequestMethod.POST)
    ResponseEntity<NsCreationResponse> nsCreationPost(@ApiParam(value = "the request used to create a SDN-O service instance" ,required=true ) @RequestBody NsCreationRequest nsRequest);


    @ApiOperation(value = "delete a SDN-O service instance", notes = "it will delete a SDN-O service instance.", response = Void.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "delete SDN-O service instance success.", response = Void.class),
        @ApiResponse(code = 400, message = "delete SDN-O service instance failure as parameters invalid.", response = Void.class),
        @ApiResponse(code = 401, message = "unauthorized.", response = Void.class),
        @ApiResponse(code = 404, message = "delete SDN-O service instance failure as can't reach server.", response = Void.class),
        @ApiResponse(code = 500, message = "delete SDN-O service instance failure as inner error.", response = Void.class) })
    @RequestMapping(value = "/ns/{instanceid}",
        produces = { "application/json;charset=UTF-8" }, 
        consumes = { "application/json;charset=UTF-8" },
        method = RequestMethod.DELETE)
    ResponseEntity<Void> nsDeletionDelete(@ApiParam(value = "ID of the SDN-O service instance to be deleted",required=true ) @PathVariable("instanceid") String instanceid);


    @ApiOperation(value = "instantiate SDN-O service instance with parameters", notes = "it will instantiate a SDN-O service instance based on parameters in the request.", response = LongOperationResponse.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 202, message = "SDN-O service instance is under-instantiating. Job id is returned.", response = LongOperationResponse.class),
        @ApiResponse(code = 400, message = "instantiate SDN-O service instance failure as parameters invalid.", response = LongOperationResponse.class),
        @ApiResponse(code = 401, message = "unauthorized.", response = LongOperationResponse.class),
        @ApiResponse(code = 404, message = "instantiate SDN-O service instance failure as can't reach server.", response = LongOperationResponse.class),
        @ApiResponse(code = 500, message = "instantiate SDN-O service instance failure as internal error.", response = LongOperationResponse.class) })
    @RequestMapping(value = "/ns/{instanceid}/instantiate",
        produces = { "application/json;charset=UTF-8" }, 
        consumes = { "application/json;charset=UTF-8" },
        method = RequestMethod.POST)
    ResponseEntity<LongOperationResponse> nsInstantiationPost(@ApiParam(value = "ID of the SDN-O service instance to be instantiated",required=true ) @PathVariable("instanceid") String instanceid,@ApiParam(value = "the request used to instantiate a SDN-O service instance" ,required=true ) @RequestBody NsInstantiationRequest nsInstantiationRequest);


    @ApiOperation(value = "query one SDN-O service instance", notes = "it will return the details of a specific SDN-O service instance", response = NsInstanceQueryResponse.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "query SDN-O service instance success.", response = NsInstanceQueryResponse.class),
        @ApiResponse(code = 400, message = "query SDN-O service instance failure as parameters invalid.", response = NsInstanceQueryResponse.class),
        @ApiResponse(code = 401, message = "unauthorized.", response = NsInstanceQueryResponse.class),
        @ApiResponse(code = 404, message = "query SDN-O service instance failure as can't reach server.", response = NsInstanceQueryResponse.class),
        @ApiResponse(code = 500, message = "query SDN-O service instance failure as inner error.", response = NsInstanceQueryResponse.class) })
    @RequestMapping(value = "/ns/{instanceid}",
        produces = { "application/json;charset=UTF-8" }, 
        consumes = { "application/json;charset=UTF-8" },
        method = RequestMethod.GET)
    ResponseEntity<NsInstanceQueryResponse> nsQueryGet(@ApiParam(value = "ID of the SDN-O service instance to be queried",required=true ) @PathVariable("instanceid") String instanceid);


    @ApiOperation(value = "terminate a SDN-O service instance", notes = "it will terminate a SDN-O service instance with some graceful termination timeout.", response = LongOperationResponse.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 202, message = "SDN-O service instance is under-terminating. Job id is returned.", response = LongOperationResponse.class),
        @ApiResponse(code = 400, message = "terminate SDN-O service instance failure as parameters invalid.", response = LongOperationResponse.class),
        @ApiResponse(code = 401, message = "unauthorized.", response = LongOperationResponse.class),
        @ApiResponse(code = 404, message = "terminate SDN-O service instance failure as can't reach server.", response = LongOperationResponse.class),
        @ApiResponse(code = 500, message = "terminate SDN-O service instance failure as internal error.", response = LongOperationResponse.class) })
    @RequestMapping(value = "/ns/{instanceid}/terminate",
        produces = { "application/json;charset=UTF-8" }, 
        consumes = { "application/json;charset=UTF-8" },
        method = RequestMethod.POST)
    ResponseEntity<LongOperationResponse> nsTerminationPost(@ApiParam(value = "ID of the SDN-O service instance to be terminated",required=true ) @PathVariable("instanceid") String instanceid,@ApiParam(value = "the request used to terminate a SDN-O service instance" ,required=true ) @RequestBody NsTerminationRequest nsTerminationRequest);

}
