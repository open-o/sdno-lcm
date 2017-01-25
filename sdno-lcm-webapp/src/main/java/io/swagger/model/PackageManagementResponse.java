package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * SDN-O NS Package MAnagement Operation (Onboard/Delete) Response
 **/

@ApiModel(description = "SDN-O NS Package MAnagement Operation (Onboard/Delete) Response")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2017-01-11T11:14:05.407Z")

public class PackageManagementResponse   {
  
  private String status = null;
  private String statusDescription = null;
  private String errorCode = null;

  /**
   * the status of SDN-O NS package management request
   **/
  public PackageManagementResponse status(String status) {
    this.status = status;
    return this;
  }

  
  @ApiModelProperty(required = true, value = "the status of SDN-O NS package management request")
  @JsonProperty("status")
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }

  /**
   * description of operation status
   **/
  public PackageManagementResponse statusDescription(String statusDescription) {
    this.statusDescription = statusDescription;
    return this;
  }

  
  @ApiModelProperty(value = "description of operation status")
  @JsonProperty("statusDescription")
  public String getStatusDescription() {
    return statusDescription;
  }
  public void setStatusDescription(String statusDescription) {
    this.statusDescription = statusDescription;
  }

  /**
   * the code of error (if happened)
   **/
  public PackageManagementResponse errorCode(String errorCode) {
    this.errorCode = errorCode;
    return this;
  }

  
  @ApiModelProperty(value = "the code of error (if happened)")
  @JsonProperty("errorCode")
  public String getErrorCode() {
    return errorCode;
  }
  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PackageManagementResponse packageManagementResponse = (PackageManagementResponse) o;
    return Objects.equals(status, packageManagementResponse.status) &&
        Objects.equals(statusDescription, packageManagementResponse.statusDescription) &&
        Objects.equals(errorCode, packageManagementResponse.errorCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, statusDescription, errorCode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PackageManagementResponse {\n");
    
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    statusDescription: ").append(toIndentedString(statusDescription)).append("\n");
    sb.append("    errorCode: ").append(toIndentedString(errorCode)).append("\n");
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

