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

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.openo.sdno.catalogclient.ModelResourceApiClient;
import org.openo.sdno.catalogclient.PackageResourceApiClient;
import org.openo.sdno.lcm.restclient.catalog.ApiException;
import org.openo.sdno.lcm.restclient.catalog.api.PackageResourceApi;
import org.openo.sdno.lcm.restclient.catalog.model.PackageMeta;
import org.openo.sdno.lcm.statetablehandler.StateTableHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LcmStateEngine {

    private static final Logger log = Logger.getLogger("LcmStateEngine");
    
    private StateTableHandler stateTableHandler;
    
    private ModelResourceApiClient modelResourceApiClient;
    
    private PackageResourceApiClient packageResourceApiClient;

    public Map<String, Object> execute(Map<String, String> params) {
    	
    	if(params == null) {
    		
    		throw new InvalidParameterException("input params may not be null");
    	}

        // check if the nsid is included in params
        String nsid = params.get("nsid");
        String csarId = params.get("nsName"); // ????????????????????
        if(null != params.get("nsid")) {

            log.info("nsid is " + nsid);
        } else {
            log.info("nsid is not found in params");
            return executeCreate(csarId);
        }
		return null;

    }

    Map<String, Object> executeCreate(String csarId) {

    	if(csarId == null || csarId.isEmpty()) {
    		
    		throw new InvalidParameterException("csarId may not be empty or null");
    	}
        // get TOSCA template
    	List<PackageMeta> packageMetaList = packageResourceApiClient.queryPackageById(csarId);
    	

        // get template instance

        // get state from instance
        
        
        try {
            // TODO let spring manage this
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
		return null;
    }

	public StateTableHandler getStateTableHandler() {
		return stateTableHandler;
	}

	@Autowired
	public void setStateTableHandler(StateTableHandler stateTableHandler) {
		this.stateTableHandler = stateTableHandler;
	}

	public ModelResourceApiClient getModelResourceApiClient() {
		return modelResourceApiClient;
	}

	@Autowired
	public void setModelResourceApiClient(ModelResourceApiClient modelResourceApiClient) {
		this.modelResourceApiClient = modelResourceApiClient;
	}

	public PackageResourceApiClient getPackageResourceApiClient() {
		return packageResourceApiClient;
	}

	@Autowired
	public void setPackageResourceApiClient(PackageResourceApiClient packageResourceApiClient) {
		this.packageResourceApiClient = packageResourceApiClient;
	}

}
