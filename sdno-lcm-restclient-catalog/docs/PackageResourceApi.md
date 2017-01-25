# PackageResourceApi

All URIs are relative to *https://localhost/openoapi/catalog/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**delPackage**](PackageResourceApi.md#delPackage) | **DELETE** /csars/{csarId} | delete a package
[**getCsarFileUri**](PackageResourceApi.md#getCsarFileUri) | **GET** /csars/{csarId}/files | get csar file uri by csarId
[**queryPackageById**](PackageResourceApi.md#queryPackageById) | **GET** /csars/{csarId} | get csar package list
[**queryPackageListByCond**](PackageResourceApi.md#queryPackageListByCond) | **GET** /csars | get csar package list by condition
[**updatePackageStatus**](PackageResourceApi.md#updatePackageStatus) | **PUT** /csars/{csarId} | update csar package status
[**uploadPackage**](PackageResourceApi.md#uploadPackage) | **POST** /csars | upload csar package


<a name="delPackage"></a>
# **delPackage**
> delPackage(csarId)

delete a package



### Example
```java
// Import classes:
//import org.openo.sdno.lcm.restclient.catalog.ApiException;
//import org.openo.sdno.lcm.restclient.catalog.api.PackageResourceApi;


PackageResourceApi apiInstance = new PackageResourceApi();
String csarId = "csarId_example"; // String | csar Id
try {
    apiInstance.delPackage(csarId);
} catch (ApiException e) {
    System.err.println("Exception when calling PackageResourceApi#delPackage");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **csarId** | **String**| csar Id |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="getCsarFileUri"></a>
# **getCsarFileUri**
> CsarFileUriResponse getCsarFileUri(csarId, relativePath)

get csar file uri by csarId



### Example
```java
// Import classes:
//import org.openo.sdno.lcm.restclient.catalog.ApiException;
//import org.openo.sdno.lcm.restclient.catalog.api.PackageResourceApi;


PackageResourceApi apiInstance = new PackageResourceApi();
String csarId = "csarId_example"; // String | csar Id
String relativePath = "relativePath_example"; // String | csar file path
try {
    CsarFileUriResponse result = apiInstance.getCsarFileUri(csarId, relativePath);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PackageResourceApi#getCsarFileUri");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **csarId** | **String**| csar Id |
 **relativePath** | **String**| csar file path |

### Return type

[**CsarFileUriResponse**](CsarFileUriResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="queryPackageById"></a>
# **queryPackageById**
> List&lt;PackageMeta&gt; queryPackageById(csarId)

get csar package list



### Example
```java
// Import classes:
//import org.openo.sdno.lcm.restclient.catalog.ApiException;
//import org.openo.sdno.lcm.restclient.catalog.api.PackageResourceApi;


PackageResourceApi apiInstance = new PackageResourceApi();
String csarId = "csarId_example"; // String | csar id
try {
    List<PackageMeta> result = apiInstance.queryPackageById(csarId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PackageResourceApi#queryPackageById");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **csarId** | **String**| csar id |

### Return type

[**List&lt;PackageMeta&gt;**](PackageMeta.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="queryPackageListByCond"></a>
# **queryPackageListByCond**
> List&lt;PackageMeta&gt; queryPackageListByCond(name, provider, version, deletionPending, type)

get csar package list by condition



### Example
```java
// Import classes:
//import org.openo.sdno.lcm.restclient.catalog.ApiException;
//import org.openo.sdno.lcm.restclient.catalog.api.PackageResourceApi;


PackageResourceApi apiInstance = new PackageResourceApi();
String name = "name_example"; // String | csar name
String provider = "provider_example"; // String | csar provider
String version = "version_example"; // String | csar version
String deletionPending = "deletionPending_example"; // String | delay to delete
String type = "type_example"; // String | csar type
try {
    List<PackageMeta> result = apiInstance.queryPackageListByCond(name, provider, version, deletionPending, type);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PackageResourceApi#queryPackageListByCond");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **name** | **String**| csar name | [optional]
 **provider** | **String**| csar provider | [optional]
 **version** | **String**| csar version | [optional]
 **deletionPending** | **String**| delay to delete | [optional]
 **type** | **String**| csar type | [optional]

### Return type

[**List&lt;PackageMeta&gt;**](PackageMeta.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="updatePackageStatus"></a>
# **updatePackageStatus**
> updatePackageStatus(csarId, operationalState, usageState, onBoardState, processState, deletionPending)

update csar package status



### Example
```java
// Import classes:
//import org.openo.sdno.lcm.restclient.catalog.ApiException;
//import org.openo.sdno.lcm.restclient.catalog.api.PackageResourceApi;


PackageResourceApi apiInstance = new PackageResourceApi();
String csarId = "csarId_example"; // String | csar Id
String operationalState = "operationalState_example"; // String | csar operational status
String usageState = "usageState_example"; // String | csar usage status
String onBoardState = "onBoardState_example"; // String | csar onboard status
String processState = "processState_example"; // String | csar process status
String deletionPending = "deletionPending_example"; // String | csar deletionPending status
try {
    apiInstance.updatePackageStatus(csarId, operationalState, usageState, onBoardState, processState, deletionPending);
} catch (ApiException e) {
    System.err.println("Exception when calling PackageResourceApi#updatePackageStatus");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **csarId** | **String**| csar Id |
 **operationalState** | **String**| csar operational status | [optional]
 **usageState** | **String**| csar usage status | [optional]
 **onBoardState** | **String**| csar onboard status | [optional]
 **processState** | **String**| csar process status | [optional]
 **deletionPending** | **String**| csar deletionPending status | [optional]

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="uploadPackage"></a>
# **uploadPackage**
> UploadPackageResponse uploadPackage(file, body)

upload csar package



### Example
```java
// Import classes:
//import org.openo.sdno.lcm.restclient.catalog.ApiException;
//import org.openo.sdno.lcm.restclient.catalog.api.PackageResourceApi;


PackageResourceApi apiInstance = new PackageResourceApi();
File file = new File("/path/to/file.txt"); // File | file inputstream
FormDataContentDisposition body = new FormDataContentDisposition(); // FormDataContentDisposition | file detail
try {
    UploadPackageResponse result = apiInstance.uploadPackage(file, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PackageResourceApi#uploadPackage");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **file** | **File**| file inputstream |
 **body** | [**FormDataContentDisposition**](FormDataContentDisposition.md)| file detail | [optional]

### Return type

[**UploadPackageResponse**](UploadPackageResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

