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

package org.openo.sdno.lcm.restclient.serviceinventory.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 *  connectivity services
 */
@ApiModel(description = " connectivity services")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-02-28T17:11:52.313Z")
public class ConnectivityService   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("version")
  private String version = null;

  @JsonProperty("adminStatus")
  private String adminStatus = null;

  @JsonProperty("operStatus")
  private String operStatus = null;

  @JsonProperty("actionState")
  private String actionState = null;

  @JsonProperty("statusReason")
  private String statusReason = null;

  @JsonProperty("ownerID")
  private String ownerID = null;

  @JsonProperty("tenantID")
  private String tenantID = null;

  @JsonProperty("location")
  private String location = null;

  @JsonProperty("templateId")
  private String templateId = null;

  @JsonProperty("lifecycleState")
  private String lifecycleState = null;

  public ConnectivityService id(String id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ConnectivityService name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ConnectivityService description(String description) {
    this.description = description;
    return this;
  }

   /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ConnectivityService version(String version) {
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

  public ConnectivityService adminStatus(String adminStatus) {
    this.adminStatus = adminStatus;
    return this;
  }

   /**
   * Get adminStatus
   * @return adminStatus
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getAdminStatus() {
    return adminStatus;
  }

  public void setAdminStatus(String adminStatus) {
    this.adminStatus = adminStatus;
  }

  public ConnectivityService operStatus(String operStatus) {
    this.operStatus = operStatus;
    return this;
  }

   /**
   * Get operStatus
   * @return operStatus
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getOperStatus() {
    return operStatus;
  }

  public void setOperStatus(String operStatus) {
    this.operStatus = operStatus;
  }

  public ConnectivityService actionState(String actionState) {
    this.actionState = actionState;
    return this;
  }

   /**
   * Get actionState
   * @return actionState
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getActionState() {
    return actionState;
  }

  public void setActionState(String actionState) {
    this.actionState = actionState;
  }

  public ConnectivityService statusReason(String statusReason) {
    this.statusReason = statusReason;
    return this;
  }

   /**
   * Get statusReason
   * @return statusReason
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getStatusReason() {
    return statusReason;
  }

  public void setStatusReason(String statusReason) {
    this.statusReason = statusReason;
  }

  public ConnectivityService ownerID(String ownerID) {
    this.ownerID = ownerID;
    return this;
  }

   /**
   * Get ownerID
   * @return ownerID
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getOwnerID() {
    return ownerID;
  }

  public void setOwnerID(String ownerID) {
    this.ownerID = ownerID;
  }

  public ConnectivityService tenantID(String tenantID) {
    this.tenantID = tenantID;
    return this;
  }

   /**
   * Get tenantID
   * @return tenantID
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getTenantID() {
    return tenantID;
  }

  public void setTenantID(String tenantID) {
    this.tenantID = tenantID;
  }

  public ConnectivityService location(String location) {
    this.location = location;
    return this;
  }

   /**
   * Get location
   * @return location
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public ConnectivityService templateId(String templateId) {
    this.templateId = templateId;
    return this;
  }

   /**
   * Get templateId
   * @return templateId
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getTemplateId() {
    return templateId;
  }

  public void setTemplateId(String templateId) {
    this.templateId = templateId;
  }

  public ConnectivityService lifecycleState(String lifecycleState) {
    this.lifecycleState = lifecycleState;
    return this;
  }

   /**
   * Get lifecycleState
   * @return lifecycleState
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getLifecycleState() {
    return lifecycleState;
  }

  public void setLifecycleState(String lifecycleState) {
    this.lifecycleState = lifecycleState;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConnectivityService connectivityService = (ConnectivityService) o;
    return Objects.equals(this.id, connectivityService.id) &&
        Objects.equals(this.name, connectivityService.name) &&
        Objects.equals(this.description, connectivityService.description) &&
        Objects.equals(this.version, connectivityService.version) &&
        Objects.equals(this.adminStatus, connectivityService.adminStatus) &&
        Objects.equals(this.operStatus, connectivityService.operStatus) &&
        Objects.equals(this.actionState, connectivityService.actionState) &&
        Objects.equals(this.statusReason, connectivityService.statusReason) &&
        Objects.equals(this.ownerID, connectivityService.ownerID) &&
        Objects.equals(this.tenantID, connectivityService.tenantID) &&
        Objects.equals(this.location, connectivityService.location) &&
        Objects.equals(this.templateId, connectivityService.templateId) &&
        Objects.equals(this.lifecycleState, connectivityService.lifecycleState);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, version, adminStatus, operStatus, actionState, statusReason, ownerID, tenantID, location, templateId, lifecycleState);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConnectivityService {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    adminStatus: ").append(toIndentedString(adminStatus)).append("\n");
    sb.append("    operStatus: ").append(toIndentedString(operStatus)).append("\n");
    sb.append("    actionState: ").append(toIndentedString(actionState)).append("\n");
    sb.append("    statusReason: ").append(toIndentedString(statusReason)).append("\n");
    sb.append("    ownerID: ").append(toIndentedString(ownerID)).append("\n");
    sb.append("    tenantID: ").append(toIndentedString(tenantID)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    templateId: ").append(toIndentedString(templateId)).append("\n");
    sb.append("    lifecycleState: ").append(toIndentedString(lifecycleState)).append("\n");
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

