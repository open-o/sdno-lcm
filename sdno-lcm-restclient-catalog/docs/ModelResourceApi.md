# ModelResourceApi

All URIs are relative to *https://localhost/openoapi/catalog/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getNestingServiceTemplate**](ModelResourceApi.md#getNestingServiceTemplate) | **GET** /servicetemplates/nesting | Query nesting service template of a node type
[**getNodeTemplateById**](ModelResourceApi.md#getNodeTemplateById) | **GET** /servicetemplates/{serviceTemplateId}/nodetemplates/{nodeTemplateId} | Query node template by node template id
[**getNodeTemplatesByType**](ModelResourceApi.md#getNodeTemplatesByType) | **GET** /servicetemplates/{serviceTemplateId}/nodetemplates | Query node template list of a specified service template
[**getParametersByOperationName**](ModelResourceApi.md#getParametersByOperationName) | **GET** /servicetemplates/{serviceTemplateId}/operations/{operationName}/parameters | Query input parameters of a specified operation
[**getServiceTemplateById**](ModelResourceApi.md#getServiceTemplateById) | **GET** /servicetemplates/{servicetemplateid} | Query service template by service template id
[**getServiceTemplateOperations**](ModelResourceApi.md#getServiceTemplateOperations) | **GET** /servicetemplates/{serviceTemplateId}/operations | Query operation list of service template
[**getServiceTemplateParameters**](ModelResourceApi.md#getServiceTemplateParameters) | **GET** /servicetemplates/{servicetemplateid}/parameters | Query input parameters of service template
[**getServiceTemplateRawData**](ModelResourceApi.md#getServiceTemplateRawData) | **POST** /servicetemplates/queryingrawdata | Query raw data of a service template by csar id
[**getServiceTemplates**](ModelResourceApi.md#getServiceTemplates) | **GET** /servicetemplates | Query service template by filter conditions
[**test**](ModelResourceApi.md#test) | **GET** /servicetemplates/test | test


<a name="getNestingServiceTemplate"></a>
# **getNestingServiceTemplate**
> List&lt;ServiceTemplate&gt; getNestingServiceTemplate(nodeTypeIds)

Query nesting service template of a node type



### Example
```java
// Import classes:
//import org.openo.sdno.lcm.restclient.catalog.ApiException;
//import org.openo.sdno.lcm.restclient.catalog.api.ModelResourceApi;


ModelResourceApi apiInstance = new ModelResourceApi();
String nodeTypeIds = "nodeTypeIds_example"; // String | Node type ids splited by ','. Such as 'tosca.nodes.nfv.VDU,tosca.nodes.nfv.VL'.
try {
    List<ServiceTemplate> result = apiInstance.getNestingServiceTemplate(nodeTypeIds);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ModelResourceApi#getNestingServiceTemplate");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **nodeTypeIds** | **String**| Node type ids splited by &#39;,&#39;. Such as &#39;tosca.nodes.nfv.VDU,tosca.nodes.nfv.VL&#39;. |

### Return type

[**List&lt;ServiceTemplate&gt;**](ServiceTemplate.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getNodeTemplateById"></a>
# **getNodeTemplateById**
> NodeTemplate getNodeTemplateById(serviceTemplateId, nodeTemplateId)

Query node template by node template id



### Example
```java
// Import classes:
//import org.openo.sdno.lcm.restclient.catalog.ApiException;
//import org.openo.sdno.lcm.restclient.catalog.api.ModelResourceApi;


ModelResourceApi apiInstance = new ModelResourceApi();
String serviceTemplateId = "serviceTemplateId_example"; // String | Service Template Id
String nodeTemplateId = "nodeTemplateId_example"; // String | Node Template Id
try {
    NodeTemplate result = apiInstance.getNodeTemplateById(serviceTemplateId, nodeTemplateId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ModelResourceApi#getNodeTemplateById");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **serviceTemplateId** | **String**| Service Template Id |
 **nodeTemplateId** | **String**| Node Template Id |

### Return type

[**NodeTemplate**](NodeTemplate.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getNodeTemplatesByType"></a>
# **getNodeTemplatesByType**
> List&lt;NodeTemplate&gt; getNodeTemplatesByType(serviceTemplateId, types)

Query node template list of a specified service template



### Example
```java
// Import classes:
//import org.openo.sdno.lcm.restclient.catalog.ApiException;
//import org.openo.sdno.lcm.restclient.catalog.api.ModelResourceApi;


ModelResourceApi apiInstance = new ModelResourceApi();
String serviceTemplateId = "serviceTemplateId_example"; // String | Service Template Id
String types = "types_example"; // String | The type of node template
try {
    List<NodeTemplate> result = apiInstance.getNodeTemplatesByType(serviceTemplateId, types);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ModelResourceApi#getNodeTemplatesByType");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **serviceTemplateId** | **String**| Service Template Id |
 **types** | **String**| The type of node template | [optional]

### Return type

[**List&lt;NodeTemplate&gt;**](NodeTemplate.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getParametersByOperationName"></a>
# **getParametersByOperationName**
> List&lt;InputParameter&gt; getParametersByOperationName(serviceTemplateId, operationName)

Query input parameters of a specified operation



### Example
```java
// Import classes:
//import org.openo.sdno.lcm.restclient.catalog.ApiException;
//import org.openo.sdno.lcm.restclient.catalog.api.ModelResourceApi;


ModelResourceApi apiInstance = new ModelResourceApi();
String serviceTemplateId = "serviceTemplateId_example"; // String | Service Template Id
String operationName = "operationName_example"; // String | Operation Name
try {
    List<InputParameter> result = apiInstance.getParametersByOperationName(serviceTemplateId, operationName);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ModelResourceApi#getParametersByOperationName");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **serviceTemplateId** | **String**| Service Template Id |
 **operationName** | **String**| Operation Name |

### Return type

[**List&lt;InputParameter&gt;**](InputParameter.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getServiceTemplateById"></a>
# **getServiceTemplateById**
> ServiceTemplate getServiceTemplateById(servicetemplateid)

Query service template by service template id



### Example
```java
// Import classes:
//import org.openo.sdno.lcm.restclient.catalog.ApiException;
//import org.openo.sdno.lcm.restclient.catalog.api.ModelResourceApi;


ModelResourceApi apiInstance = new ModelResourceApi();
String servicetemplateid = "servicetemplateid_example"; // String | service template id
try {
    ServiceTemplate result = apiInstance.getServiceTemplateById(servicetemplateid);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ModelResourceApi#getServiceTemplateById");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **servicetemplateid** | **String**| service template id |

### Return type

[**ServiceTemplate**](ServiceTemplate.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getServiceTemplateOperations"></a>
# **getServiceTemplateOperations**
> List&lt;ServiceTemplateOperation&gt; getServiceTemplateOperations(serviceTemplateId)

Query operation list of service template



### Example
```java
// Import classes:
//import org.openo.sdno.lcm.restclient.catalog.ApiException;
//import org.openo.sdno.lcm.restclient.catalog.api.ModelResourceApi;


ModelResourceApi apiInstance = new ModelResourceApi();
String serviceTemplateId = "serviceTemplateId_example"; // String | Service Template Id
try {
    List<ServiceTemplateOperation> result = apiInstance.getServiceTemplateOperations(serviceTemplateId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ModelResourceApi#getServiceTemplateOperations");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **serviceTemplateId** | **String**| Service Template Id |

### Return type

[**List&lt;ServiceTemplateOperation&gt;**](ServiceTemplateOperation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getServiceTemplateParameters"></a>
# **getServiceTemplateParameters**
> Parameters getServiceTemplateParameters(servicetemplateid)

Query input parameters of service template



### Example
```java
// Import classes:
//import org.openo.sdno.lcm.restclient.catalog.ApiException;
//import org.openo.sdno.lcm.restclient.catalog.api.ModelResourceApi;


ModelResourceApi apiInstance = new ModelResourceApi();
String servicetemplateid = "servicetemplateid_example"; // String | service template id
try {
    Parameters result = apiInstance.getServiceTemplateParameters(servicetemplateid);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ModelResourceApi#getServiceTemplateParameters");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **servicetemplateid** | **String**| service template id |

### Return type

[**Parameters**](Parameters.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getServiceTemplateRawData"></a>
# **getServiceTemplateRawData**
> ServiceTemplateRawData getServiceTemplateRawData(body)

Query raw data of a service template by csar id



### Example
```java
// Import classes:
//import org.openo.sdno.lcm.restclient.catalog.ApiException;
//import org.openo.sdno.lcm.restclient.catalog.api.ModelResourceApi;


ModelResourceApi apiInstance = new ModelResourceApi();
QueryRawDataCondition body = new QueryRawDataCondition(); // QueryRawDataCondition | Query Service Template Raw Data Condition
try {
    ServiceTemplateRawData result = apiInstance.getServiceTemplateRawData(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ModelResourceApi#getServiceTemplateRawData");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**QueryRawDataCondition**](QueryRawDataCondition.md)| Query Service Template Raw Data Condition |

### Return type

[**ServiceTemplateRawData**](ServiceTemplateRawData.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getServiceTemplates"></a>
# **getServiceTemplates**
> List&lt;ServiceTemplate&gt; getServiceTemplates(status, deletionPending)

Query service template by filter conditions



### Example
```java
// Import classes:
//import org.openo.sdno.lcm.restclient.catalog.ApiException;
//import org.openo.sdno.lcm.restclient.catalog.api.ModelResourceApi;


ModelResourceApi apiInstance = new ModelResourceApi();
String status = "status_example"; // String | template status
Boolean deletionPending = true; // Boolean | delay to delete
try {
    List<ServiceTemplate> result = apiInstance.getServiceTemplates(status, deletionPending);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ModelResourceApi#getServiceTemplates");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **status** | **String**| template status | [optional] [enum: Enabled, Disabled]
 **deletionPending** | **Boolean**| delay to delete | [optional]

### Return type

[**List&lt;ServiceTemplate&gt;**](ServiceTemplate.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="test"></a>
# **test**
> List&lt;String&gt; test()

test



### Example
```java
// Import classes:
//import org.openo.sdno.lcm.restclient.catalog.ApiException;
//import org.openo.sdno.lcm.restclient.catalog.api.ModelResourceApi;


ModelResourceApi apiInstance = new ModelResourceApi();
try {
    List<String> result = apiInstance.test();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ModelResourceApi#test");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

**List&lt;String&gt;**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

