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

package org.openo.sdno.lcm.webapp;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.openo.sdno.lcm.exception.ExternalComponentException;
import org.openo.sdno.lcm.exception.InvalidInputException;
import org.openo.sdno.lcm.exception.LcmInternalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@EnableWebMvc
@ControllerAdvice
public class LcmExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String DETAIL = "detail";

    private static final String MESSAGE = "message";

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    ResponseEntity<Object> handleControllerException(HttpServletRequest req, Throwable ex) {

        Map<String, String> responseBody = new HashMap<>();
        responseBody.put(DETAIL, ex.getMessage());
        if(ex instanceof LcmInternalException) {
            responseBody.put(MESSAGE, "An internal LCM error occurred");
            return new ResponseEntity<Object>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);

        } else if(ex instanceof ExternalComponentException) {
            responseBody.put(MESSAGE, "An external component could not be accessed or returned an error reponse");
            return new ResponseEntity<Object>(responseBody, HttpStatus.NOT_FOUND);

        } else if(ex instanceof InvalidInputException) {
            responseBody.put(MESSAGE, "Invalid input was received");
            return new ResponseEntity<Object>(responseBody, HttpStatus.BAD_REQUEST);

        } else {
            responseBody.put(MESSAGE, "An unexpected error has occurred");
            return new ResponseEntity<Object>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
