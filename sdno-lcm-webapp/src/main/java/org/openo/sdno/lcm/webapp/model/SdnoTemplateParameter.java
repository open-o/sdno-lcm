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
package org.openo.sdno.lcm.webapp.model;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * parameter used by SDN-O to instantiate its service instance. it&#39;s fundamentally a name-value pair.
 **/

@ApiModel(description = "parameter used by SDN-O to instantiate its service instance. it's fundamentally a name-value pair.")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2017-01-11T11:14:05.407Z")

public class SdnoTemplateParameter   {
  
  private String name = null;
  private String value = null;

  /**
   * name of the parameter
   **/
  public SdnoTemplateParameter name(String name) {
    this.name = name;
    return this;
  }

  
  @ApiModelProperty(required = true, value = "name of the parameter")
  @JsonProperty("name")
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  /**
   * value of the parameter
   **/
  public SdnoTemplateParameter value(String value) {
    this.value = value;
    return this;
  }

  
  @ApiModelProperty(required = true, value = "value of the parameter")
  @JsonProperty("value")
  public String getValue() {
    return value;
  }
  public void setValue(String value) {
    this.value = value;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SdnoTemplateParameter sdnoTemplateParameter = (SdnoTemplateParameter) o;
    return Objects.equals(name, sdnoTemplateParameter.name) &&
        Objects.equals(value, sdnoTemplateParameter.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, value);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SdnoTemplateParameter {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
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

