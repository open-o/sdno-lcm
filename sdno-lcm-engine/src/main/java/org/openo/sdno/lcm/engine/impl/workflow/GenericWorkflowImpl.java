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

import java.util.Map;
import java.util.logging.Logger;

import org.openo.sdno.lcm.ariaclient.ParserApiClient;
import org.openo.sdno.lcm.catalogclient.ModelResourceApiClient;
import org.openo.sdno.lcm.catalogclient.PackageResourceApiClient;
import org.openo.sdno.lcm.decomposer.Decomposer;
import org.openo.sdno.lcm.dispatcher.Dispatcher;
import org.openo.sdno.lcm.engine.RegisterWorkflow;
import org.openo.sdno.lcm.engine.Workflow;
import org.openo.sdno.lcm.engine.impl.WorkflowRegistry;
import org.openo.sdno.lcm.exception.LcmInternalException;
import org.openo.sdno.lcm.exception.SouthboundExecutionException;
import org.openo.sdno.lcm.filestorehandler.FileStoreHandler;
import org.openo.sdno.lcm.model.workplan.WorkPlan;
import org.openo.sdno.lcm.model.workplan.WorkPlanExecutionResult;
import org.openo.sdno.lcm.model.workplan.WorkPlanExecutionStrategy;
import org.openo.sdno.lcm.restclient.serviceinventory.model.GetConnectivityServiceResponse;
import org.openo.sdno.lcm.restclient.serviceinventory.model.GetConnectivityServiceResponseSample;
import org.openo.sdno.lcm.serviceinventoryclient.DefaultMssApiClient;
import org.openo.sdno.lcm.statetablehandler.StateTableHandler;
import org.openo.sdno.lcm.templateinstanceparser.TemplateInstanceParser;
import org.openo.sdno.lcm.templatemodel.service.Instance;
import org.openo.sdno.lcm.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Abstract superclass of generic workflows, providing spring dependencies and
 * showing implemented interfaces.
 */
public abstract class GenericWorkflowImpl implements Workflow, RegisterWorkflow {

    protected StateTableHandler stateTableHandler;

    protected ModelResourceApiClient modelResourceApiClient;

    protected PackageResourceApiClient packageResourceApiClient;

    protected DefaultMssApiClient defaultMssApiClient;

    protected TemplateInstanceParser templateInstanceParser;

    protected ParserApiClient parserApiClient;

    protected Decomposer decomposer;

    protected Dispatcher dispatcher;

    protected WorkflowRegistry workflowRegistry;

    protected Mapper mapper;

    protected FileStoreHandler fileStoreHandler;

    /**
     * Gets a certain parameter from the passed param map.
     * 
     * @param key
     *            the param key
     * @param params
     * @return
     */
    protected Object getParam(String key, Map<String, Object> params) {

        Object param = params.get(key);
        if (param == null) {

            throw new LcmInternalException(key + " may not be null");
        }
        if (param instanceof String && ((String) param).isEmpty()) {

            throw new LcmInternalException(key + "string may not be empty");
        }
        this.getLogger().fine(String.format("Value of param %s is %s", key, param.toString()));
        return param;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.openo.sdno.lcm.engine.RegisterWorkflow#getWorkflowToRegister()
     */
    @Override
    public Workflow getWorkflowToRegister() {

        return this;
    }

    /**
     * Reads a certain Connectivity Service from service inventory (MSS)
     * 
     * @param serviceId
     *            the id of the CS to read
     * @return
     */
    protected GetConnectivityServiceResponseSample readConnectivityServiceFromMss(String serviceId) {
        GetConnectivityServiceResponse readConnectivityService = defaultMssApiClient.readConnectivityService(serviceId);
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

    @Autowired
    public void setDispatcher(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Autowired
    public void setWorkflowRegistry(WorkflowRegistry workflowRegistry) {
        this.workflowRegistry = workflowRegistry;
    }

    /**
     * Generate the Workplan via decomposer and execute it via dispatcher.
     * 
     * @param csarId
     * @param apiOperation
     * @param templateInstance
     * @throws SouthboundExecutionException
     */
    protected void executeWorkplan(String csarId, String apiOperation, Instance templateInstance, String nsId)
            throws SouthboundExecutionException {

        /**
         * If this workplan is instantiate we need to store the file, if it is
         * another atomic wfl we need to recover the stored instance and unify
         * the ID properties.
         */
        this.getLogger().fine("file store action required for this workflow");
        Instance instanceFromFile = fileStoreHandler.getInstanceFromFile(nsId);
        templateInstance.fillIdPropertiesFromInstance(instanceFromFile);

        WorkPlan workPlan = this.decomposer.decompose(templateInstance, apiOperation, csarId);
        WorkPlanExecutionResult dispatchResult = dispatcher.dispatch(workPlan, WorkPlanExecutionStrategy.FAIL_FAST);
        if (!dispatchResult.getOverallResult()) {
            throw new SouthboundExecutionException("Execution failed in the SBI:\n" + dispatchResult.toString());
        }
    }

    /**
     * Get the logger specific to the workflow concrete class
     * 
     * @return
     */
    protected abstract Logger getLogger();

    @Autowired
    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    public void setFileStoreHandler(FileStoreHandler fileStoreHandler) {
        this.fileStoreHandler = fileStoreHandler;
    }

    @Autowired
    public void setParserApiClient(ParserApiClient parserApiClient) {
        this.parserApiClient = parserApiClient;
    }

}
