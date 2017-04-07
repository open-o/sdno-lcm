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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.openo.sdno.lcm.controller.NsApiControllerFacade;
import org.openo.sdno.lcm.util.Mapper;
import org.openo.sdno.lcm.webapp.model.LongOperationResponse;
import org.openo.sdno.lcm.webapp.model.NsCreationRequest;
import org.openo.sdno.lcm.webapp.model.NsCreationResponse;
import org.openo.sdno.lcm.webapp.model.NsInstanceQueryResponse;
import org.openo.sdno.lcm.webapp.model.NsTerminationRequest;
import org.openo.sdno.lcm.webapp.model.SdnoTemplateParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.annotations.ApiParam;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2017-01-11T11:14:05.407Z")

@Controller
public class NsApiController implements NsApi {

    private final Logger log = Logger.getLogger("NsApiController");

    private NsApiControllerFacade nsApiControllerFacade;

    private Mapper mapper;

    public ResponseEntity<NsCreationResponse> nsCreationPost(
            @ApiParam(value = "the request used to create a SDN-O service instance", required = true) @RequestBody NsCreationRequest nsRequest) {

        log.fine("~~~~~ NsApiController - nsCreationPost ~~~~~");
        Map<String, Object> params = mapper.beanToMap(nsRequest);
        Map<String, Object> responseMap = nsApiControllerFacade.nsCreationPost(params);
        NsCreationResponse nsCreationResponse = mapper.mapToBean(NsCreationResponse.class, responseMap);
        ResponseEntity<NsCreationResponse> response =
                new ResponseEntity<NsCreationResponse>(nsCreationResponse, HttpStatus.CREATED);
        return response;
    }

    public ResponseEntity<Void> nsDeletionDelete(
            @ApiParam(value = "ID of the SDN-O service instance to be deleted", required = true) @PathVariable("instanceid") String instanceid) {
        Map<String, Object> params = new HashMap<>();
        params.put("instanceid", instanceid);
        nsApiControllerFacade.nsDeletionDelete(params);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<LongOperationResponse> nsInstantiationPost(
            @ApiParam(value = "ID of the SDN-O service instance to be instantiated", required = true) @PathVariable("instanceid") String instanceid,
            @ApiParam(value = "the request used to instantiate a SDN-O service instance", required = true) @RequestBody Map<String, Object> nsInstantiationRequest) {

        log.fine("~~~~~ NsApiController - nsInstantiationPost ~~~~~");
        Map<String, Object> params = mapper.beanToMap(nsInstantiationRequest);
        Map<String, Object> responseMap = nsApiControllerFacade.nsInstantiationPost(params);

        LongOperationResponse responseEntity = new LongOperationResponse();
        // return the service ID for the moment as it's not really an asynchronous operation
        responseEntity.setJobId((String)responseMap.get("jobId"));
        ResponseEntity<LongOperationResponse> response =
                new ResponseEntity<LongOperationResponse>(responseEntity, HttpStatus.OK);
        return response;
    }

    public ResponseEntity<NsInstanceQueryResponse> nsQueryGet(
            @ApiParam(value = "ID of the SDN-O service instance to be queried", required = true) @PathVariable("instanceid") String instanceid) {

        Map<String, Object> params = new HashMap<>();
        params.put("instanceid", instanceid);
        Map<String, Object> responseMap = nsApiControllerFacade.nsQueryGet(params);
        NsInstanceQueryResponse nsInstanceQueryResponse = new NsInstanceQueryResponse();
        nsInstanceQueryResponse.setId((String)responseMap.get("id"));
        nsInstanceQueryResponse.setDescription((String)responseMap.get("description"));
        nsInstanceQueryResponse.setName((String)responseMap.get("name"));
        nsInstanceQueryResponse.setNsdId((String)responseMap.get("templateId"));

        List<SdnoTemplateParameter> additionalParams = new ArrayList<>();
        SdnoTemplateParameter serviceStateParam = new SdnoTemplateParameter();
        serviceStateParam.setName("lifecycleState");
        serviceStateParam.setValue((String)responseMap.get("lifecycleState"));
        additionalParams.add(serviceStateParam);
        nsInstanceQueryResponse.setAdditionalParams(additionalParams);
        // TODO set remaining fields as 'additionalParams'
        ResponseEntity<NsInstanceQueryResponse> response =
                new ResponseEntity<NsInstanceQueryResponse>(nsInstanceQueryResponse, HttpStatus.OK);
        return response;
    }

    public ResponseEntity<LongOperationResponse> nsTerminationPost(
            @ApiParam(value = "ID of the SDN-O service instance to be terminated", required = true) @PathVariable("instanceid") String instanceid,
            @ApiParam(value = "the request used to terminate a SDN-O service instance", required = true) @RequestBody NsTerminationRequest nsTerminationRequest) {

        log.fine("~~~~~ NsApiController - nsTerminationPost ~~~~~");
        Map<String, Object> params = mapper.beanToMap(nsTerminationRequest);
        Map<String, Object> responseMap = nsApiControllerFacade.nsTerminationPost(params);
        LongOperationResponse responseEntity = new LongOperationResponse();
        // return the service ID for the moment as it's not really an asynchronous operation
        responseEntity.setJobId((String)responseMap.get("jobId"));
        ResponseEntity<LongOperationResponse> response =
                new ResponseEntity<LongOperationResponse>(responseEntity, HttpStatus.OK);
        return response;
    }

    @Autowired
    public void setNsApiControllerFacade(NsApiControllerFacade nsApiControllerFacade) {
        this.nsApiControllerFacade = nsApiControllerFacade;
    }

    public Mapper getMapper() {
        return mapper;
    }

    @Autowired
    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }

}
