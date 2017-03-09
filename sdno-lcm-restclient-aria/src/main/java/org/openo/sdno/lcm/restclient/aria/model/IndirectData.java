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

package org.openo.sdno.lcm.restclient.aria.model;

import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * IndirectData
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-02-06T13:02:54.199Z")
public class IndirectData {

    @JsonProperty("uri")
    private String uri = null;

    @JsonProperty("inputs")
    private Map<String, String> inputs = null;
    
    
    public IndirectData type(String uri) {
        this.uri = uri;
        return this;
      }


    @Override
    public int hashCode() {
        return Objects.hash();
    }


    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if(o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

    @ApiModelProperty(example = "null", value = "")    
    public String getUri() {
        return uri;
    }

    
    public void setUri(String uri) {
        this.uri = uri;
    }

    
    public Map<String, String> getInputs() {
        return inputs;
    }

    
    public void setInputs(Map<String, String> inputs) {
        this.inputs = inputs;
    }


    @Override
    public String toString() {
        return "IndirectData [uri=" + uri + ", inputs=" + inputs + "]";
    }


    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        IndirectData other = (IndirectData)obj;
        if(inputs == null) {
            if(other.inputs != null)
                return false;
        } else if(!inputs.equals(other.inputs))
            return false;
        if(uri == null) {
            if(other.uri != null)
                return false;
        } else if(!uri.equals(other.uri))
            return false;
        return true;
    }
}
