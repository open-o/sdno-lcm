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

package org.openo.sdno.lcm.genericclient;

import java.util.List;
import org.apache.http.HttpResponse;
import io.swagger.models.HttpMethod;
import io.swagger.models.parameters.HeaderParameter;
import io.swagger.models.parameters.PathParameter;
import io.swagger.models.parameters.QueryParameter;
import com.fasterxml.jackson.databind.JsonNode;

import org.openo.sdno.lcm.exception.ExternalComponentException;

public interface GenericApiClient {

    /**
     * calling one service API and return the response
     *
     * @param apiUrl the original api url
     * @param method HTTP method to be used
     * @param contentTypeValue the value of ContentType header
     * @param properties node properties
     * @param pathParameters Path Parameters
     * @param queryParameters Query Parameters
     * @param headers header parameters in swagger
     * @param body the request body (in JSON format)
     * @return HttpResponse from the service to be called.
     *         Note that ExternalComponentException will be thrown if exceptions happen during service call;
     */
    HttpResponse execute(final String apiUrl, final HttpMethod method,
                                final String contentTypeValue, final JsonNode properties,
                                final List<PathParameter> pathParameters, List<QueryParameter> queryParameters,
                                final List<HeaderParameter> headers, final JsonNode body);
}
