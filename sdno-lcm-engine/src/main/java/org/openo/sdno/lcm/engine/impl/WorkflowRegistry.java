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
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import org.openo.sdno.lcm.engine.RegisterWorkflow;
import org.openo.sdno.lcm.engine.Workflow;
import org.openo.sdno.lcm.exception.LcmInternalException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Component to manage workflow registration and execution.
 */
@Component
public class WorkflowRegistry implements ApplicationContextAware {

    private static final Logger log = Logger.getLogger("WorkflowRegistry");

    private ApplicationContext applicationContext;

    Map<String, Workflow> workflowMap = new ConcurrentHashMap<>();

    /**
     * Execute a workflow identified by ID, previously registered during startup.
     * 
     * @param workflowId the id of the workflow to execute - could be generic or custom
     * @param params the parameter map
     * @return the response parameter map
     */
    public Map<String, Object> executeWorkflow(String workflowId, Map<String, Object> params) {
        if(null == params) {
            
            throw new LcmInternalException(String.format("Failed to execute workflow %s due to null params", workflowId));
        }
        Workflow workflow = workflowMap.get(workflowId);
        if(null == workflow) {
            
            throw new LcmInternalException(String.format("Failed to execute workflow %s as no such workflow registered", workflowId));
        }
        return workflow.execute(params);
    }

    /**
     * Runs on startup. Register all beans implementing RegisterWorkflow as workflows.
     */
    public void registerWorkflows() {

        Map<String, RegisterWorkflow> workflowRegBeans = applicationContext.getBeansOfType(RegisterWorkflow.class);
        int i = 0;
        for(RegisterWorkflow workflowReg : workflowRegBeans.values()) {
            String workflowRegId = workflowReg.getWorkflowId();
            workflowMap.put(workflowRegId, workflowReg.getWorkflowToRegister());
            log.info("Registered workflow with ID: " + workflowRegId);
            i++;
        }
        log.info("Registered " + i + " workflows");
    }

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework
     * .context.ApplicationContext)
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        this.applicationContext = applicationContext;
    }
}
