/*
 * Copyright 2017 Huawei Technologies Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.openo.sdno.lcm.util;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import io.swagger.models.Swagger;
import io.swagger.models.Path;
import io.swagger.models.HttpMethod;
import io.swagger.models.Operation;
import io.swagger.models.parameters.Parameter;
import io.swagger.models.parameters.BodyParameter;
import io.swagger.models.parameters.HeaderParameter;
import io.swagger.models.parameters.PathParameter;

/**
 * This class provides methods for extracting information from Swagger conveniently.
 */
public class SwaggerUtils {
    private static final String BODY_PARAMETER_STR = "body";
    private static final String PATH_PARAMETER_STR = "path";
    private static final String HEADER_PARAMETER_STR = "header";

    /**
     * Get consume of the corresponding operation from Swagger.
     * Notes:
     * Consumes defined in specific operation will override consumes in the whole swagger.
     * It is the value of Content-Type header to be sent in the corresponding Http request.
     * Hence, althrough a list is used, there should be one value.
     *
     * @param swagger Swagger Specification
     * @param apiUrl URL used for this operation
     * @param method HttpMethod used for this operation
     * @return Consume of the corresponding operation (Content-Type value expected by SDN-O micro-service)
     */
    public static String getConsumeFromSwagger(final Swagger swagger, final String apiUrl, final HttpMethod method) {
        List<String> swaggerConsumes = swagger.getConsumes();

        Operation operation = getOperationFromSwagger(swagger, apiUrl, method);
        if(operation == null) return null;

        List<String> operationConsumes = operation.getConsumes();

        if(operationConsumes!=null && operationConsumes.size()>0) return operationConsumes.get(0);
        else if(swaggerConsumes!=null && swaggerConsumes.size()>0) return swaggerConsumes.get(0);
        else return null;
    }


    /**
     * Get a list of Header Parameters of the corresponding operation from Swagger.
     * Notes:
     * Header parameters determines the headers to be sent with the corresponding Http request.
     *
     * @param swagger Swagger Specification
     * @param apiUrl URL used for this operation
     * @param method HttpMethod used for this operation
     * @return a list of header parameters defined in the corresponding operation.
     */
    public static List<HeaderParameter> getHeaderParametersFromSwagger(final Swagger swagger, final String apiUrl, final HttpMethod method) {
        ArrayList<HeaderParameter> result = new ArrayList<HeaderParameter>();

        List<Parameter> parameters = getAllParametersFromSwagger(swagger,apiUrl, method);
        for(Parameter p : parameters) {
            if(p.getIn().equals(HEADER_PARAMETER_STR)) {
                HeaderParameter hp = (HeaderParameter)p;
                result.add(hp);
            }
        }
        return result;
    }


    /**
     * Get a list of Path Parameters of the corresponding operation from Swagger.
     * Notes:
     * Path parameters are the parameters locates in API Path.
     * We need substitute some placeholder of API path with corresponding value.
     *
     * @param swagger Swagger Specification
     * @param apiUrl URL used for this operation
     * @param method HttpMethod used for this operation
     * @return a list of path parameters defined in the corresponding operation.
     */
    public static List<PathParameter> getPathParametersFromSwagger(final Swagger swagger, final String apiUrl, final HttpMethod method) {
        ArrayList<PathParameter> result = new ArrayList<PathParameter>();

        List<Parameter> parameters = getAllParametersFromSwagger(swagger,apiUrl, method);

        for(Parameter p : parameters) {
            if(p.getIn().equals(PATH_PARAMETER_STR)) {
                PathParameter pp = (PathParameter)p;
                result.add(pp);
            }
        }

        return result;
    }

