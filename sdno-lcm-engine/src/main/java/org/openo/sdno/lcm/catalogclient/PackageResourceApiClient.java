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
package org.openo.sdno.lcm.catalogclient;

import java.util.List;

import org.openo.sdno.lcm.restclient.catalog.ApiException;
import org.openo.sdno.lcm.restclient.catalog.model.CsarFileUriResponse;
import org.openo.sdno.lcm.restclient.catalog.model.PackageMeta;

public interface PackageResourceApiClient {

	/**
	 * delete a package
	 * 
	 * @param csarId
	 *            csar Id (required)
	 * @throws ApiException
	 *             if fails to make API call
	 */
	void delPackage(String csarId);

	/**
	 * get csar file uri by csarId
	 * 
	 * @param csarId
	 *            csar Id (required)
	 * @param relativePath
	 *            csar file path (required)
	 * @return CsarFileUriResponse
	 * @throws ApiException
	 *             if fails to make API call
	 */
	CsarFileUriResponse getCsarFileUri(String csarId, String relativePath);

	/**
	 * get csar package list
	 * 
	 * @param csarId
	 *            csar id (required)
	 * @return List<PackageMeta>
	 * @throws ApiException
	 *             if fails to make API call
	 */
	List<PackageMeta> queryPackageById(String csarId);

	/**
	 * get csar package list by condition
	 * 
	 * @param name
	 *            csar name (optional)
	 * @param provider
	 *            csar provider (optional)
	 * @param version
	 *            csar version (optional)
	 * @param deletionPending
	 *            delay to delete (optional)
	 * @param type
	 *            csar type (optional)
	 * @return List<PackageMeta>
	 * @throws ApiException
	 *             if fails to make API call
	 */
	List<PackageMeta> queryPackageListByCond(String name, String provider, String version, String deletionPending,
			String type);

}