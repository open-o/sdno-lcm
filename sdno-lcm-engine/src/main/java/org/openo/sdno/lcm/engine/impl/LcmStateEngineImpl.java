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

package org.openo.sdno.lcm.engine.impl;

import java.util.Map;
import java.util.logging.Logger;

import org.openo.sdno.lcm.ariaclient.ParserApiClient;
import org.openo.sdno.lcm.catalogclient.ModelResourceApiClient;
import org.openo.sdno.lcm.catalogclient.PackageResourceApiClient;
import org.openo.sdno.lcm.engine.GenericWorkflowId;
import org.openo.sdno.lcm.engine.LcmStateEngine;
import org.openo.sdno.lcm.exception.LcmInternalException;
import org.openo.sdno.lcm.serviceinventoryclient.DefaultMssApiClient;
import org.openo.sdno.lcm.statetablehandler.StateTableHandler;
import org.openo.sdno.lcm.templateinstanceparser.TemplateInstanceParser;
import org.openo.sdno.lcm.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LcmStateEngineImpl implements LcmStateEngine {

    private static final Logger log = Logger.getLogger("LcmStateEngineImpl");

    private StateTableHandler stateTableHandler;

    private ModelResourceApiClient modelResourceApiClient;

    private PackageResourceApiClient packageResourceApiClient;

    private ParserApiClient parserApiClient;

    private DefaultMssApiClient defaultMssApiClient;

    private TemplateInstanceParser templateInstanceParser;

    private WorkflowRegistry workflowRegistry;

    /*
     * (non-Javadoc)
     * @see org.openo.sdno.lcm.engine.impl.LcmStateEngine#execute(java.util.Map)
     */
    @Override
    public Map<String, Object> execute(Map<String, Object> params) {

        if(params == null) {

            throw new IllegalArgumentException("input params may not be null");
        }
        log.fine("params:" + params.toString());

        // check if the nsid is included in params
        if(params.containsKey(Constants.LCM_NBI_SERVICE_ID)
                && !((String)params.get(Constants.LCM_NBI_SERVICE_ID)).isEmpty()) {

            String nsid = (String)params.get(Constants.LCM_NBI_SERVICE_ID);
            log.info(Constants.LCM_NBI_SERVICE_ID + " is " + nsid);

            // TODO execute the appropriate workflow...
            // TODO get the service from the inventory
            // stateTableHandler.validateServiceTransition(null, null, null);
            // TODO get state from instance - except in create case we know already
            String dummyWflId = "deploy";
            return workflowRegistry.executeWorkflow(dummyWflId, params);

        } else {
            log.info(Constants.LCM_NBI_SERVICE_ID + " is not found in params, beginning service creation");
            // no connectivity service ID, so this must be a create
            if(params.containsKey(Constants.LCM_NBI_CSAR_NAME)
                    && !((String)params.get(Constants.LCM_NBI_CSAR_NAME)).isEmpty()) {

                String csarName = (String)params.get(Constants.LCM_NBI_CSAR_NAME);
                log.info(Constants.LCM_NBI_CSAR_NAME + " is " + csarName);
                return workflowRegistry.executeWorkflow(GenericWorkflowId.CREATE.toString(), params);
            } else {
                log.info(Constants.LCM_NBI_CSAR_NAME + " is not found in params");
                throw new LcmInternalException("No workflows possible with the parameters given");
            }
        }
    }

    @Autowired
    public void setStateTableHandler(StateTableHandler stateTableHandler) {
        this.stateTableHandler = stateTableHandler;
    }

    @Autowired
    public void setModelResourceApiClient(ModelResourceApiClient modelResourceApiClient) {
        this.modelResourceApiClient = modelResourceApiClient;
    }

    @Autowired
    public void setPackageResourceApiClient(PackageResourceApiClient packageResourceApiClient) {
        this.packageResourceApiClient = packageResourceApiClient;
    }

    @Autowired
    public void setParserApiClient(ParserApiClient parserApiClient) {
        this.parserApiClient = parserApiClient;
    }

    @Autowired
    public void setDefaultMssApiClient(DefaultMssApiClient defaultMssApiClient) {
        this.defaultMssApiClient = defaultMssApiClient;
    }

    @Autowired
    public void setTemplateInstanceParser(TemplateInstanceParser templateInstanceParser) {
        this.templateInstanceParser = templateInstanceParser;
    }

    @Autowired
    public void setWorkflowRegistry(WorkflowRegistry workflowRegistry) {
        this.workflowRegistry = workflowRegistry;
    }

}
