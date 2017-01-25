package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * SDN-O NS Package Onboarding Request
 **/

@ApiModel(description = "SDN-O NS Package Onboarding Request")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2017-01-11T11:14:05.407Z")

public class PackageOnboardRequest   {
  
  private String csarId = null;

  /**
   * UUID of the SDN-O NS package to be onboarded.
   **/
  public PackageOnboardRequest csarId(String csarId) {
    this.csarId = csarId;
    return this;
  }

  
  @ApiModelProperty(required = true, value = "UUID of the SDN-O NS package to be onboarded.")
  @JsonProperty("csarId")
  public String getCsarId() {
    return csarId;
  }
  public void setCsarId(String csarId) {
    this.csarId = csarId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PackageOnboardRequest packageOnboardRequest = (PackageOnboardRequest) o;
    return Objects.equals(csarId, packageOnboardRequest.csarId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(csarId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PackageOnboardRequest {\n");
    
    sb.append("    csarId: ").append(toIndentedString(csarId)).append("\n");
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

