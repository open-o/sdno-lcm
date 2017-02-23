/**
 * connectivity services
 * API of connectivity services
 *
 * OpenAPI spec version: 1.1.0
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


package org.openo.sdno.lcm.restclient.serviceinventory.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openo.sdno.lcm.restclient.serviceinventory.model.ResponseConnectivityService;


/**
 * ConnectivityServiceResp
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-02-15T16:47:04.731Z")
public class ConnectivityServiceResp   {
  @JsonProperty("objects")
  private Map<String, List<ResponseConnectivityService>> objects = new HashMap<String, List<ResponseConnectivityService>>();

  public ConnectivityServiceResp objects(Map<String, List<ResponseConnectivityService>> objects) {
    this.objects = objects;
    return this;
  }

   /**
   *  connectivity service map
   * @return objects
  **/
  @ApiModelProperty(example = "null", value = " connectivity service map")
  public Map<String, List<ResponseConnectivityService>> getObjects() {
    return objects;
  }

  public void setObjects(Map<String, List<ResponseConnectivityService>> objects) {
    this.objects = objects;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConnectivityServiceResp connectivityServiceResp = (ConnectivityServiceResp) o;
    return Objects.equals(this.objects, connectivityServiceResp.objects);
  }

  @Override
  public int hashCode() {
    return Objects.hash(objects);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConnectivityServiceResp {\n");
    
    sb.append("    objects: ").append(toIndentedString(objects)).append("\n");
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
