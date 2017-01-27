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
