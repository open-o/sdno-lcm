package org.openo.sdno.lcm.restclient.catalog.api;

import org.openo.sdno.lcm.restclient.catalog.ApiException;
import org.openo.sdno.lcm.restclient.catalog.ApiClient;
import org.openo.sdno.lcm.restclient.catalog.Configuration;
import org.openo.sdno.lcm.restclient.catalog.Pair;

import javax.ws.rs.core.GenericType;

import org.openo.sdno.lcm.restclient.catalog.model.CsarFileUriResponse;
import org.openo.sdno.lcm.restclient.catalog.model.PackageMeta;
import org.openo.sdno.lcm.restclient.catalog.model.UploadPackageResponse;
import org.openo.sdno.lcm.restclient.catalog.model.FormDataContentDisposition;
import java.io.File;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-01-18T13:47:52.904Z")
public class PackageResourceApi {
  private ApiClient apiClient;

  public PackageResourceApi() {
    this(Configuration.getDefaultApiClient());
  }

  public PackageResourceApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * delete a package
   * 
   * @param csarId csar Id (required)
   * @throws ApiException if fails to make API call
   */
  public void delPackage(String csarId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'csarId' is set
    if (csarId == null) {
      throw new ApiException(400, "Missing the required parameter 'csarId' when calling delPackage");
    }
    
    // create path and map variables
    String localVarPath = "/csars/{csarId}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "csarId" + "\\}", apiClient.escapeString(csarId.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    
    final String[] localVarAccepts = {
      
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };


    apiClient.invokeAPI(localVarPath, "DELETE", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, null);
  }
  /**
   * get csar file uri by csarId
   * 
   * @param csarId csar Id (required)
   * @param relativePath csar file path (required)
   * @return CsarFileUriResponse
   * @throws ApiException if fails to make API call
   */
  public CsarFileUriResponse getCsarFileUri(String csarId, String relativePath) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'csarId' is set
    if (csarId == null) {
      throw new ApiException(400, "Missing the required parameter 'csarId' when calling getCsarFileUri");
    }
    
    // verify the required parameter 'relativePath' is set
    if (relativePath == null) {
      throw new ApiException(400, "Missing the required parameter 'relativePath' when calling getCsarFileUri");
    }
    
    // create path and map variables
    String localVarPath = "/csars/{csarId}/files".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "csarId" + "\\}", apiClient.escapeString(csarId.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "relativePath", relativePath));

    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<CsarFileUriResponse> localVarReturnType = new GenericType<CsarFileUriResponse>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * get csar package list
   * 
   * @param csarId csar id (required)
   * @return List<PackageMeta>
   * @throws ApiException if fails to make API call
   */
  public List<PackageMeta> queryPackageById(String csarId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'csarId' is set
    if (csarId == null) {
      throw new ApiException(400, "Missing the required parameter 'csarId' when calling queryPackageById");
    }
    
    // create path and map variables
    String localVarPath = "/csars/{csarId}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "csarId" + "\\}", apiClient.escapeString(csarId.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<List<PackageMeta>> localVarReturnType = new GenericType<List<PackageMeta>>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * get csar package list by condition
   * 
   * @param name csar name (optional)
   * @param provider csar provider (optional)
   * @param version csar version (optional)
   * @param deletionPending delay to delete (optional)
   * @param type csar type (optional)
   * @return List<PackageMeta>
   * @throws ApiException if fails to make API call
   */
  public List<PackageMeta> queryPackageListByCond(String name, String provider, String version, String deletionPending, String type) throws ApiException {
    Object localVarPostBody = null;
    
    // create path and map variables
    String localVarPath = "/csars".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "name", name));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "provider", provider));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "version", version));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "deletionPending", deletionPending));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "type", type));

    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<List<PackageMeta>> localVarReturnType = new GenericType<List<PackageMeta>>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * update csar package status
   * 
   * @param csarId csar Id (required)
   * @param operationalState csar operational status (optional)
   * @param usageState csar usage status (optional)
   * @param onBoardState csar onboard status (optional)
   * @param processState csar process status (optional)
   * @param deletionPending csar deletionPending status (optional)
   * @throws ApiException if fails to make API call
   */
  public void updatePackageStatus(String csarId, String operationalState, String usageState, String onBoardState, String processState, String deletionPending) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'csarId' is set
    if (csarId == null) {
      throw new ApiException(400, "Missing the required parameter 'csarId' when calling updatePackageStatus");
    }
    
    // create path and map variables
    String localVarPath = "/csars/{csarId}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "csarId" + "\\}", apiClient.escapeString(csarId.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "operationalState", operationalState));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "usageState", usageState));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "onBoardState", onBoardState));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "processState", processState));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "deletionPending", deletionPending));

    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };


    apiClient.invokeAPI(localVarPath, "PUT", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, null);
  }
  /**
   * upload csar package
   * 
   * @param file file inputstream (required)
   * @param body file detail (optional)
   * @return UploadPackageResponse
   * @throws ApiException if fails to make API call
   */
  public UploadPackageResponse uploadPackage(File file, FormDataContentDisposition body) throws ApiException {
    Object localVarPostBody = body;
    
    // verify the required parameter 'file' is set
    if (file == null) {
      throw new ApiException(400, "Missing the required parameter 'file' when calling uploadPackage");
    }
    
    // create path and map variables
    String localVarPath = "/csars".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    if (file != null)
      localVarFormParams.put("file", file);

    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "multipart/form-data"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<UploadPackageResponse> localVarReturnType = new GenericType<UploadPackageResponse>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
}
