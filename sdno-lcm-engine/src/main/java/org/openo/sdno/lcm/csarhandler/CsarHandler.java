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

package org.openo.sdno.lcm.csarhandler;

import org.openo.sdno.lcm.templatemodel.csar.Csar;

import com.fasterxml.jackson.databind.JsonNode;

import io.swagger.models.Swagger;

/**
 * Responsible for downloading and caching the .csar files, and retrieving required artifacts from
 * them
 */
public interface CsarHandler {

//    /**
//     * Get the metadata of a CSAR file based on the CSAR name
//     * 
//     * @param csarName the CSAR name
//     * @return the metadata object Csar
//     */
//    Csar getCsarByName(String csarName);
//
//    /**
//     * Get the csarId based on it its name
//     * 
//     * @param csarName the name of the CSAR
//     * @return the ID the CSAR
//     */
//    String getCsarId(String csarName);

    Swagger getSwaggerSpec(String csarId, String swaggerPath);

    JsonNode getMapperSpec(String csarId, String mapperPath);

}
