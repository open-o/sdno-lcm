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

package org.openo.sdno.lcm.restclient.serviceinventory.api;

import org.openo.sdno.lcm.restclient.serviceinventory.ApiException;
import org.openo.sdno.lcm.restclient.serviceinventory.ApiClient;
import org.openo.sdno.lcm.restclient.serviceinventory.Configuration;
import org.openo.sdno.lcm.restclient.serviceinventory.Pair;

import javax.ws.rs.core.GenericType;

import org.openo.sdno.lcm.restclient.serviceinventory.model.CreateConnectivityServiceRequest;
import org.openo.sdno.lcm.restclient.serviceinventory.model.CreateConnectivityServiceResponse;
import org.openo.sdno.lcm.restclient.serviceinventory.model.GetAllConnectivityServiceResponse;
import org.openo.sdno.lcm.restclient.serviceinventory.model.GetConnectivityServiceResponse;
import org.openo.sdno.lcm.restclient.serviceinventory.model.UpdateResponse;
import org.openo.sdno.lcm.restclient.serviceinventory.model.UpdateConnectivityServiceRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-02-28T17:11:52.313Z")
public class DefaultApi {
  private ApiClient apiClient;

  public DefaultApi() {
    this(Configuration.getDefaultApiClient());
  }

  public DefaultApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * 
   * Add a new connectivity services
   * @param body the createConnectivityService to be created (required)
   * @return CreateConnectivityServiceResponse
   * @throws ApiException if fails to make API call
   */
  public CreateConnectivityServiceResponse createConnectivityService(CreateConnectivityServiceRequest body) throws ApiException {
    Object localVarPostBody = body;
    
    // verify the required parameter 'body' is set
    if (body == null) {
      throw new ApiException(400, "Missing the required parameter 'body' when calling createConnectivityService");
    }
    
    // create path and map variables
    String localVarPath = "/buckets/connectivityservicedb/resources/connectivityservice/objects".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    
    final String[] localVarAccepts = {
      "application/json;charset=UTF-8"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json;charset=UTF-8"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<CreateConnectivityServiceResponse> localVarReturnType = new GenericType<CreateConnectivityServiceResponse>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * 
   * Delete the connectivity service specified in request
   * @param id ID of the connectivity service to be deleted (required)
   * @throws ApiException if fails to make API call
   */
  public void deleteConnectivityService(String id) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(400, "Missing the required parameter 'id' when calling deleteConnectivityService");
    }
    
    // create path and map variables
    String localVarPath = "/buckets/connectivityservicedb/resources/connectivityservice/objects/{id}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "id" + "\\}", apiClient.escapeString(id.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    
    final String[] localVarAccepts = {
      "application/json;charset=UTF-8"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json;charset=UTF-8"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };


    apiClient.invokeAPI(localVarPath, "DELETE", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, null);
  }
  /**
   * 
   * get connectivity services
   * @return GetAllConnectivityServiceResponse
   * @throws ApiException if fails to make API call
   */
  public GetAllConnectivityServiceResponse getConnectivityService() throws ApiException {
    Object localVarPostBody = null;
    
    // create path and map variables
    String localVarPath = "/buckets/connectivityservicedb/resources/connectivityservice/objects".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    
    final String[] localVarAccepts = {
      "application/json;charset=UTF-8"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json;charset=UTF-8"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<GetAllConnectivityServiceResponse> localVarReturnType = new GenericType<GetAllConnectivityServiceResponse>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * 
   * Get the connectivity service specified in request
   * @param id ID of the connectivity service to be queried (required)
   * @return GetConnectivityServiceResponse
   * @throws ApiException if fails to make API call
   */
  public GetConnectivityServiceResponse readConnectivityService(String id) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(400, "Missing the required parameter 'id' when calling readConnectivityService");
    }
    
    // create path and map variables
    String localVarPath = "/buckets/connectivityservicedb/resources/connectivityservice/objects/{id}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "id" + "\\}", apiClient.escapeString(id.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    
    final String[] localVarAccepts = {
      "application/json;charset=UTF-8"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json;charset=UTF-8"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<GetConnectivityServiceResponse> localVarReturnType = new GenericType<GetConnectivityServiceResponse>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * 
   * modify the connectivity service specified in request
   * @param id ID of the connectivity service to be updated (required)
   * @param body the partial connectivity service for the update operation (required)
   * @return UpdateResponse
   * @throws ApiException if fails to make API call
   */
  public UpdateResponse updateConnectivityServiceRequest(String id, UpdateConnectivityServiceRequest body) throws ApiException {
    Object localVarPostBody = body;
    
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(400, "Missing the required parameter 'id' when calling updateConnectivityServiceRequest");
    }
    
    // verify the required parameter 'body' is set
    if (body == null) {
      throw new ApiException(400, "Missing the required parameter 'body' when calling updateConnectivityServiceRequest");
    }
    
    // create path and map variables
    String localVarPath = "/buckets/connectivityservicedb/resources/connectivityservice/objects/{id}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "id" + "\\}", apiClient.escapeString(id.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    
    final String[] localVarAccepts = {
      "application/json;charset=UTF-8"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json;charset=UTF-8"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<UpdateResponse> localVarReturnType = new GenericType<UpdateResponse>() {};
    return apiClient.invokeAPI(localVarPath, "PUT", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
}
