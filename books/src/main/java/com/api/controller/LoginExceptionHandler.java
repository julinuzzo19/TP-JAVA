package com.api.controller;

import com.api.exception.SystemLoginException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@ControllerAdvice(assignableTypes = LoginController.class)
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LoginExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginExceptionHandler.class);

        @ExceptionHandler(SystemLoginException.class)
        public ResponseEntity handleFirstShotNotFound(SystemLoginException ex) {
            LOGGER.error("handleFirstShotNotFound", ex);
            return new ResponseEntity(new ErrorValidation("LoginError"), HttpStatus.NOT_ACCEPTABLE);
        }
}
