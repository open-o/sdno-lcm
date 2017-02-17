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

package org.openo.sdno.lcm.templatemodel.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
// @JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"template_name", "template_author", "version", "vendor", "id", "csarProvider"})
public class Metadata {

    @JsonProperty("template_name")
    private String templateName;

    @JsonProperty("template_author")
    private String templateAuthor;

    @JsonProperty("version")
    private Double version;

    @JsonProperty("vendor")
    private String vendor;

    @JsonProperty("id")
    private String id;

    @JsonProperty("csarProvider")
    private String csarProvider;
    
    @JsonProperty("csarType")
    private String csarType;
    
    @JsonProperty("csarVersion")
    private String csarVersion;

    @JsonProperty("designer")
    private String designer;
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("type")
    private String type;

    private final static long serialVersionUID = -5697192037962940540L;

    @JsonProperty("template_name")
    public String getTemplateName() {
        return templateName;
    }

    @JsonProperty("template_name")
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    @JsonProperty("template_author")
    public String getTemplateAuthor() {
        return templateAuthor;
    }

    @JsonProperty("template_author")
    public void setTemplateAuthor(String templateAuthor) {
        this.templateAuthor = templateAuthor;
    }

    @JsonProperty("version")
    public Double getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(Double version) {
        this.version = version;
    }

    @JsonProperty("vendor")
    public String getVendor() {
        return vendor;
    }

    @JsonProperty("vendor")
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((templateAuthor == null) ? 0 : templateAuthor.hashCode());
        result = prime * result + ((templateName == null) ? 0 : templateName.hashCode());
        result = prime * result + ((vendor == null) ? 0 : vendor.hashCode());
        result = prime * result + ((version == null) ? 0 : version.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(getClass() != obj.getClass()) {
            return false;
        }
        Metadata other = (Metadata)obj;
        if(id == null) {
            if(other.id != null) {
                return false;
            }
        } else if(!id.equals(other.id)) {
            return false;
        }
        if(templateAuthor == null) {
            if(other.templateAuthor != null) {
                return false;
            }
        } else if(!templateAuthor.equals(other.templateAuthor)) {
            return false;
        }
        if(templateName == null) {
            if(other.templateName != null) {
                return false;
            }
        } else if(!templateName.equals(other.templateName)) {
            return false;
        }
        if(vendor == null) {
            if(other.vendor != null) {
                return false;
            }
        } else if(!vendor.equals(other.vendor)) {
            return false;
        }
        if(version == null) {
            if(other.version != null) {
                return false;
            }
        } else if(!version.equals(other.version)) {
            return false;
        }
        return true;
    }

    @JsonProperty("csarProvider")
    public String getCsarProvider() {
        return csarProvider;
    }

    @JsonProperty("csarProvider")
    public void setCsarProvider(String csarProvider) {
        this.csarProvider = csarProvider;
    }

    @JsonProperty("csarType")
    public String getCsarType() {
        return csarType;
    }

    @JsonProperty("csarType")
    public void setCsarType(String csarType) {
        this.csarType = csarType;
    }

    
    /**
     * @return the csarVersion
     */
    public String getCsarVersion() {
        return csarVersion;
    }

    
    /**
     * @param csarVersion the csarVersion to set
     */
    public void setCsarVersion(String csarVersion) {
        this.csarVersion = csarVersion;
    }

    @JsonProperty("designer")
    public String getDesigner() {
        return designer;
    }

    @JsonProperty("designer")
    public void setDesigner(String designer) {
        this.designer = designer;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

}
