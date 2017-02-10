# DefaultApi

All URIs are relative to *http://localhost/openoapi/sdnomss/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createConnectivityService**](DefaultApi.md#createConnectivityService) | **POST** /buckets/connectivityservicedb/resources/connectivityservice/objects | 
[**deleteConnectivityService**](DefaultApi.md#deleteConnectivityService) | **DELETE** /buckets/connectivityservicedb/resources/connectivityservice/objects/{id} | 
[**getConnectivityService**](DefaultApi.md#getConnectivityService) | **GET** /buckets/connectivityservicedb/resources/connectivityservice/objects | 
[**readConnectivityService**](DefaultApi.md#readConnectivityService) | **GET** /buckets/connectivityservicedb/resources/connectivityservice/objects/{id} | 
[**updateConnectivityService**](DefaultApi.md#updateConnectivityService) | **PUT** /buckets/connectivityservicedb/resources/connectivityservice/objects/{id} | 


<a name="createConnectivityService"></a>
# **createConnectivityService**
> ResponseConnectivityService createConnectivityService(body)



Add a new connectivity services

### Example
```java
// Import classes:
//import org.openo.sdno.lcm.restclient.serviceinventory.ApiException;
//import org.openo.sdno.lcm.restclient.serviceinventory.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
ConnectivityService body = new ConnectivityService(); // ConnectivityService | the createConnectivityService to be created
try {
    ResponseConnectivityService result = apiInstance.createConnectivityService(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#createConnectivityService");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**ConnectivityService**](ConnectivityService.md)| the createConnectivityService to be created |

### Return type

[**ResponseConnectivityService**](ResponseConnectivityService.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="deleteConnectivityService"></a>
# **deleteConnectivityService**
> deleteConnectivityService(id)



Delete the connectivity services

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

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getConnectivityService"></a>
# **getConnectivityService**
> ResponseConnectivityService getConnectivityService()



Add a new connectivity services

### Example
```java
// Import classes:
//import org.openo.sdno.lcm.restclient.serviceinventory.ApiException;
//import org.openo.sdno.lcm.restclient.serviceinventory.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
try {
    ResponseConnectivityService result = apiInstance.getConnectivityService();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#getConnectivityService");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**ResponseConnectivityService**](ResponseConnectivityService.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="readConnectivityService"></a>
# **readConnectivityService**
> ResponseConnectivityService readConnectivityService(id)



Read the connectivity services

### Example
```java
// Import classes:
//import org.openo.sdno.lcm.restclient.serviceinventory.ApiException;
//import org.openo.sdno.lcm.restclient.serviceinventory.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
String id = "id_example"; // String | ID of the connectivity service to be deleted
try {
    ResponseConnectivityService result = apiInstance.readConnectivityService(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#readConnectivityService");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**| ID of the connectivity service to be deleted |

### Return type

[**ResponseConnectivityService**](ResponseConnectivityService.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateConnectivityService"></a>
# **updateConnectivityService**
> UpdateResponseConnectivityService updateConnectivityService(id, body)



modify the value.

### Example
```java
// Import classes:
//import org.openo.sdno.lcm.restclient.serviceinventory.ApiException;
//import org.openo.sdno.lcm.restclient.serviceinventory.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
String id = "id_example"; // String | ID of the connectivity service to be deleted
UpdateConnectivityService body = new UpdateConnectivityService(); // UpdateConnectivityService | the partial connectivity service for the update operation
try {
    UpdateResponseConnectivityService result = apiInstance.updateConnectivityService(id, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#updateConnectivityService");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**| ID of the connectivity service to be deleted |
 **body** | [**UpdateConnectivityService**](UpdateConnectivityService.md)| the partial connectivity service for the update operation |

### Return type

[**UpdateResponseConnectivityService**](UpdateResponseConnectivityService.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

