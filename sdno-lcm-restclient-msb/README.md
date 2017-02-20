# sdno-lcm-restclient-msb

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
    <artifactId>sdno-lcm-restclient-msb</artifactId>
    <version>1.1.0-SNAPSHOT</version>
    <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
compile "org.openo.sdno.lcm:sdno-lcm-restclient-msb:1.1.0-SNAPSHOT"
```

### Others

At first generate the JAR by executing:

    mvn package

Then manually install the following JARs:

* target/sdno-lcm-restclient-msb-1.1.0-SNAPSHOT.jar
* target/lib/*.jar

## Getting Started

Please follow the [installation](#installation) instruction and execute the following Java code:

```java

import org.openo.sdno.lcm.restclient.msb.*;
import org.openo.sdno.lcm.restclient.msb.auth.*;
import org.openo.sdno.lcm.restclient.msb.model.*;
import org.openo.sdno.lcm.restclient.msb.api.MSBServiceResourceApi;

import java.io.File;
import java.util.*;

public class MSBServiceResourceApiExample {

    public static void main(String[] args) {
        
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
    }
}

```

## Documentation for API Endpoints

All URIs are relative to *https://localhost/api/microservices/v1*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*MSBServiceResourceApi* | [**addMicroService**](docs/MSBServiceResourceApi.md#addMicroService) | **POST** /services | add one microservice 
*MSBServiceResourceApi* | [**deleteMicroService**](docs/MSBServiceResourceApi.md#deleteMicroService) | **DELETE** /services/{serviceName}/version/{version} | delete one full microservice by serviceName and version
*MSBServiceResourceApi* | [**getAllMicroService**](docs/MSBServiceResourceApi.md#getAllMicroService) | **GET** /services | get all microservices 
*MSBServiceResourceApi* | [**getMicroService**](docs/MSBServiceResourceApi.md#getMicroService) | **GET** /services/{serviceName}/version/{version} | get one microservice 
*MSBServiceResourceApi* | [**updateMicroService**](docs/MSBServiceResourceApi.md#updateMicroService) | **PUT** /services/{serviceName}/version/{version} | update one microservice by serviceName and version
*MSBServiceResourceApi* | [**updateServiceStatus**](docs/MSBServiceResourceApi.md#updateServiceStatus) | **PUT** /services/{serviceName}/version/{version}/status/{status} | update  microservice status by serviceName and version


## Documentation for Models

 - [ApiRouteInfo](docs/ApiRouteInfo.md)
 - [CustomRouteInfo](docs/CustomRouteInfo.md)
 - [DiscoverInfo](docs/DiscoverInfo.md)
 - [Gauges](docs/Gauges.md)
 - [HttpMetrics](docs/HttpMetrics.md)
 - [IuiRouteInfo](docs/IuiRouteInfo.md)
 - [JVMMetrics](docs/JVMMetrics.md)
 - [MetricsInfo](docs/MetricsInfo.md)
 - [MicroServiceFullInfo](docs/MicroServiceFullInfo.md)
 - [MicroServiceInfo](docs/MicroServiceInfo.md)
 - [Node](docs/Node.md)
 - [NodeInfo](docs/NodeInfo.md)
 - [RouteServer](docs/RouteServer.md)
 - [ServiceAccessInfo](docs/ServiceAccessInfo.md)
 - [Timers](docs/Timers.md)


## Documentation for Authorization

All endpoints do not require authorization.
Authentication schemes defined for the API:

## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issue.

## Author



