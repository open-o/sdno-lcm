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

package org.openo.sdno.lcm.model.workplan;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.models.HttpMethod;
import io.swagger.models.Swagger;
import org.openo.sdno.lcm.templatemodel.service.Node;

import static com.google.common.base.Preconditions.*;

/**
 * This class represents a work item
 */
public class WorkItem {

    private final Node node;  //we mainly use node properties from service instance
    private final Swagger swaggerSpec;  //swagger specification
    private final JsonNode mapperSpec;  //mapper specification (from node property to API)
    private final String apiUrl;  //API URL
    private final HttpMethod method;  //Http method to be used

    String response;  //response from SDN-O micro-service when executing this work item.

    //constructor
    public WorkItem(final Node node, final Swagger swaggerSpec, final JsonNode mapperSpec,
                    final String apiUrl, final HttpMethod method) {
        checkNotNull(node, "Each work item must have one related node.");
        checkNotNull(swaggerSpec, "Each work item must have one corresponding swagger specification.");
        checkNotNull(mapperSpec, "Each work item must have one corresponding mapping specification.");
        checkNotNull(method, "Each work item must have one corresponding HttpMethod.");
        checkArgument((apiUrl!=null)&&(apiUrl.length()>0), "API Url cannot be null or empty.");

        this.node = node;
        this.swaggerSpec = swaggerSpec;
        this.mapperSpec = mapperSpec;
        this.apiUrl = apiUrl;
        this.method = method;
    }

    //getters
    public Node getNode() {
        return node;
    }

    public Swagger getSwaggerSpec() {
        return swaggerSpec;
    }

    public JsonNode getMapperSpec() {
        return mapperSpec;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getResponse() {
        return response;
    }

    //getter and setter for the response of API call
    public void setResponse(String response) {
        this.response = response;
    }
}
