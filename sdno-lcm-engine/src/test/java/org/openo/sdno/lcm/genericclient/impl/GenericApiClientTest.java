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

package org.openo.sdno.lcm.genericclient.impl;

import java.util.List;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.openo.sdno.lcm.exception.LcmInternalException;
import org.openo.sdno.lcm.exception.ExternalComponentException;

import org.apache.http.HttpResponse;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.Header;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.HttpEntity;

import io.swagger.models.parameters.HeaderParameter;
import io.swagger.models.parameters.PathParameter;
import io.swagger.models.parameters.QueryParameter;
import io.swagger.models.HttpMethod;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.JsonNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import org.testng.annotations.Test;
import static org.testng.Assert.*;
import static org.easymock.EasyMock.*;


@Test(groups = {"sdno-lcm-unit"})
@ContextConfiguration(locations = {"classpath:spring-test-config.xml"})
public class GenericApiClientTest extends AbstractTestNGSpringContextTests {
    @Autowired
    GenericApiClientImpl client;

    @Test
    public void testGenerateFinalApiUrlWithoutReplacement() {
        String result = client.generateFinalApiUrl("/test", null, null, null);
        assertEquals(result, "http://127.0.0.1:8080/test", "Unexpected path change occured.");
    }

    @Test
    public void testGenerateFinalApiUrlWithReplacement() {
        List<PathParameter> pathParameters = new ArrayList<PathParameter>();
        PathParameter p = new PathParameter();
        p.setName("uuid");
        pathParameters.add(p);

        JsonNode properties = null;
        try {
            properties = (JsonNode) new ObjectMapper().readTree("{\"id\": \"123456\"}");
        } catch(IOException e) {
            assertTrue(false, "Strange error happens when creating JsonNode from string.");
        }

        String result = client.generateFinalApiUrl("/test/{uuid}", properties, pathParameters, null);
        assertEquals(result, "http://127.0.0.1:8080/test/123456", "Unexpected path change occured.");
    }

    @Test(expectedExceptions = LcmInternalException.class,
          expectedExceptionsMessageRegExp ="GenericApiClient currently doesn't support query parameters..*")
    public void testGenerateFinalApiUrlWithQueryParameters() {
        List<PathParameter> pathParameters = new ArrayList<PathParameter>();
        PathParameter p = new PathParameter();
        p.setName("uuid");
        pathParameters.add(p);

        List<QueryParameter> queryParameters = new ArrayList<QueryParameter>();
        QueryParameter p2 = new QueryParameter();
        p2.setName("range");
        queryParameters.add(p2);

        JsonNode properties = null;
        try {
            properties = (JsonNode) new ObjectMapper().readTree("{\"id\": \"123456\"}");
        } catch(IOException e) {
            assertTrue(false, "Strange error happens when creating JsonNode from string.");
        }

        String result = client.generateFinalApiUrl("/test/{uuid}", properties, pathParameters, queryParameters);
        assertEquals(result, "http://127.0.0.1:8080/test/123456", "Unexpected path change occured.");
    }

    @Test(expectedExceptions = LcmInternalException.class,
          expectedExceptionsMessageRegExp ="GenericApiClient currently doesn't support multiple path parameters..*")
    public void testGenerateFinalApiUrlWithMultiplePathParameters() {
        List<PathParameter> pathParameters = new ArrayList<PathParameter>();
        PathParameter p1 = new PathParameter();
        p1.setName("uuid");
        pathParameters.add(p1);
        PathParameter p2 = new PathParameter();
        p2.setName("name");
        pathParameters.add(p2);

        JsonNode properties = null;
        try {
            properties = (JsonNode) new ObjectMapper().readTree("{\"id\": \"123456\"}");
        } catch(IOException e) {
            assertTrue(false, "Strange error happens when creating JsonNode from string.");
        }

        String result = client.generateFinalApiUrl("/test/{uuid}", properties, pathParameters, null);
        assertEquals(result, "http://127.0.0.1:8080/test/123456", "Unexpected path change occured.");
    }

    @Test
    public void testPrepareHttpRequestGet() {
        HttpUriRequest request = client.prepareHttpRequest("/test", HttpMethod.GET, "application/json",
                                                       null, null, null, null, null);

        assertEquals(request.getURI().toString(), "http://127.0.0.1:8080/test", "URI in request is wrong.");
        assertEquals(request.getMethod(), "GET", "HttpMethod is wrong.");

        Header[] headers = request.getAllHeaders();
        assertEquals(headers[0].getValue(), "application/json", "Content-Type header is wrong.");
    }

    @Test
    public void testPrepareHttpRequestDelete() {
        List<PathParameter> pathParameters = new ArrayList<PathParameter>();
        PathParameter p = new PathParameter();
        p.setName("uuid");
        pathParameters.add(p);

        JsonNode properties = null;
        try {
            properties = (JsonNode) new ObjectMapper().readTree("{\"id\": \"123456\"}");
        } catch(IOException e) {
            assertTrue(false, "Strange error happens when creating JsonNode from string.");
        }

        HttpUriRequest request = client.prepareHttpRequest("/test/{uuid}", HttpMethod.DELETE, "application/json",
                                                       properties, pathParameters, null, null, null);

        assertEquals(request.getURI().toString(), "http://127.0.0.1:8080/test/123456", "URI in request is wrong.");
        assertEquals(request.getMethod(), "DELETE", "HttpMethod is wrong.");

        Header[] headers = request.getAllHeaders();
        assertEquals(headers[0].getValue(), "application/json", "Content-Type header is wrong.");
    }


