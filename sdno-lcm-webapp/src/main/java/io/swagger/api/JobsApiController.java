package io.swagger.api;

import io.swagger.model.JobQueryResponse;

import io.swagger.annotations.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2017-01-11T11:14:05.407Z")

@Controller
public class JobsApiController implements JobsApi {

    public ResponseEntity<JobQueryResponse> jobQueryGet(@ApiParam(value = "ID of the job to be queried",required=true ) @PathVariable("jobid") String jobid) {
        // do some magic!
        return new ResponseEntity<JobQueryResponse>(HttpStatus.OK);
    }

}
