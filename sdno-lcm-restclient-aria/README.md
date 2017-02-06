# restclient-aria

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
    <artifactId>restclient-aria</artifactId>
    <version>1.1.0-SNAPSHOT</version>
    <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
compile "org.openo.sdno.lcm:restclient-aria:1.1.0-SNAPSHOT"
```

### Others

At first generate the JAR by executing:

    mvn package

Then manually install the following JARs:

* target/restclient-aria-1.1.0-SNAPSHOT.jar
* target/lib/*.jar

## Getting Started

Please follow the [installation](#installation) instruction and execute the following Java code:

```java

import org.openo.sdno.lcm.restclient.aria.*;
import org.openo.sdno.lcm.restclient.aria.auth.*;
import org.openo.sdno.lcm.restclient.aria.model.*;
import org.openo.sdno.lcm.restclient.aria.api.ParserApi;

import java.io.File;
import java.util.*;

public class ParserApiExample {

    public static void main(String[] args) {
        
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
    }
}

```

## Documentation for API Endpoints

All URIs are relative to *https://localhost*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*ParserApi* | [**parseControllerInstanceFile**](docs/ParserApi.md#parseControllerInstanceFile) | **GET** /instance | Create instance from file using given path
*ParserApi* | [**parseControllerInstanceIndirect**](docs/ParserApi.md#parseControllerInstanceIndirect) | **POST** /indirect/instance | Create instance from blueprint specyfied by URI
*ParserApi* | [**parseControllerInstanceUpload**](docs/ParserApi.md#parseControllerInstanceUpload) | **POST** /instance | Create instance from uploaded blueprint file
*ParserApi* | [**parseControllerModelFile**](docs/ParserApi.md#parseControllerModelFile) | **GET** /model | Create model from blueprint file using given path
*ParserApi* | [**parseControllerModelIndirect**](docs/ParserApi.md#parseControllerModelIndirect) | **POST** /indirect/model | Create model from blueprint specyfied by URI
*ParserApi* | [**parseControllerModelUpload**](docs/ParserApi.md#parseControllerModelUpload) | **POST** /model | Create model from uploaded blueprint file
*ParserApi* | [**parseControllerValidateFile**](docs/ParserApi.md#parseControllerValidateFile) | **GET** /validate | Validate blueprint file using given path
*ParserApi* | [**parseControllerValidateIndirect**](docs/ParserApi.md#parseControllerValidateIndirect) | **POST** /indirect/validate | Validate blueprint file using given URI
*ParserApi* | [**parseControllerValidateUpload**](docs/ParserApi.md#parseControllerValidateUpload) | **POST** /validate | Validate uploaded blueprint file


## Documentation for Models

 - [IndirectData](docs/IndirectData.md)


## Documentation for Authorization

All endpoints do not require authorization.
Authentication schemes defined for the API:

## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issue.

## Author



