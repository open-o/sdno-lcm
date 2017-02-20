# MSBServiceResourceApi

All URIs are relative to *https://localhost/api/microservices/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addMicroService**](MSBServiceResourceApi.md#addMicroService) | **POST** /services | add one microservice 
[**deleteMicroService**](MSBServiceResourceApi.md#deleteMicroService) | **DELETE** /services/{serviceName}/version/{version} | delete one full microservice by serviceName and version
[**getAllMicroService**](MSBServiceResourceApi.md#getAllMicroService) | **GET** /services | get all microservices 
[**getMicroService**](MSBServiceResourceApi.md#getMicroService) | **GET** /services/{serviceName}/version/{version} | get one microservice 
[**updateMicroService**](MSBServiceResourceApi.md#updateMicroService) | **PUT** /services/{serviceName}/version/{version} | update one microservice by serviceName and version
[**updateServiceStatus**](MSBServiceResourceApi.md#updateServiceStatus) | **PUT** /services/{serviceName}/version/{version}/status/{status} | update  microservice status by serviceName and version


<a name="addMicroService"></a>
# **addMicroService**
> MicroServiceFullInfo addMicroService(body, createOrUpdate, port)

add one microservice 



### Example
```java
// Import classes:
//import org.openo.sdno.lcm.restclient.msb.ApiException;
//import org.openo.sdno.lcm.restclient.msb.api.MSBServiceResourceApi;


MSBServiceResourceApi apiInstance = new MSBServiceResourceApi();
MicroServiceInfo body = new MicroServiceInfo(); // MicroServiceInfo | MicroServiceInfo Instance Info
Boolean createOrUpdate = true; // Boolean | createOrUpdate
String port = "port_example"; // String | port
try {
    MicroServiceFullInfo result = apiInstance.addMicroService(body, createOrUpdate, port);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling MSBServiceResourceApi#addMicroService");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**MicroServiceInfo**](MicroServiceInfo.md)| MicroServiceInfo Instance Info |
 **createOrUpdate** | **Boolean**| createOrUpdate | [optional] [default to true]
 **port** | **String**| port | [optional]

### Return type

[**MicroServiceFullInfo**](MicroServiceFullInfo.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="deleteMicroService"></a>
# **deleteMicroService**
> deleteMicroService(serviceName, version, port)

delete one full microservice by serviceName and version



### Example
```java
// Import classes:
//import org.openo.sdno.lcm.restclient.msb.ApiException;
//import org.openo.sdno.lcm.restclient.msb.api.MSBServiceResourceApi;


MSBServiceResourceApi apiInstance = new MSBServiceResourceApi();
String serviceName = "serviceName_example"; // String | microservice serviceName
String version = "version_example"; // String | microservice version,if the version is empty, please enter \"null\"
String port = "port_example"; // String | port
try {
    apiInstance.deleteMicroService(serviceName, version, port);
} catch (ApiException e) {
    System.err.println("Exception when calling MSBServiceResourceApi#deleteMicroService");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **serviceName** | **String**| microservice serviceName |
 **version** | **String**| microservice version,if the version is empty, please enter \&quot;null\&quot; |
 **port** | **String**| port | [optional]

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getAllMicroService"></a>
# **getAllMicroService**
> List&lt;MicroServiceFullInfo&gt; getAllMicroService()

get all microservices 



### Example
```java
// Import classes:
//import org.openo.sdno.lcm.restclient.msb.ApiException;
//import org.openo.sdno.lcm.restclient.msb.api.MSBServiceResourceApi;


MSBServiceResourceApi apiInstance = new MSBServiceResourceApi();
try {
    List<MicroServiceFullInfo> result = apiInstance.getAllMicroService();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling MSBServiceResourceApi#getAllMicroService");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;MicroServiceFullInfo&gt;**](MicroServiceFullInfo.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getMicroService"></a>
# **getMicroService**
> MicroServiceFullInfo getMicroService(serviceName, version, port)

get one microservice 

### Example
```java
// Import classes:
//import org.openo.sdno.lcm.restclient.msb.ApiException;
//import org.openo.sdno.lcm.restclient.msb.api.MSBServiceResourceApi;


MSBServiceResourceApi apiInstance = new MSBServiceResourceApi();
String serviceName = "serviceName_example"; // String | microservice serviceName
String version = "version_example"; // String | microservice version,if the version is empty, please enter \"null\"
String port = "port_example"; // String | port
try {
    MicroServiceFullInfo result = apiInstance.getMicroService(serviceName, version, port);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling MSBServiceResourceApi#getMicroService");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **serviceName** | **String**| microservice serviceName |
 **version** | **String**| microservice version,if the version is empty, please enter \&quot;null\&quot; |
 **port** | **String**| port | [optional]

### Return type

[**MicroServiceFullInfo**](MicroServiceFullInfo.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="updateMicroService"></a>
# **updateMicroService**
> MicroServiceFullInfo updateMicroService(serviceName, version, body)

update one microservice by serviceName and version



### Example
```java
// Import classes:
//import org.openo.sdno.lcm.restclient.msb.ApiException;
//import org.openo.sdno.lcm.restclient.msb.api.MSBServiceResourceApi;


MSBServiceResourceApi apiInstance = new MSBServiceResourceApi();
String serviceName = "serviceName_example"; // String | microservice serviceName
String version = "version_example"; // String | microservice version,if the version is empty, please enter \"null\"
MicroServiceInfo body = new MicroServiceInfo(); // MicroServiceInfo | microservice Instance Info
try {
    MicroServiceFullInfo result = apiInstance.updateMicroService(serviceName, version, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling MSBServiceResourceApi#updateMicroService");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **serviceName** | **String**| microservice serviceName |
 **version** | **String**| microservice version,if the version is empty, please enter \&quot;null\&quot; |
 **body** | [**MicroServiceInfo**](MicroServiceInfo.md)| microservice Instance Info |

### Return type

[**MicroServiceFullInfo**](MicroServiceFullInfo.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="updateServiceStatus"></a>
# **updateServiceStatus**
> MicroServiceFullInfo updateServiceStatus(serviceName, version, status)

update  microservice status by serviceName and version



### Example
```java
// Import classes:
//import org.openo.sdno.lcm.restclient.msb.ApiException;
//import org.openo.sdno.lcm.restclient.msb.api.MSBServiceResourceApi;


MSBServiceResourceApi apiInstance = new MSBServiceResourceApi();
String serviceName = "serviceName_example"; // String | microservice serviceName
String version = "version_example"; // String | microservice version,if the version is empty, please enter \"null\"
String status = "status_example"; // String | status,1ï¼šabled  0ï¼šdisabled
try {
    MicroServiceFullInfo result = apiInstance.updateServiceStatus(serviceName, version, status);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling MSBServiceResourceApi#updateServiceStatus");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **serviceName** | **String**| microservice serviceName |
 **version** | **String**| microservice version,if the version is empty, please enter \&quot;null\&quot; |
 **status** | **String**| status,1ï¼šabled  0ï¼šdisabled |

### Return type

[**MicroServiceFullInfo**](MicroServiceFullInfo.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

