package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * SDN-O Service Instance Creation Response
 **/

@ApiModel(description = "SDN-O Service Instance Creation Response")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2017-01-11T11:14:05.407Z")

public class NsCreationResponse   {
  
  private String nsInstanceId = null;

  /**
   * ID of the SDN-O service instance that is created.
   **/
  public NsCreationResponse nsInstanceId(String nsInstanceId) {
    this.nsInstanceId = nsInstanceId;
    return this;
  }

  
  @ApiModelProperty(required = true, value = "ID of the SDN-O service instance that is created.")
  @JsonProperty("nsInstanceId")
  public String getNsInstanceId() {
    return nsInstanceId;
  }
  public void setNsInstanceId(String nsInstanceId) {
    this.nsInstanceId = nsInstanceId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NsCreationResponse nsCreationResponse = (NsCreationResponse) o;
    return Objects.equals(nsInstanceId, nsCreationResponse.nsInstanceId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nsInstanceId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsCreationResponse {\n");
    
    sb.append("    nsInstanceId: ").append(toIndentedString(nsInstanceId)).append("\n");
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