    @Test
    public void testPrepareHttpRequestPost() {
        List<PathParameter> pathParameters = new ArrayList<PathParameter>();
        PathParameter p = new PathParameter();
        p.setName("uuid");
        pathParameters.add(p);

        JsonNode body = null;
        try {
            body = (JsonNode) new ObjectMapper().readTree("{\"name\": \"MyName\"}");
        } catch(IOException e) {
            assertTrue(false, "Strange error happens when creating JsonNode from string.");
        }

        HttpUriRequest request = client.prepareHttpRequest("/test", HttpMethod.POST, "application/json",
                                                       null, null, null, null, body);

        assertEquals(request.getURI().toString(), "http://127.0.0.1:8080/test", "URI in request is wrong.");
        assertEquals(request.getMethod(), "POST", "HttpMethod is wrong.");

        Header[] headers = request.getAllHeaders();
        assertEquals(headers[0].getValue(), "application/json", "Content-Type header is wrong.");

        //check body content
        HttpPost postRequest = (HttpPost) request;
        HttpEntity entity = postRequest.getEntity();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            entity.writeTo(os);
        } catch (IOException e) {
            assertTrue(false, "Strange error happens when reading request body.");
        }
        String bodyStr = new String(os.toByteArray());

        JsonNode newBody = null;
        try {
            newBody = (JsonNode) new ObjectMapper().readTree(bodyStr);
        } catch(IOException e) {
            assertTrue(false, "Strange error happens when creating JsonNode from string.");
        }
        assertTrue(((null!=body)&&(null!=newBody)), "Json object is not constructed successfully.");
        assertEquals(body.get("name").asText(), newBody.get("name").asText(), "Body content is changed unexpectedly.");
    }

    @Test(expectedExceptions = LcmInternalException.class,
          expectedExceptionsMessageRegExp = "GenericApiClient still doesn't support http method.*")
    public void testPrepareHttpRequestHead() {
        HttpUriRequest request = client.prepareHttpRequest("/test", HttpMethod.HEAD, "application/json",
                                                       null, null, null, null, null);
    }

    @Test
    public void testPrepareHttpRequestWithMultipleHeaders() {
        List<HeaderParameter> headerParameters = new ArrayList<HeaderParameter>();
        HeaderParameter p = new HeaderParameter();
        p.setName("MyHeader");
        p.setDefaultValue("MyValue");
        headerParameters.add(p);

        HttpUriRequest request = client.prepareHttpRequest("/test", HttpMethod.GET, "application/json",
                                                       null, null, null, headerParameters, null);

        assertEquals(request.getURI().toString(), "http://127.0.0.1:8080/test", "URI in request is wrong.");
        assertEquals(request.getMethod(), "GET", "HttpMethod is wrong.");

        Header[] headers = request.getAllHeaders();
        assertEquals(headers.length, 2, "The number of headers is wrong.");
        String value1 = headers[0].getValue();
        String value2 = headers[1].getValue();
        if(value1.equals("application/json")) {
            assertEquals(value2, "MyValue", "header content is wrong.");
        } else {
            assertEquals(value2, "application/json", "header content is wrong.");
        }
    }

    @Test(expectedExceptions = LcmInternalException.class,
          expectedExceptionsMessageRegExp =
          "GenericApiClient currently doesn't support header parameter w/o default value..*")
    public void testPrepareHttpRequestForHeadersWithoutValue() {
        List<HeaderParameter> headerParameters = new ArrayList<HeaderParameter>();
        HeaderParameter p = new HeaderParameter();
        p.setName("MyHeader");
        headerParameters.add(p);

        HttpUriRequest request = client.prepareHttpRequest("/test", HttpMethod.GET, "application/json",
                                                       null, null, null, headerParameters, null);

        assertEquals(request.getURI().toString(), "http://127.0.0.1:8080/test", "URI in request is wrong.");
        assertEquals(request.getMethod(), "GET", "HttpMethod is wrong.");

        Header[] headers = request.getAllHeaders();
        assertEquals(headers.length, 2, "The number of headers is wrong.");
        String value1 = headers[0].getValue();
        String value2 = headers[1].getValue();
        if(value1.equals("application/json")) {
            assertEquals(value2, "MyValue", "header content is wrong.");
        } else {
            assertEquals(value2, "application/json", "header content is wrong.");
        }
    }

    @Test(expectedExceptions = ExternalComponentException.class,
          expectedExceptionsMessageRegExp = "Exception occurs during service call..*")
    public void testExecutionWithExternalException() {
        try {
            HttpClient hClient = mock(HttpClient.class);
            expect(hClient.execute(null)).andThrow(new IOException());
            replay(hClient);

            client.executeRequest(hClient, null);
        } catch(IOException e) {
            assertTrue(false, "GenericApiClient failed to catch IOException.");
        }
    }

    @Test
    public void testExecutionSuccessfully() {
        HttpResponse rsp = new BasicHttpResponse(new ProtocolVersion("http",1,1), 200, "Success");
        try {
            HttpClient hClient = mock(HttpClient.class);
            expect(hClient.execute(null)).andReturn(rsp);
            replay(hClient);

            HttpResponse rsp2 = client.executeRequest(hClient, null);
            assertEquals(rsp2.getStatusLine().getStatusCode(), 200, "Response isn't returned correctly.");
        } catch(IOException e) {
            assertTrue(false, "GenericApiClient failed to catch IOException.");
        }
    }
}
