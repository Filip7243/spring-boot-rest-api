package com.example.springbootrestapi.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class LimitsReachedAdvice {

    @ResponseBody
    @ExceptionHandler(LimitsReachedException.class)
    public ResponseEntity<?> limitsReachedExceptionHandler(LimitsReachedException e) {
        return ResponseEntity.status(409).body(e.getMessage());
    }
}
