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

package org.openo.sdno.lcm.engine;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


import org.openo.sdno.lcm.restclient.catalog.ApiException;
import org.openo.sdno.lcm.restclient.catalog.api.PackageResourceApi;
import org.openo.sdno.lcm.restclient.catalog.model.PackageMeta;


public class LcmStateEngine {

    private static final Logger log = Logger.getLogger("LcmStateEngine");

    public static void execute(Map<String, String> params) {

        // check if the nsid is included in params
        String nsid = params.get("nsid");
        if(null != params.get("nsid")) {

            log.info("nsid is " + nsid);
        } else {
            log.info("nsid is not found in params");
            executeCreate();
        }

    }

    public static void executeCreate() {

        // get TOSCA template

        // get template instance

        // get state from instance
        
        
        try {
            PackageResourceApi packageResourceApi = new PackageResourceApi();
            List<PackageMeta> queryPackageListByCond =
                    packageResourceApi.queryPackageListByCond(null, null, null, null, null);
            log.severe(queryPackageListByCond.size() + " packages have been returned in the query");
            for(PackageMeta pm : queryPackageListByCond) {
                log.severe(pm.toString());
            }
        } catch(ApiException e) {

            log.severe("Package query has failed with error: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
