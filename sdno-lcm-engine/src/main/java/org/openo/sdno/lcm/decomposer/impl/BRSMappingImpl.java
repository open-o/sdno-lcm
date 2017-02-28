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

package org.openo.sdno.lcm.decomposer.impl;

import java.util.List;
import java.util.Stack;
import java.util.UUID;
import java.util.logging.Logger;

import org.openo.sdno.lcm.decomposer.BRSMapping;
import org.openo.sdno.lcm.templatemodel.csar.Csar;
import org.openo.sdno.lcm.templatemodel.service.Dependency;
import org.openo.sdno.lcm.templatemodel.service.Instance;
import org.openo.sdno.lcm.templatemodel.service.Node;
import org.openo.sdno.lcm.model.workplan.WorkPlan;
import org.springframework.stereotype.Component;

import java.util.Map;
import org.openo.sdno.lcm.util.NetworkElementMO;

@Component
public class BRSMappingImpl implements BRSMapping {
	
	private final Logger log = Logger.getLogger("BRSMappingImpl");
	
	@Override
    public NetworkElementMO retrieveResourceFromBRS (String nodeName, Map <String, String> condition) {
	
		NetworkElementMO neMO = new NetworkElementMO();
		
		log.info(String.format("Node name %s ", nodeName) );
		
		//check nodeName
		// if (nodeName == "Site") :
		//      Send request to BRS to retrieve SITE info
		//      BrsRestconfProxy.get (SITEURI, "" , condition)
		//      return SiteMO
		//     
		//else:
		//      send REST request to BRS to retrieve Managed Element info
		//      RestfulResponse response = BrsRestconfProxy.get (NEURI, "", condition);
		//      read response and transform it into NetworkElementMO
		//      return NetworkElementMO
		return neMO;
	}

}
