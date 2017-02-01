
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
