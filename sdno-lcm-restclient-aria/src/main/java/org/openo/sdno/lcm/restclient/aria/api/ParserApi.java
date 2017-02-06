package org.openo.sdno.lcm.restclient.aria.api;

import org.openo.sdno.lcm.restclient.aria.ApiException;
import org.openo.sdno.lcm.restclient.aria.ApiClient;
import org.openo.sdno.lcm.restclient.aria.Configuration;
import org.openo.sdno.lcm.restclient.aria.Pair;

import javax.ws.rs.core.GenericType;

import org.openo.sdno.lcm.restclient.aria.model.IndirectData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-02-06T13:02:54.199Z")
public class ParserApi {
  private ApiClient apiClient;

  public ParserApi() {
    this(Configuration.getDefaultApiClient());
  }

  public ParserApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Create instance from file using given path
   * 
   * @param path Path to blueprint file (required)
   * @param inputs Inputs for instance creation from blueprint (optional)
   * @return Object
   * @throws ApiException if fails to make API call
   */
  public Object parseControllerInstanceFile(String path, String inputs) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'path' is set
    if (path == null) {
      throw new ApiException(400, "Missing the required parameter 'path' when calling parseControllerInstanceFile");
    }
    
    // create path and map variables
    String localVarPath = "/instance".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "path", path));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "inputs", inputs));

    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<Object> localVarReturnType = new GenericType<Object>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Create instance from blueprint specyfied by URI
   * 
   * @param indirectData Blueprint specification (required)
   * @return Object
   * @throws ApiException if fails to make API call
   */
  public Object parseControllerInstanceIndirect(IndirectData indirectData) throws ApiException {
    Object localVarPostBody = indirectData;
    
    // verify the required parameter 'indirectData' is set
    if (indirectData == null) {
      throw new ApiException(400, "Missing the required parameter 'indirectData' when calling parseControllerInstanceIndirect");
    }
    
    // create path and map variables
    String localVarPath = "/indirect/instance".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<Object> localVarReturnType = new GenericType<Object>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Create instance from uploaded blueprint file
   * 
   * @param uploadContent  (required)
   * @param inputs Inputs for instance creation from blueprint (optional)
   * @return Object
   * @throws ApiException if fails to make API call
   */
  public Object parseControllerInstanceUpload(Object uploadContent, String inputs) throws ApiException {
    Object localVarPostBody = uploadContent;
    
    // verify the required parameter 'uploadContent' is set
    if (uploadContent == null) {
      throw new ApiException(400, "Missing the required parameter 'uploadContent' when calling parseControllerInstanceUpload");
    }
    
    // create path and map variables
    String localVarPath = "/instance".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "inputs", inputs));

    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/x-yaml"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<Object> localVarReturnType = new GenericType<Object>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Create model from blueprint file using given path
   * 
   * @param path Path to blueprint file (required)
   * @return Object
   * @throws ApiException if fails to make API call
   */
  public Object parseControllerModelFile(String path) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'path' is set
    if (path == null) {
      throw new ApiException(400, "Missing the required parameter 'path' when calling parseControllerModelFile");
    }
    
    // create path and map variables
    String localVarPath = "/model".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "path", path));

    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<Object> localVarReturnType = new GenericType<Object>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Create model from blueprint specyfied by URI
   * 
   * @param indirectData Blueprint specification (required)
   * @return Object
   * @throws ApiException if fails to make API call
   */
  public Object parseControllerModelIndirect(IndirectData indirectData) throws ApiException {
    Object localVarPostBody = indirectData;
    
    // verify the required parameter 'indirectData' is set
    if (indirectData == null) {
      throw new ApiException(400, "Missing the required parameter 'indirectData' when calling parseControllerModelIndirect");
    }
    
    // create path and map variables
    String localVarPath = "/indirect/model".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<Object> localVarReturnType = new GenericType<Object>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Create model from uploaded blueprint file
   * 
   * @param uploadContent  (required)
   * @param inputs Inputs for instance creation from blueprint (optional)
   * @return Object
   * @throws ApiException if fails to make API call
   */
  public Object parseControllerModelUpload(Object uploadContent, String inputs) throws ApiException {
    Object localVarPostBody = uploadContent;
    
    // verify the required parameter 'uploadContent' is set
    if (uploadContent == null) {
      throw new ApiException(400, "Missing the required parameter 'uploadContent' when calling parseControllerModelUpload");
    }
    
    // create path and map variables
    String localVarPath = "/model".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "inputs", inputs));

    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/x-yaml"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<Object> localVarReturnType = new GenericType<Object>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Validate blueprint file using given path
   * 
   * @param path Path to blueprint file (required)
   * @return Object
   * @throws ApiException if fails to make API call
   */
  public Object parseControllerValidateFile(String path) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'path' is set
    if (path == null) {
      throw new ApiException(400, "Missing the required parameter 'path' when calling parseControllerValidateFile");
    }
    
    // create path and map variables
    String localVarPath = "/validate".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "path", path));

    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<Object> localVarReturnType = new GenericType<Object>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Validate blueprint file using given URI
   * 
   * @param indirectData Blueprint specification (required)
   * @return Object
   * @throws ApiException if fails to make API call
   */
  public Object parseControllerValidateIndirect(IndirectData indirectData) throws ApiException {
    Object localVarPostBody = indirectData;
    
    // verify the required parameter 'indirectData' is set
    if (indirectData == null) {
      throw new ApiException(400, "Missing the required parameter 'indirectData' when calling parseControllerValidateIndirect");
    }
    
    // create path and map variables
    String localVarPath = "/indirect/validate".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<Object> localVarReturnType = new GenericType<Object>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Validate uploaded blueprint file
   * 
   * @param uploadContent  (required)
   * @param inputs Inputs for instance creation from blueprint (optional)
   * @return Object
   * @throws ApiException if fails to make API call
   */
  public Object parseControllerValidateUpload(Object uploadContent, String inputs) throws ApiException {
    Object localVarPostBody = uploadContent;
    
    // verify the required parameter 'uploadContent' is set
    if (uploadContent == null) {
      throw new ApiException(400, "Missing the required parameter 'uploadContent' when calling parseControllerValidateUpload");
    }
    
    // create path and map variables
    String localVarPath = "/validate".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "inputs", inputs));

    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/x-yaml"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<Object> localVarReturnType = new GenericType<Object>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
}