    /**
     * Get body model name of the corresponding operation from Swagger.
     * Notes:
     * It is the name of the schema (model name) used by the body of Http request.
     * BodyParameter (hence schema) may not exist for some operations, such as GET and DELETE operations.
     *
     * @param swagger Swagger Specification
     * @param apiUrl URL used for this operation
     * @param method HttpMethod used for this operation
     * @return the model name defined for the corrresponding operation. It could be null in some cases.
     */
    public static String getBodyModelNameFromSwagger(final Swagger swagger, final String apiUrl, final HttpMethod method) {
        BodyParameter bp = getBodyParameterFromSwagger(swagger, apiUrl, method);
        if(bp==null) return null;

        String result = null;
        try {
            String reference = bp.getSchema().getReference();
            String[] parts = reference.split("/");
            result = parts[parts.length -1];
        } catch(Exception e) { //catch potential null exception.
            e.printStackTrace();
        }

        return result;
    }


    /**
     * Get BodyParameter of the corresponding operation from Swagger.
     * Notes:
     * BodyParameter may not exist for some operations, such as GET and DELETE operations.
     * It contains the name of data model to be sent in the body of Http request.
     *
     * @param swagger Swagger Specification
     * @param apiUrl URL used for this operation
     * @param method HttpMethod used for this operation
     * @return the BodyParameter defined for the corrresponding operation. It could be null in some cases.
     */
    private static BodyParameter getBodyParameterFromSwagger(final Swagger swagger, final String apiUrl, final HttpMethod method) {
        List<Parameter> parameters = getAllParametersFromSwagger(swagger,apiUrl, method);

        for(Parameter p : parameters) {
            if(p.getIn().equals(BODY_PARAMETER_STR)) {
                BodyParameter bp = (BodyParameter)p;
                return bp;
            }
        }
        return null;
    }

    /**
     * Get all parameters from Swagger based on API-URL and HttpMethod.
     * Notes:
     * Not only Operation, parameters may also be defined in the Path of Swagger model.
     *
     * @param swagger Swagger Specification
     * @param apiUrl URL used for this operation
     * @param method HttpMethod used for this operation
     * @return all parameters related with the Operation determined by API-URL and HttpMethod.
     */
    private static List<Parameter> getAllParametersFromSwagger(final Swagger swagger, final String apiUrl, final HttpMethod method) {
        ArrayList<Parameter> result = new ArrayList<Parameter>();

        Path path = getPathFromSwagger(swagger, apiUrl);
        if(path==null) return result;

        List<Parameter> pathParameters = path.getParameters();
        if(pathParameters!=null) result.addAll(pathParameters);

        Operation operation = getOperationFromSwagger(swagger, apiUrl, method);
        if(operation!=null) {
            List<Parameter> operationParameters = operation.getParameters();
            if(operationParameters!=null) result.addAll(operationParameters);
        }

        return result;
    }

    /**
     * Get Operation (one part of swagger model) from Swagger based on API-URL and HttpMethod.
     * Notes:
     * The input parameter if the full API URL. However, Swagger may use basePath and we need handle this scenario.
     *
     * @param swagger Swagger Specification
     * @param apiUrl URL used for this operation
     * @param method HttpMethod used for this operation
     * @return Operation determined by API-URL and HttpMethod.
     */
    private static Operation getOperationFromSwagger(final Swagger swagger, final String apiUrl, final HttpMethod method) {
        Path path = getPathFromSwagger(swagger, apiUrl);
        if(path==null) return null;

        Map<HttpMethod, Operation> operations = path.getOperationMap();
        return operations.get(method);
    }


    /**
     * Get Path (one part of swagger model) from Swagger based on API-URL.
     * Notes:
     * The input parameter if the full API URL. However, Swagger may use basePath and we need handle this scenario.
     *
     * @param swagger Swagger Specification
     * @param apiUrl URL of this API
     * @return API Path of the corresponding API URL.
     */
    private static Path getPathFromSwagger(final Swagger swagger, final String apiUrl) {
        String baseUrl = swagger.getBasePath();
        if(baseUrl.length() > apiUrl.length()) return null;

        Map<String, Path> paths = swagger.getPaths();
        String searchKey = apiUrl.substring(baseUrl.length());

        return paths.get(searchKey);
    }
}