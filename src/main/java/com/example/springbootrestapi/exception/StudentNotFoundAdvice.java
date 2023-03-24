package com.example.springbootrestapi.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class StudentNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(StudentNotFoundException.class)
    public String studentNotFoundHandler(StudentNotFoundException e) {
        return e.getMessage();
    }
}
