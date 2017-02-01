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

package org.openo.sdno.catalogclient.impl;

import java.util.List;

import org.openo.sdno.catalogclient.ModelResourceApiClient;
import org.openo.sdno.lcm.restclient.catalog.ApiException;
import org.openo.sdno.lcm.restclient.catalog.model.InputParameter;
import org.openo.sdno.lcm.restclient.catalog.model.NodeTemplate;
import org.openo.sdno.lcm.restclient.catalog.model.Parameters;
import org.openo.sdno.lcm.restclient.catalog.model.QueryRawDataCondition;
import org.openo.sdno.lcm.restclient.catalog.model.ServiceTemplate;
import org.openo.sdno.lcm.restclient.catalog.model.ServiceTemplateOperation;
import org.openo.sdno.lcm.restclient.catalog.model.ServiceTemplateRawData;
import org.springframework.stereotype.Component;

/**
 * @author mark
 *
 */
@Component
public class ModelResourceApiClientImpl implements ModelResourceApiClient {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openo.sdno.catalogclient.ModelResourceApiClient#
	 * getNestingServiceTemplate(java.lang.String)
	 */
	@Override
	public List<ServiceTemplate> getNestingServiceTemplate(String nodeTypeIds) throws ApiException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.openo.sdno.catalogclient.ModelResourceApiClient#getNodeTemplateById(
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public NodeTemplate getNodeTemplateById(String serviceTemplateId, String nodeTemplateId) throws ApiException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openo.sdno.catalogclient.ModelResourceApiClient#
	 * getNodeTemplatesByType(java.lang.String, java.lang.String)
	 */
	@Override
	public List<NodeTemplate> getNodeTemplatesByType(String serviceTemplateId, String types) throws ApiException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openo.sdno.catalogclient.ModelResourceApiClient#
	 * getParametersByOperationName(java.lang.String, java.lang.String)
	 */
	@Override
	public List<InputParameter> getParametersByOperationName(String serviceTemplateId, String operationName)
			throws ApiException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openo.sdno.catalogclient.ModelResourceApiClient#
	 * getServiceTemplateById(java.lang.String)
	 */
	@Override
	public ServiceTemplate getServiceTemplateById(String servicetemplateid) throws ApiException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openo.sdno.catalogclient.ModelResourceApiClient#
	 * getServiceTemplateOperations(java.lang.String)
	 */
	@Override
	public List<ServiceTemplateOperation> getServiceTemplateOperations(String serviceTemplateId) throws ApiException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openo.sdno.catalogclient.ModelResourceApiClient#
	 * getServiceTemplateParameters(java.lang.String)
	 */
	@Override
	public Parameters getServiceTemplateParameters(String servicetemplateid) throws ApiException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openo.sdno.catalogclient.ModelResourceApiClient#
	 * getServiceTemplateRawData(org.openo.sdno.lcm.restclient.catalog.model.
	 * QueryRawDataCondition)
	 */
	@Override
	public ServiceTemplateRawData getServiceTemplateRawData(QueryRawDataCondition body) throws ApiException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.openo.sdno.catalogclient.ModelResourceApiClient#getServiceTemplates(
	 * java.lang.String, java.lang.Boolean)
	 */
	@Override
	public List<ServiceTemplate> getServiceTemplates(String status, Boolean deletionPending) throws ApiException {
		// TODO Auto-generated method stub
		return null;
	}

}
