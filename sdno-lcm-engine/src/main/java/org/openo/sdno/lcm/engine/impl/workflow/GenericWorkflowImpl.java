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

package org.openo.sdno.lcm.engine.impl.workflow;

import org.openo.sdno.lcm.catalogclient.ModelResourceApiClient;
import org.openo.sdno.lcm.catalogclient.PackageResourceApiClient;
import org.openo.sdno.lcm.decomposer.Decomposer;
import org.openo.sdno.lcm.engine.RegisterWorkflow;
import org.openo.sdno.lcm.engine.Workflow;
import org.openo.sdno.lcm.restclient.serviceinventory.model.GetConnectivityServiceResponse;
import org.openo.sdno.lcm.restclient.serviceinventory.model.GetConnectivityServiceResponseSample;
import org.openo.sdno.lcm.serviceinventoryclient.DefaultMssApiClient;
import org.openo.sdno.lcm.statetablehandler.StateTableHandler;
import org.openo.sdno.lcm.templateinstanceparser.TemplateInstanceParser;
import org.openo.sdno.lcm.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Abstract superclass of generic workflows, providing properties and showing implemented interfaces.
 */
public abstract class GenericWorkflowImpl implements Workflow, RegisterWorkflow {

    protected StateTableHandler stateTableHandler;

    protected ModelResourceApiClient modelResourceApiClient;

    protected PackageResourceApiClient packageResourceApiClient;

    protected DefaultMssApiClient defaultMssApiClient;

    protected TemplateInstanceParser templateInstanceParser;
    
    protected Decomposer decomposer;

    public GenericWorkflowImpl() {
        super();
    }

    /*
     * (non-Javadoc)
     * @see org.openo.sdno.lcm.engine.RegisterWorkflow#getWorkflowToRegister()
     */
    public Workflow getWorkflowToRegister() {

        return this;
    }
    
    protected GetConnectivityServiceResponseSample readConnectivityServiceFromMss(String serviceId) {
        GetConnectivityServiceResponse readConnectivityService =
                defaultMssApiClient.readConnectivityService(serviceId);
        return readConnectivityService.getObject();
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
    public void setDefaultMssApiClient(DefaultMssApiClient defaultMssApiClient) {
        this.defaultMssApiClient = defaultMssApiClient;
    }

    @Autowired
    public void setTemplateInstanceParser(TemplateInstanceParser templateInstanceParser) {
        this.templateInstanceParser = templateInstanceParser;
    }

    @Autowired 
    public void setDecomposer(Decomposer decomposer) {
        this.decomposer = decomposer;
    }

}
