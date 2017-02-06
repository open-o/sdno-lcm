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

package org.openo.sdno.lcm.ariaclient;

import org.openo.sdno.lcm.restclient.aria.model.IndirectData;

public interface ParserApiClient {
	
	  /**
	   * Create instance from file using given path
	   * 
	   * @param path Path to blueprint file (required)
	   * @param inputs Inputs for instance creation from blueprint (optional)
	   * @return Object
	   * @throws ApiException if fails to make API call
	   */
	  public Object parseControllerInstanceFile(String path, String inputs);
	  
	  /**
	   * Create instance from blueprint specyfied by URI
	   * 
	   * @param indirectData Blueprint specification (required)
	   * @return Object
	   * @throws ApiException if fails to make API call
	   */
	  public Object parseControllerInstanceIndirect(IndirectData indirectData);
	  
	  /**
	   * Create instance from uploaded blueprint file
	   * 
	   * @param uploadContent  (required)
	   * @param inputs Inputs for instance creation from blueprint (optional)
	   * @return Object
	   * @throws ApiException if fails to make API call
	   */
	  public Object parseControllerInstanceUpload(Object uploadContent, String inputs);
	  
	  /**
	   * Create model from blueprint file using given path
	   * 
	   * @param path Path to blueprint file (required)
	   * @return Object
	   * @throws ApiException if fails to make API call
	   */
	  public Object parseControllerModelFile(String path);
	  
	  /**
	   * Create model from blueprint specyfied by URI
	   * 
	   * @param indirectData Blueprint specification (required)
	   * @return Object
	   * @throws ApiException if fails to make API call
	   */
	  public Object parseControllerModelIndirect(IndirectData indirectData);
	  
	  /**
	   * Create model from uploaded blueprint file
	   * 
	   * @param uploadContent  (required)
	   * @param inputs Inputs for instance creation from blueprint (optional)
	   * @return Object
	   * @throws ApiException if fails to make API call
	   */
	  public Object parseControllerModelUpload(Object uploadContent, String inputs);
	  
	  /**
	   * Validate blueprint file using given path
	   * 
	   * @param path Path to blueprint file (required)
	   * @return Object
	   * @throws ApiException if fails to make API call
	   */
	  public Object parseControllerValidateFile(String path);
	  
	  /**
	   * Validate blueprint file using given URI
	   * 
	   * @param indirectData Blueprint specification (required)
	   * @return Object
	   * @throws ApiException if fails to make API call
	   */
	  public Object parseControllerValidateIndirect(IndirectData indirectData);
	  
	  /**
	   * Validate uploaded blueprint file
	   * 
	   * @param uploadContent  (required)
	   * @param inputs Inputs for instance creation from blueprint (optional)
	   * @return Object
	   * @throws ApiException if fails to make API call
	   */
	  public Object parseControllerValidateUpload(Object uploadContent, String inputs);

}
