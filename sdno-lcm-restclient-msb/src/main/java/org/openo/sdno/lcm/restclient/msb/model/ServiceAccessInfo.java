/**
 * MicroService Bus rest API
 * 
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package org.openo.sdno.lcm.restclient.msb.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * ServiceAccessInfo
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-02-20T12:04:17.236Z")
public class ServiceAccessInfo   {
  @JsonProperty("serviceType")
  private String serviceType = null;

  @JsonProperty("serviceName")
  private String serviceName = null;

  @JsonProperty("version")
  private String version = null;

  @JsonProperty("accessAddr")
  private String accessAddr = null;

  public ServiceAccessInfo serviceType(String serviceType) {
    this.serviceType = serviceType;
    return this;
  }

   /**
   * Get serviceType
   * @return serviceType
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getServiceType() {
    return serviceType;
  }

  public void setServiceType(String serviceType) {
    this.serviceType = serviceType;
  }

  public ServiceAccessInfo serviceName(String serviceName) {
    this.serviceName = serviceName;
    return this;
  }

   /**
   * Get serviceName
   * @return serviceName
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getServiceName() {
    return serviceName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  public ServiceAccessInfo version(String version) {
    this.version = version;
    return this;
  }

   /**
   * Get version
   * @return version
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public ServiceAccessInfo accessAddr(String accessAddr) {
    this.accessAddr = accessAddr;
    return this;
  }

   /**
   * Get accessAddr
   * @return accessAddr
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getAccessAddr() {
    return accessAddr;
  }

  public void setAccessAddr(String accessAddr) {
    this.accessAddr = accessAddr;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ServiceAccessInfo serviceAccessInfo = (ServiceAccessInfo) o;
    return Objects.equals(this.serviceType, serviceAccessInfo.serviceType) &&
        Objects.equals(this.serviceName, serviceAccessInfo.serviceName) &&
        Objects.equals(this.version, serviceAccessInfo.version) &&
        Objects.equals(this.accessAddr, serviceAccessInfo.accessAddr);
  }

  @Override
  public int hashCode() {
    return Objects.hash(serviceType, serviceName, version, accessAddr);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ServiceAccessInfo {\n");
    
    sb.append("    serviceType: ").append(toIndentedString(serviceType)).append("\n");
    sb.append("    serviceName: ").append(toIndentedString(serviceName)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    accessAddr: ").append(toIndentedString(accessAddr)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

