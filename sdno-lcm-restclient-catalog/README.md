# restclient-catalog

## Requirements

Building the API client library requires [Maven](https://maven.apache.org/) to be installed.

## Installation

To install the API client library to your local Maven repository, simply execute:

```shell
mvn install
```

To deploy it to a remote Maven repository instead, configure the settings of the repository and execute:

```shell
mvn deploy
```

Refer to the [official documentation](https://maven.apache.org/plugins/maven-deploy-plugin/usage.html) for more information.

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
    <groupId>org.openo.sdno.lcm</groupId>
    <artifactId>restclient-catalog</artifactId>
    <version>1.1.0-SNAPSHOT</version>
    <scope>compile</scope>
</dependency>
```

### Others

At first generate the JAR by executing:

    mvn package

Then manually install the following JARs:

* target/restclient-catalog-1.1.0-SNAPSHOT.jar
* target/lib/*.jar

## Getting Started

Please follow the [installation](#installation) instruction and execute the following Java code:

```java

import org.openo.sdno.lcm.restclient.catalog.*;
import org.openo.sdno.lcm.restclient.catalog.auth.*;
import org.openo.sdno.lcm.restclient.catalog.model.*;
import org.openo.sdno.lcm.restclient.catalog.api.ModelResourceApi;

import java.io.File;
import java.util.*;

public class ModelResourceApiExample {

    public static void main(String[] args) {
        
        ModelResourceApi apiInstance = new ModelResourceApi();
        String nodeTypeIds = "nodeTypeIds_example"; // String | Node type ids splited by ','. Such as 'tosca.nodes.nfv.VDU,tosca.nodes.nfv.VL'.
        try {
            List<ServiceTemplate> result = apiInstance.getNestingServiceTemplate(nodeTypeIds);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ModelResourceApi#getNestingServiceTemplate");
            e.printStackTrace();
        }
    }
}

```

## Documentation for API Endpoints

All URIs are relative to *https://localhost/openoapi/catalog/v1*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*ModelResourceApi* | [**getNestingServiceTemplate**](docs/ModelResourceApi.md#getNestingServiceTemplate) | **GET** /servicetemplates/nesting | Query nesting service template of a node type
*ModelResourceApi* | [**getNodeTemplateById**](docs/ModelResourceApi.md#getNodeTemplateById) | **GET** /servicetemplates/{serviceTemplateId}/nodetemplates/{nodeTemplateId} | Query node template by node template id
*ModelResourceApi* | [**getNodeTemplatesByType**](docs/ModelResourceApi.md#getNodeTemplatesByType) | **GET** /servicetemplates/{serviceTemplateId}/nodetemplates | Query node template list of a specified service template
*ModelResourceApi* | [**getParametersByOperationName**](docs/ModelResourceApi.md#getParametersByOperationName) | **GET** /servicetemplates/{serviceTemplateId}/operations/{operationName}/parameters | Query input parameters of a specified operation
*ModelResourceApi* | [**getServiceTemplateById**](docs/ModelResourceApi.md#getServiceTemplateById) | **GET** /servicetemplates/{servicetemplateid} | Query service template by service template id
*ModelResourceApi* | [**getServiceTemplateOperations**](docs/ModelResourceApi.md#getServiceTemplateOperations) | **GET** /servicetemplates/{serviceTemplateId}/operations | Query operation list of service template
*ModelResourceApi* | [**getServiceTemplateParameters**](docs/ModelResourceApi.md#getServiceTemplateParameters) | **GET** /servicetemplates/{servicetemplateid}/parameters | Query input parameters of service template
*ModelResourceApi* | [**getServiceTemplateRawData**](docs/ModelResourceApi.md#getServiceTemplateRawData) | **POST** /servicetemplates/queryingrawdata | Query raw data of a service template by csar id
*ModelResourceApi* | [**getServiceTemplates**](docs/ModelResourceApi.md#getServiceTemplates) | **GET** /servicetemplates | Query service template by filter conditions
*ModelResourceApi* | [**test**](docs/ModelResourceApi.md#test) | **GET** /servicetemplates/test | test
*PackageResourceApi* | [**delPackage**](docs/PackageResourceApi.md#delPackage) | **DELETE** /csars/{csarId} | delete a package
*PackageResourceApi* | [**getCsarFileUri**](docs/PackageResourceApi.md#getCsarFileUri) | **GET** /csars/{csarId}/files | get csar file uri by csarId
*PackageResourceApi* | [**queryPackageById**](docs/PackageResourceApi.md#queryPackageById) | **GET** /csars/{csarId} | get csar package list
*PackageResourceApi* | [**queryPackageListByCond**](docs/PackageResourceApi.md#queryPackageListByCond) | **GET** /csars | get csar package list by condition
*PackageResourceApi* | [**updatePackageStatus**](docs/PackageResourceApi.md#updatePackageStatus) | **PUT** /csars/{csarId} | update csar package status
*PackageResourceApi* | [**uploadPackage**](docs/PackageResourceApi.md#uploadPackage) | **POST** /csars | upload csar package


## Documentation for Models

 - [CapReqMapping](docs/CapReqMapping.md)
 - [CommonErrorResponse](docs/CommonErrorResponse.md)
 - [CsarFileUriResponse](docs/CsarFileUriResponse.md)
 - [FormDataContentDisposition](docs/FormDataContentDisposition.md)
 - [InputParameter](docs/InputParameter.md)
 - [KeyValuePair](docs/KeyValuePair.md)
 - [NodeTemplate](docs/NodeTemplate.md)
 - [OutputParameter](docs/OutputParameter.md)
 - [PackageMeta](docs/PackageMeta.md)
 - [Parameters](docs/Parameters.md)
 - [QueryRawDataCondition](docs/QueryRawDataCondition.md)
 - [RelationShip](docs/RelationShip.md)
 - [ServiceTemplate](docs/ServiceTemplate.md)
 - [ServiceTemplateOperation](docs/ServiceTemplateOperation.md)
 - [ServiceTemplateRawData](docs/ServiceTemplateRawData.md)
 - [SubstitutionMapping](docs/SubstitutionMapping.md)
 - [UploadPackageResponse](docs/UploadPackageResponse.md)


## Documentation for Authorization

All endpoints do not require authorization.
Authentication schemes defined for the API:

## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issue.

## Author



