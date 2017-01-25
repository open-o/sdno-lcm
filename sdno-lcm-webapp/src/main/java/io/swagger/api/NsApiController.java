package io.swagger.api;

import java.util.logging.Logger;

import org.openo.sdno.lcm.controller.NsApiControllerFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.annotations.ApiParam;
import io.swagger.model.LongOperationResponse;
import io.swagger.model.NsCreationRequest;
import io.swagger.model.NsCreationResponse;
import io.swagger.model.NsInstanceQueryResponse;
import io.swagger.model.NsInstantiationRequest;
import io.swagger.model.NsTerminationRequest;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2017-01-11T11:14:05.407Z")

@Controller
public class NsApiController implements NsApi {

	private final Logger log = Logger.getLogger("NsApiController");

	public ResponseEntity<NsCreationResponse> nsCreationPost(@ApiParam(value = "the request used to create a SDN-O service instance" ,required=true ) @RequestBody NsCreationRequest nsRequest) {
        // do some magic!
		log.severe("~~~~~ NsApiController - nsCreationPost ~~~~~");
		NsApiControllerFacade nsApiControllerFacade = new NsApiControllerFacade();
		nsApiControllerFacade.nsCreationPost();
        return new ResponseEntity<NsCreationResponse>(HttpStatus.OK);
    }

    public ResponseEntity<Void> nsDeletionDelete(@ApiParam(value = "ID of the SDN-O service instance to be deleted",required=true ) @PathVariable("instanceid") String instanceid) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<LongOperationResponse> nsInstantiationPost(@ApiParam(value = "ID of the SDN-O service instance to be instantiated",required=true ) @PathVariable("instanceid") String instanceid,
        @ApiParam(value = "the request used to instantiate a SDN-O service instance" ,required=true ) @RequestBody NsInstantiationRequest nsInstantiationRequest) {
        // do some magic!
        return new ResponseEntity<LongOperationResponse>(HttpStatus.OK);
    }

    public ResponseEntity<NsInstanceQueryResponse> nsQueryGet(@ApiParam(value = "ID of the SDN-O service instance to be queried",required=true ) @PathVariable("instanceid") String instanceid) {
        // do some magic!
        return new ResponseEntity<NsInstanceQueryResponse>(HttpStatus.OK);
    }

    public ResponseEntity<LongOperationResponse> nsTerminationPost(@ApiParam(value = "ID of the SDN-O service instance to be terminated",required=true ) @PathVariable("instanceid") String instanceid,
        @ApiParam(value = "the request used to terminate a SDN-O service instance" ,required=true ) @RequestBody NsTerminationRequest nsTerminationRequest) {
        // do some magic!
        return new ResponseEntity<LongOperationResponse>(HttpStatus.OK);
    }

}
