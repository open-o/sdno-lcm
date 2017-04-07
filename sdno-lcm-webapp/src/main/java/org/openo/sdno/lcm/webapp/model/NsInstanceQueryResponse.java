/*
 * Copyright 2016-2017 Huawei Technologies Co., Ltd.
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
package org.openo.sdno.lcm.webapp.model;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openo.sdno.lcm.webapp.model.SdnoTemplateParameter;
import java.util.ArrayList;
import java.util.List;



/**
 * SDN-O Service Instance Query Response, i.e., details of a service instance
 **/

@ApiModel(description = "SDN-O Service Instance Query Response, i.e., details of a service instance")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2017-01-11T11:14:05.407Z")

public class NsInstanceQueryResponse   {
  
  private String id = null;
  private String name = null;
  private String nsdId = null;
  private String description = null;
  private List<SdnoTemplateParameter> additionalParams = new ArrayList<SdnoTemplateParameter>();

  /**
   * ID of the SDN-O service instance
   **/
  public NsInstanceQueryResponse id(String id) {
    this.id = id;
    return this;
  }

  
  @ApiModelProperty(required = true, value = "ID of the SDN-O service instance")
  @JsonProperty("id")
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  /**
   * name of the SDN-O service instance
   **/
  public NsInstanceQueryResponse name(String name) {
    this.name = name;
    return this;
  }

  
  @ApiModelProperty(required = true, value = "name of the SDN-O service instance")
  @JsonProperty("name")
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  /**
   * ID of the template used to create this SDN-O service instance
   **/
  public NsInstanceQueryResponse nsdId(String nsdId) {
    this.nsdId = nsdId;
    return this;
  }

  
  @ApiModelProperty(required = true, value = "ID of the template used to create this SDN-O service instance")
  @JsonProperty("nsdId")
  public String getNsdId() {
    return nsdId;
  }
  public void setNsdId(String nsdId) {
    this.nsdId = nsdId;
  }

  /**
   * description of the SDN-O service instance
   **/
  public NsInstanceQueryResponse description(String description) {
    this.description = description;
    return this;
  }

  
  @ApiModelProperty(value = "description of the SDN-O service instance")
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * parameters used to instantiate this SDN-O service instance
   **/
  public NsInstanceQueryResponse additionalParams(List<SdnoTemplateParameter> additionalParams) {
    this.additionalParams = additionalParams;
    return this;
  }

  
  @ApiModelProperty(value = "parameters used to instantiate this SDN-O service instance")
  @JsonProperty("additionalParams")
  public List<SdnoTemplateParameter> getAdditionalParams() {
    return additionalParams;
  }
  public void setAdditionalParams(List<SdnoTemplateParameter> additionalParams) {
    this.additionalParams = additionalParams;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NsInstanceQueryResponse nsInstanceQueryResponse = (NsInstanceQueryResponse) o;
    return Objects.equals(id, nsInstanceQueryResponse.id) &&
        Objects.equals(name, nsInstanceQueryResponse.name) &&
        Objects.equals(nsdId, nsInstanceQueryResponse.nsdId) &&
        Objects.equals(description, nsInstanceQueryResponse.description) &&
        Objects.equals(additionalParams, nsInstanceQueryResponse.additionalParams);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, nsdId, description, additionalParams);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstanceQueryResponse {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    nsdId: ").append(toIndentedString(nsdId)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    additionalParams: ").append("[");
    for(SdnoTemplateParameter additionalParam:additionalParams) {
        sb.append(additionalParam.toString()).append(" ");
    }
    sb.append("]\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

