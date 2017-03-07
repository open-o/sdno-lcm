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

import com.fasterxml.jackson.databind.JsonNode;

import io.swagger.models.Swagger;

/**
 * Responsible for downloading and caching the .csar files, and retrieving required artifacts from
 * them.
 */
public interface CsarHandler {

    /**
     * Get the swagger spec object from the CSAR.
     * 
     * @param csarId the ID of the CSAR
     * @param swaggerPath the path to the swagger spec in the specified CSAR
     * @return the Swagger object
     */
    Swagger getSwaggerSpec(String csarId, String swaggerPath);

    /**
     * Get the mapper spec from the CSAR.
     * 
     * @param csarId the ID of the CSAR
     * @param mapperPath the path to the mapper spec in the specified CSAR
     * @return the JsonNode containing the parsed mapper spec JSON
     */
    JsonNode getMapperSpec(String csarId, String mapperPath);

}
