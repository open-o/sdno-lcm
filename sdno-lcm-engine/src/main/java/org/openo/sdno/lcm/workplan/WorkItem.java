/*
 * Copyright 2017 Huawei Technologies Co., Ltd.
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

package org.openo.sdno.lcm.workplan;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.httpclient.HttpMethod;
import io.swagger.models.Swagger; 

import org.openo.sdno.lcm.templatemodel.service.Node;

/**
 * This class represents a work item
 */
public class WorkItem {

    Node nodeProperties;  //node properties from service instance
    Swagger swaggerSpec;  //swagger specification
    JsonNode mapperSpec;  //mapper specification (from node property to API)
    String apiUrl;  //API URL
    HttpMethod method;  //Http method to be used

    String response;  //response from SDN-O micro-service when executing this work item.

    //constructor

    //getters & setters
}
