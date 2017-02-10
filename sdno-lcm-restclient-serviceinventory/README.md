# sdno-lcm-restclient-serviceinventory

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
    <artifactId>sdno-lcm-restclient-serviceinventory</artifactId>
    <version>1.1.0-SNAPSHOT</version>
    <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
compile "org.openo.sdno.lcm:sdno-lcm-restclient-serviceinventory:1.1.0-SNAPSHOT"
```

### Others

At first generate the JAR by executing:

    mvn package

Then manually install the following JARs:

* target/sdno-lcm-restclient-serviceinventory-1.1.0-SNAPSHOT.jar
* target/lib/*.jar

## Getting Started

Please follow the [installation](#installation) instruction and execute the following Java code:

```java

import org.openo.sdno.lcm.restclient.serviceinventory.*;
import org.openo.sdno.lcm.restclient.serviceinventory.auth.*;
import org.openo.sdno.lcm.restclient.serviceinventory.model.*;
import org.openo.sdno.lcm.restclient.serviceinventory.api.DefaultApi;

import java.io.File;
import java.util.*;

public class DefaultApiExample {

    public static void main(String[] args) {
        
        DefaultApi apiInstance = new DefaultApi();
        ConnectivityService body = new ConnectivityService(); // ConnectivityService | the createConnectivityService to be created
        try {
            ResponseConnectivityService result = apiInstance.createConnectivityService(body);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DefaultApi#createConnectivityService");
            e.printStackTrace();
        }
    }
}

```

## Documentation for API Endpoints

All URIs are relative to *http://localhost/openoapi/sdnomss/v1*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*DefaultApi* | [**createConnectivityService**](docs/DefaultApi.md#createConnectivityService) | **POST** /buckets/connectivityservicedb/resources/connectivityservice/objects | 
*DefaultApi* | [**deleteConnectivityService**](docs/DefaultApi.md#deleteConnectivityService) | **DELETE** /buckets/connectivityservicedb/resources/connectivityservice/objects/{id} | 
*DefaultApi* | [**getConnectivityService**](docs/DefaultApi.md#getConnectivityService) | **GET** /buckets/connectivityservicedb/resources/connectivityservice/objects | 
*DefaultApi* | [**readConnectivityService**](docs/DefaultApi.md#readConnectivityService) | **GET** /buckets/connectivityservicedb/resources/connectivityservice/objects/{id} | 
*DefaultApi* | [**updateConnectivityService**](docs/DefaultApi.md#updateConnectivityService) | **PUT** /buckets/connectivityservicedb/resources/connectivityservice/objects/{id} | 


## Documentation for Models

 - [ConnectivityService](docs/ConnectivityService.md)
 - [ResponseConnectivityService](docs/ResponseConnectivityService.md)
 - [UpdateConnectivityService](docs/UpdateConnectivityService.md)
 - [UpdateResponseConnectivityService](docs/UpdateResponseConnectivityService.md)


## Documentation for Authorization

All endpoints do not require authorization.
Authentication schemes defined for the API:

## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issue.

## Author



