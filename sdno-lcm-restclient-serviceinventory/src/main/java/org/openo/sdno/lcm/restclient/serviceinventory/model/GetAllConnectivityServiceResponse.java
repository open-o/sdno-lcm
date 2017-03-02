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
import java.util.ArrayList;
import java.util.List;
import org.openo.sdno.lcm.restclient.serviceinventory.model.GetConnectivityServiceResponseSample;


/**
 * GetAllConnectivityServiceResponse
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-02-28T17:11:52.313Z")
public class GetAllConnectivityServiceResponse   {
  @JsonProperty("total")
  private Integer total = null;

  @JsonProperty("pageSize")
  private Integer pageSize = null;

  @JsonProperty("totalPageNum")
  private Integer totalPageNum = null;

  @JsonProperty("currentPage")
  private Integer currentPage = null;

  @JsonProperty("objects")
  private List<GetConnectivityServiceResponseSample> objects = new ArrayList<GetConnectivityServiceResponseSample>();

  public GetAllConnectivityServiceResponse total(Integer total) {
    this.total = total;
    return this;
  }

   /**
   * Get total
   * @return total
  **/
  @ApiModelProperty(example = "null", value = "")
  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public GetAllConnectivityServiceResponse pageSize(Integer pageSize) {
    this.pageSize = pageSize;
    return this;
  }

   /**
   * Get pageSize
   * @return pageSize
  **/
  @ApiModelProperty(example = "null", value = "")
  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public GetAllConnectivityServiceResponse totalPageNum(Integer totalPageNum) {
    this.totalPageNum = totalPageNum;
    return this;
  }

   /**
   * Get totalPageNum
   * @return totalPageNum
  **/
  @ApiModelProperty(example = "null", value = "")
  public Integer getTotalPageNum() {
    return totalPageNum;
  }

  public void setTotalPageNum(Integer totalPageNum) {
    this.totalPageNum = totalPageNum;
  }

  public GetAllConnectivityServiceResponse currentPage(Integer currentPage) {
    this.currentPage = currentPage;
    return this;
  }

   /**
   * Get currentPage
   * @return currentPage
  **/
  @ApiModelProperty(example = "null", value = "")
  public Integer getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(Integer currentPage) {
    this.currentPage = currentPage;
  }

  public GetAllConnectivityServiceResponse objects(List<GetConnectivityServiceResponseSample> objects) {
    this.objects = objects;
    return this;
  }

   /**
   * Get objects
   * @return objects
  **/
  @ApiModelProperty(example = "null", value = "")
  public List<GetConnectivityServiceResponseSample> getObjects() {
    return objects;
  }

  public void setObjects(List<GetConnectivityServiceResponseSample> objects) {
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
    GetAllConnectivityServiceResponse getAllConnectivityServiceResponse = (GetAllConnectivityServiceResponse) o;
    return Objects.equals(this.total, getAllConnectivityServiceResponse.total) &&
        Objects.equals(this.pageSize, getAllConnectivityServiceResponse.pageSize) &&
        Objects.equals(this.totalPageNum, getAllConnectivityServiceResponse.totalPageNum) &&
        Objects.equals(this.currentPage, getAllConnectivityServiceResponse.currentPage) &&
        Objects.equals(this.objects, getAllConnectivityServiceResponse.objects);
  }

  @Override
  public int hashCode() {
    return Objects.hash(total, pageSize, totalPageNum, currentPage, objects);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetAllConnectivityServiceResponse {\n");
    
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
    sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
    sb.append("    totalPageNum: ").append(toIndentedString(totalPageNum)).append("\n");
    sb.append("    currentPage: ").append(toIndentedString(currentPage)).append("\n");
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

