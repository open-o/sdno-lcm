package org.openo.sdno.lcm.webapp.model;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openo.sdno.lcm.webapp.model.SdnoTemplateParameter;
import java.util.ArrayList;
import java.util.List;



/**
 * SDN-O Service Instance Instantiation Request
 **/

@ApiModel(description = "SDN-O Service Instance Instantiation Request")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2017-01-11T11:14:05.407Z")

public class NsInstantiationRequest   {
  
  private String nsInstanceId = null;
  private List<SdnoTemplateParameter> additionalParamForNS = new ArrayList<SdnoTemplateParameter>();

  /**
   * ID of the SDN-O service instance to be instantiated
   **/
  public NsInstantiationRequest nsInstanceId(String nsInstanceId) {
    this.nsInstanceId = nsInstanceId;
    return this;
  }

  
  @ApiModelProperty(required = true, value = "ID of the SDN-O service instance to be instantiated")
  @JsonProperty("nsInstanceId")
  public String getNsInstanceId() {
    return nsInstanceId;
  }
  public void setNsInstanceId(String nsInstanceId) {
    this.nsInstanceId = nsInstanceId;
  }

  /**
   * parameters used to instantiate this SDN-O service instance
   **/
  public NsInstantiationRequest additionalParamForNS(List<SdnoTemplateParameter> additionalParamForNS) {
    this.additionalParamForNS = additionalParamForNS;
    return this;
  }

  
  @ApiModelProperty(value = "parameters used to instantiate this SDN-O service instance")
  @JsonProperty("additionalParamForNS")
  public List<SdnoTemplateParameter> getAdditionalParamForNS() {
    return additionalParamForNS;
  }
  public void setAdditionalParamForNS(List<SdnoTemplateParameter> additionalParamForNS) {
    this.additionalParamForNS = additionalParamForNS;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NsInstantiationRequest nsInstantiationRequest = (NsInstantiationRequest) o;
    return Objects.equals(nsInstanceId, nsInstantiationRequest.nsInstanceId) &&
        Objects.equals(additionalParamForNS, nsInstantiationRequest.additionalParamForNS);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nsInstanceId, additionalParamForNS);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstantiationRequest {\n");
    
    sb.append("    nsInstanceId: ").append(toIndentedString(nsInstanceId)).append("\n");
    sb.append("    additionalParamForNS: ").append(toIndentedString(additionalParamForNS)).append("\n");
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

