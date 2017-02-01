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
package org.openo.sdno.catalogclient;

import java.util.List;

import org.openo.sdno.lcm.restclient.catalog.ApiException;
import org.openo.sdno.lcm.restclient.catalog.model.InputParameter;
import org.openo.sdno.lcm.restclient.catalog.model.NodeTemplate;
import org.openo.sdno.lcm.restclient.catalog.model.Parameters;
import org.openo.sdno.lcm.restclient.catalog.model.QueryRawDataCondition;
import org.openo.sdno.lcm.restclient.catalog.model.ServiceTemplate;
import org.openo.sdno.lcm.restclient.catalog.model.ServiceTemplateOperation;
import org.openo.sdno.lcm.restclient.catalog.model.ServiceTemplateRawData;

public interface ModelResourceApiClient {

	/**
	 * Query nesting service template of a node type
	 * 
	 * @param nodeTypeIds
	 *            Node type ids splited by &#39;,&#39;. Such as
	 *            &#39;tosca.nodes.nfv.VDU,tosca.nodes.nfv.VL&#39;. (required)
	 * @return List<ServiceTemplate>
	 * @throws ApiException
	 *             if fails to make API call
	 */
	List<ServiceTemplate> getNestingServiceTemplate(String nodeTypeIds) throws ApiException;

	/**
	 * Query node template by node template id
	 * 
	 * @param serviceTemplateId
	 *            Service Template Id (required)
	 * @param nodeTemplateId
	 *            Node Template Id (required)
	 * @return NodeTemplate
	 * @throws ApiException
	 *             if fails to make API call
	 */
	NodeTemplate getNodeTemplateById(String serviceTemplateId, String nodeTemplateId) throws ApiException;

	/**
	 * Query node template list of a specified service template
	 * 
	 * @param serviceTemplateId
	 *            Service Template Id (required)
	 * @param types
	 *            The type of node template (optional)
	 * @return List<NodeTemplate>
	 * @throws ApiException
	 *             if fails to make API call
	 */
	List<NodeTemplate> getNodeTemplatesByType(String serviceTemplateId, String types) throws ApiException;

	/**
	 * Query input parameters of a specified operation
	 * 
	 * @param serviceTemplateId
	 *            Service Template Id (required)
	 * @param operationName
	 *            Operation Name (required)
	 * @return List<InputParameter>
	 * @throws ApiException
	 *             if fails to make API call
	 */
	List<InputParameter> getParametersByOperationName(String serviceTemplateId, String operationName)
			throws ApiException;

	/**
	 * Query service template by service template id
	 * 
	 * @param servicetemplateid
	 *            service template id (required)
	 * @return ServiceTemplate
	 * @throws ApiException
	 *             if fails to make API call
	 */
	ServiceTemplate getServiceTemplateById(String servicetemplateid) throws ApiException;

	/**
	 * Query operation list of service template
	 * 
	 * @param serviceTemplateId
	 *            Service Template Id (required)
	 * @return List<ServiceTemplateOperation>
	 * @throws ApiException
	 *             if fails to make API call
	 */
	List<ServiceTemplateOperation> getServiceTemplateOperations(String serviceTemplateId) throws ApiException;

	/**
	 * Query input parameters of service template
	 * 
	 * @param servicetemplateid
	 *            service template id (required)
	 * @return Parameters
	 * @throws ApiException
	 *             if fails to make API call
	 */
	Parameters getServiceTemplateParameters(String servicetemplateid) throws ApiException;

	/**
	 * Query raw data of a service template by csar id
	 * 
	 * @param body
	 *            Query Service Template Raw Data Condition (required)
	 * @return ServiceTemplateRawData
	 * @throws ApiException
	 *             if fails to make API call
	 */
	ServiceTemplateRawData getServiceTemplateRawData(QueryRawDataCondition body) throws ApiException;

	/**
	 * Query service template by filter conditions
	 * 
	 * @param status
	 *            template status (optional)
	 * @param deletionPending
	 *            delay to delete (optional)
	 * @return List<ServiceTemplate>
	 * @throws ApiException
	 *             if fails to make API call
	 */
	List<ServiceTemplate> getServiceTemplates(String status, Boolean deletionPending) throws ApiException;

}