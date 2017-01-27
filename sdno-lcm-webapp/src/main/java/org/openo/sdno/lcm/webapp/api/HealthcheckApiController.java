
package org.openo.sdno.lcm.webapp.api;

import org.openo.sdno.lcm.restclient.LocalMsbRestClientImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2017-01-11T11:14:05.407Z")

@Controller
public class HealthcheckApiController implements HealthcheckApi {

    public ResponseEntity<Void> nsHealthcheck() {
        LocalMsbRestClientImpl localMsbRestClientImpl = new LocalMsbRestClientImpl();
        localMsbRestClientImpl.doOperation();
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
