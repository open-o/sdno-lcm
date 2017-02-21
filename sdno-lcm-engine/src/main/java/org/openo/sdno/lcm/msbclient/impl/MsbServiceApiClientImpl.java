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

package org.openo.sdno.lcm.msbclient.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.validator.routines.InetAddressValidator;
import org.openo.sdno.lcm.exception.ExternalComponentException;
import org.openo.sdno.lcm.exception.LcmInternalException;
import org.openo.sdno.lcm.msbclient.MsbServiceApiClient;
import org.openo.sdno.lcm.restclient.msb.api.MSBServiceResourceApi;
import org.openo.sdno.lcm.restclient.msb.model.MicroServiceFullInfo;
import org.openo.sdno.lcm.restclient.msb.model.MicroServiceInfo;
import org.openo.sdno.lcm.restclient.msb.model.MicroServiceInfo.LbPolicyEnum;
import org.openo.sdno.lcm.restclient.msb.model.MicroServiceInfo.ProtocolEnum;
import org.openo.sdno.lcm.restclient.msb.model.Node;
import org.openo.sdno.lcm.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:config.properties")
public class MsbServiceApiClientImpl implements MsbServiceApiClient {

    private static final String SERVICE_VERSION = "v1";

    private static final String SERVICE_NAME = "sdnonslcm";

    private static final String SERVER_CONTEXT_PATH = "server.contextPath";

    private static final String SERVER_PORT = "server.port";

    private static final String SDNO_LCM_IP = "SDNO_LCM_IP";

    private final Logger log = Logger.getLogger("MsbServiceApiClientImpl");

    @Autowired
    private Environment env;

    @Override
    public MicroServiceFullInfo register() {

        Node node = new Node();
        node.setPort(env.getProperty(SERVER_PORT));

        String lcmIp = System.getenv(SDNO_LCM_IP);
        if(lcmIp == null || (!InetAddressValidator.getInstance().isValid(lcmIp))) {

            log.warning("Invalid or null LCM IP address read from environment variable " + SDNO_LCM_IP + ": " + lcmIp);
            lcmIp = env.getProperty(Constants.SDNO_LCM_DEFAULT_IP);
            if(lcmIp == null || (!InetAddressValidator.getInstance().isValid(lcmIp))) {

                throw new LcmInternalException("Unable to read default IP for LCM from config.properties");
            }
        }

        node.setIp(lcmIp);
        List<Node> nodes = new ArrayList<Node>();
        nodes.add(node);
        MicroServiceInfo microServiceInfo = new MicroServiceInfo();
        microServiceInfo.setUrl(env.getProperty(SERVER_CONTEXT_PATH)); // ?
        microServiceInfo.setProtocol(ProtocolEnum.REST);
        microServiceInfo.setServiceName(SERVICE_NAME); // ?
        microServiceInfo.setVersion(SERVICE_VERSION);
        microServiceInfo.setNodes(nodes);
        microServiceInfo.setLbPolicy(LbPolicyEnum.ROUND_ROBIN);

        log.info("LCM registration info for MSB:\n" + microServiceInfo.toString());
        try {
            // false param so we remove nslcm if it was registered!
            MicroServiceFullInfo registration =
                    getMSBServiceResourceApi().addMicroService(microServiceInfo, false, null);
            log.info("Registration status " + registration.getStatus());
            return registration;
        } catch(Exception e) {
            log.severe("Failed to register with the microservice bus due to " + e.getMessage());
            throw new ExternalComponentException("Failed to add microservice", e);
        }
    }

    private MSBServiceResourceApi getMSBServiceResourceApi() {
        // It's recommended to create an instance of `ApiClient` per thread in a multithreaded
        // environment
        MSBServiceResourceApi msbServiceResourceApi = new MSBServiceResourceApi();
        msbServiceResourceApi.getApiClient()
                .setBasePath(env.getRequiredProperty(Constants.COMMON_SERVICES_MSB_BASE_PATH));
        return msbServiceResourceApi;
    }

    public void setEnv(Environment env) {
        this.env = env;
    }
}
