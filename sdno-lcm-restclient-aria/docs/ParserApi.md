# ParserApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**parseControllerInstanceFile**](ParserApi.md#parseControllerInstanceFile) | **GET** /instance | Create instance from file using given path
[**parseControllerInstanceIndirect**](ParserApi.md#parseControllerInstanceIndirect) | **POST** /indirect/instance | Create instance from blueprint specyfied by URI
[**parseControllerInstanceUpload**](ParserApi.md#parseControllerInstanceUpload) | **POST** /instance | Create instance from uploaded blueprint file
[**parseControllerModelFile**](ParserApi.md#parseControllerModelFile) | **GET** /model | Create model from blueprint file using given path
[**parseControllerModelIndirect**](ParserApi.md#parseControllerModelIndirect) | **POST** /indirect/model | Create model from blueprint specyfied by URI
[**parseControllerModelUpload**](ParserApi.md#parseControllerModelUpload) | **POST** /model | Create model from uploaded blueprint file
[**parseControllerValidateFile**](ParserApi.md#parseControllerValidateFile) | **GET** /validate | Validate blueprint file using given path
[**parseControllerValidateIndirect**](ParserApi.md#parseControllerValidateIndirect) | **POST** /indirect/validate | Validate blueprint file using given URI
[**parseControllerValidateUpload**](ParserApi.md#parseControllerValidateUpload) | **POST** /validate | Validate uploaded blueprint file


<a name="parseControllerInstanceFile"></a>
# **parseControllerInstanceFile**
> Object parseControllerInstanceFile(path, inputs)

Create instance from file using given path

### Example
```java
// Import classes:
//import org.openo.sdno.lcm.restclient.aria.ApiException;
//import org.openo.sdno.lcm.restclient.aria.api.ParserApi;


ParserApi apiInstance = new ParserApi();
String path = "path_example"; // String | Path to blueprint file
String inputs = "inputs_example"; // String | Inputs for instance creation from blueprint
try {
    Object result = apiInstance.parseControllerInstanceFile(path, inputs);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ParserApi#parseControllerInstanceFile");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **path** | **String**| Path to blueprint file |
 **inputs** | **String**| Inputs for instance creation from blueprint | [optional]

### Return type

**Object**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="parseControllerInstanceIndirect"></a>
# **parseControllerInstanceIndirect**
> Object parseControllerInstanceIndirect(indirectData)

Create instance from blueprint specyfied by URI

### Example
```java
// Import classes:
//import org.openo.sdno.lcm.restclient.aria.ApiException;
//import org.openo.sdno.lcm.restclient.aria.api.ParserApi;


ParserApi apiInstance = new ParserApi();
IndirectData indirectData = new IndirectData(); // IndirectData | Blueprint specification
try {
    Object result = apiInstance.parseControllerInstanceIndirect(indirectData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ParserApi#parseControllerInstanceIndirect");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **indirectData** | [**IndirectData**](IndirectData.md)| Blueprint specification |

### Return type

**Object**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="parseControllerInstanceUpload"></a>
# **parseControllerInstanceUpload**
> Object parseControllerInstanceUpload(uploadContent, inputs)

Create instance from uploaded blueprint file

### Example
```java
// Import classes:
//import org.openo.sdno.lcm.restclient.aria.ApiException;
//import org.openo.sdno.lcm.restclient.aria.api.ParserApi;


ParserApi apiInstance = new ParserApi();
Object uploadContent = null; // Object | 
String inputs = "inputs_example"; // String | Inputs for instance creation from blueprint
try {
    Object result = apiInstance.parseControllerInstanceUpload(uploadContent, inputs);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ParserApi#parseControllerInstanceUpload");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **uploadContent** | **Object**|  |
 **inputs** | **String**| Inputs for instance creation from blueprint | [optional]

### Return type

**Object**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/x-yaml
 - **Accept**: application/json

<a name="parseControllerModelFile"></a>
# **parseControllerModelFile**
> Object parseControllerModelFile(path)

Create model from blueprint file using given path

### Example
```java
// Import classes:
//import org.openo.sdno.lcm.restclient.aria.ApiException;
//import org.openo.sdno.lcm.restclient.aria.api.ParserApi;


ParserApi apiInstance = new ParserApi();
String path = "path_example"; // String | Path to blueprint file
try {
    Object result = apiInstance.parseControllerModelFile(path);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ParserApi#parseControllerModelFile");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **path** | **String**| Path to blueprint file |

### Return type

**Object**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="parseControllerModelIndirect"></a>
# **parseControllerModelIndirect**
> Object parseControllerModelIndirect(indirectData)

Create model from blueprint specyfied by URI

### Example
```java
// Import classes:
//import org.openo.sdno.lcm.restclient.aria.ApiException;
//import org.openo.sdno.lcm.restclient.aria.api.ParserApi;


ParserApi apiInstance = new ParserApi();
IndirectData indirectData = new IndirectData(); // IndirectData | Blueprint specification
try {
    Object result = apiInstance.parseControllerModelIndirect(indirectData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ParserApi#parseControllerModelIndirect");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **indirectData** | [**IndirectData**](IndirectData.md)| Blueprint specification |

### Return type

**Object**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="parseControllerModelUpload"></a>
# **parseControllerModelUpload**
> Object parseControllerModelUpload(uploadContent, inputs)

Create model from uploaded blueprint file

### Example
```java
// Import classes:
//import org.openo.sdno.lcm.restclient.aria.ApiException;
//import org.openo.sdno.lcm.restclient.aria.api.ParserApi;


ParserApi apiInstance = new ParserApi();
Object uploadContent = null; // Object | 
String inputs = "inputs_example"; // String | Inputs for instance creation from blueprint
try {
    Object result = apiInstance.parseControllerModelUpload(uploadContent, inputs);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ParserApi#parseControllerModelUpload");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **uploadContent** | **Object**|  |
 **inputs** | **String**| Inputs for instance creation from blueprint | [optional]

### Return type

**Object**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/x-yaml
 - **Accept**: application/json

<a name="parseControllerValidateFile"></a>
# **parseControllerValidateFile**
> Object parseControllerValidateFile(path)

Validate blueprint file using given path

### Example
```java
// Import classes:
//import org.openo.sdno.lcm.restclient.aria.ApiException;
//import org.openo.sdno.lcm.restclient.aria.api.ParserApi;


ParserApi apiInstance = new ParserApi();
String path = "path_example"; // String | Path to blueprint file
try {
    Object result = apiInstance.parseControllerValidateFile(path);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ParserApi#parseControllerValidateFile");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **path** | **String**| Path to blueprint file |

### Return type

**Object**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="parseControllerValidateIndirect"></a>
# **parseControllerValidateIndirect**
> Object parseControllerValidateIndirect(indirectData)

Validate blueprint file using given URI

### Example
```java
// Import classes:
//import org.openo.sdno.lcm.restclient.aria.ApiException;
//import org.openo.sdno.lcm.restclient.aria.api.ParserApi;


ParserApi apiInstance = new ParserApi();
IndirectData indirectData = new IndirectData(); // IndirectData | Blueprint specification
try {
    Object result = apiInstance.parseControllerValidateIndirect(indirectData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ParserApi#parseControllerValidateIndirect");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **indirectData** | [**IndirectData**](IndirectData.md)| Blueprint specification |

### Return type

**Object**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="parseControllerValidateUpload"></a>
# **parseControllerValidateUpload**
> Object parseControllerValidateUpload(uploadContent, inputs)

Validate uploaded blueprint file

### Example
```java
// Import classes:
//import org.openo.sdno.lcm.restclient.aria.ApiException;
//import org.openo.sdno.lcm.restclient.aria.api.ParserApi;


ParserApi apiInstance = new ParserApi();
Object uploadContent = null; // Object | 
String inputs = "inputs_example"; // String | Inputs for instance creation from blueprint
try {
    Object result = apiInstance.parseControllerValidateUpload(uploadContent, inputs);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ParserApi#parseControllerValidateUpload");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **uploadContent** | **Object**|  |
 **inputs** | **String**| Inputs for instance creation from blueprint | [optional]

### Return type

**Object**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/x-yaml
 - **Accept**: application/json

