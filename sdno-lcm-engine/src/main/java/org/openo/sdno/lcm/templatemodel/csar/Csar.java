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

package org.openo.sdno.lcm.templatemodel.csar;

public class Csar {

    /**
     * @param csarId
     * @param name
     */
    public Csar(String csarId, String name, String downloadUri) {
        super();
        this.csarId = csarId;
        this.name = name;
        this.downloadUri = downloadUri;
    }

    private String csarId = null;

    private String name = null;

    private String downloadUri = null;

    
    public String getDownloadUri() {
        return downloadUri;
    }

    
    public void setDownloadUri(String downloadUri) {
        this.downloadUri = downloadUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCsarId() {
        return csarId;
    }

    public void setCsarId(String csarId) {
        this.csarId = csarId;
    }

}
