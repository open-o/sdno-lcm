# DefaultApi

All URIs are relative to *http://localhost/openoapi/sdnomss/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createConnectivityService**](DefaultApi.md#createConnectivityService) | **POST** /buckets/connectivityservicedb/resources/connectivityservice/objects | 
[**deleteConnectivityService**](DefaultApi.md#deleteConnectivityService) | **DELETE** /buckets/connectivityservicedb/resources/connectivityservice/objects/{id} | 
[**getConnectivityService**](DefaultApi.md#getConnectivityService) | **GET** /buckets/connectivityservicedb/resources/connectivityservice/objects | 
[**readConnectivityService**](DefaultApi.md#readConnectivityService) | **GET** /buckets/connectivityservicedb/resources/connectivityservice/objects/{id} | 
[**updateConnectivityServiceRequest**](DefaultApi.md#updateConnectivityServiceRequest) | **PUT** /buckets/connectivityservicedb/resources/connectivityservice/objects/{id} | 


<a name="createConnectivityService"></a>
# **createConnectivityService**
> CreateConnectivityServiceResponse createConnectivityService(body)



Add a new connectivity services

### Example
```java
// Import classes:
//import org.openo.sdno.lcm.restclient.serviceinventory.ApiException;
//import org.openo.sdno.lcm.restclient.serviceinventory.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
CreateConnectivityServiceRequest body = new CreateConnectivityServiceRequest(); // CreateConnectivityServiceRequest | the createConnectivityService to be created
try {
    CreateConnectivityServiceResponse result = apiInstance.createConnectivityService(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#createConnectivityService");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**CreateConnectivityServiceRequest**](CreateConnectivityServiceRequest.md)| the createConnectivityService to be created |

### Return type

[**CreateConnectivityServiceResponse**](CreateConnectivityServiceResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json;charset=UTF-8
 - **Accept**: application/json;charset=UTF-8

<a name="deleteConnectivityService"></a>
# **deleteConnectivityService**
> deleteConnectivityService(id)



Delete the connectivity service specified in request

### Example
```java
// Import classes:
//import org.openo.sdno.lcm.restclient.serviceinventory.ApiException;
//import org.openo.sdno.lcm.restclient.serviceinventory.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
String id = "id_example"; // String | ID of the connectivity service to be deleted
try {
    apiInstance.deleteConnectivityService(id);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#deleteConnectivityService");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**| ID of the connectivity service to be deleted |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json;charset=UTF-8
 - **Accept**: application/json;charset=UTF-8

<a name="getConnectivityService"></a>
# **getConnectivityService**
> GetAllConnectivityServiceResponse getConnectivityService()



get connectivity services

### Example
```java
// Import classes:
//import org.openo.sdno.lcm.restclient.serviceinventory.ApiException;
//import org.openo.sdno.lcm.restclient.serviceinventory.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
try {
    GetAllConnectivityServiceResponse result = apiInstance.getConnectivityService();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#getConnectivityService");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**GetAllConnectivityServiceResponse**](GetAllConnectivityServiceResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json;charset=UTF-8
 - **Accept**: application/json;charset=UTF-8

<a name="readConnectivityService"></a>
# **readConnectivityService**
> GetConnectivityServiceResponse readConnectivityService(id)



Get the connectivity service specified in request

### Example
```java
// Import classes:
//import org.openo.sdno.lcm.restclient.serviceinventory.ApiException;
//import org.openo.sdno.lcm.restclient.serviceinventory.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
String id = "id_example"; // String | ID of the connectivity service to be queried
try {
    GetConnectivityServiceResponse result = apiInstance.readConnectivityService(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#readConnectivityService");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**| ID of the connectivity service to be queried |

### Return type

[**GetConnectivityServiceResponse**](GetConnectivityServiceResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json;charset=UTF-8
 - **Accept**: application/json;charset=UTF-8

<a name="updateConnectivityServiceRequest"></a>
# **updateConnectivityServiceRequest**
> UpdateResponse updateConnectivityServiceRequest(id, body)



modify the connectivity service specified in request

### Example
```java
// Import classes:
//import org.openo.sdno.lcm.restclient.serviceinventory.ApiException;
//import org.openo.sdno.lcm.restclient.serviceinventory.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
String id = "id_example"; // String | ID of the connectivity service to be updated
UpdateConnectivityServiceRequest body = new UpdateConnectivityServiceRequest(); // UpdateConnectivityServiceRequest | the partial connectivity service for the update operation
try {
    UpdateResponse result = apiInstance.updateConnectivityServiceRequest(id, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#updateConnectivityServiceRequest");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**| ID of the connectivity service to be updated |
 **body** | [**UpdateConnectivityServiceRequest**](UpdateConnectivityServiceRequest.md)| the partial connectivity service for the update operation |

### Return type

[**UpdateResponse**](UpdateResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json;charset=UTF-8
 - **Accept**: application/json;charset=UTF-8

