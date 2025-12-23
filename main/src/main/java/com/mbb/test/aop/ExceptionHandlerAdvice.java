package com.mbb.test.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mbb.test.error.ErrorResponse;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    private static final Logger log = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        log.error("Unhandled exception: {}", ex.getMessage(), ex);
        ErrorResponse errorResponse = new ErrorResponse("INTERNAL_ERROR", "Internal server error");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
