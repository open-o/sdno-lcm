package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * SDN-O Service Instance Creation Request
 **/

@ApiModel(description = "SDN-O Service Instance Creation Request")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2017-01-11T11:14:05.407Z")

public class NsCreationRequest   {
  
  private String nsdId = null;
  private String nsName = null;
  private String description = null;

  /**
   * ID of the template in catalog used to create the SDN-O service instance
   **/
  public NsCreationRequest nsdId(String nsdId) {
    this.nsdId = nsdId;
    return this;
  }

  
  @ApiModelProperty(required = true, value = "ID of the template in catalog used to create the SDN-O service instance")
  @JsonProperty("nsdId")
  public String getNsdId() {
    return nsdId;
  }
  public void setNsdId(String nsdId) {
    this.nsdId = nsdId;
  }

  /**
   * name of the SDN-O service instance to be created
   **/
  public NsCreationRequest nsName(String nsName) {
    this.nsName = nsName;
    return this;
  }

  
  @ApiModelProperty(required = true, value = "name of the SDN-O service instance to be created")
  @JsonProperty("nsName")
  public String getNsName() {
    return nsName;
  }
  public void setNsName(String nsName) {
    this.nsName = nsName;
  }

  /**
   * description of the SDN-O service instance to be created
   **/
  public NsCreationRequest description(String description) {
    this.description = description;
    return this;
  }

  
  @ApiModelProperty(value = "description of the SDN-O service instance to be created")
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NsCreationRequest nsCreationRequest = (NsCreationRequest) o;
    return Objects.equals(nsdId, nsCreationRequest.nsdId) &&
        Objects.equals(nsName, nsCreationRequest.nsName) &&
        Objects.equals(description, nsCreationRequest.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nsdId, nsName, description);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsCreationRequest {\n");
    
    sb.append("    nsdId: ").append(toIndentedString(nsdId)).append("\n");
    sb.append("    nsName: ").append(toIndentedString(nsName)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
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

