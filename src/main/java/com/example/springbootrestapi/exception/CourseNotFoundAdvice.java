package com.example.springbootrestapi.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class CourseNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(CourseNotFoundException.class)
    public String courseNotFoundHandler(CourseNotFoundException ex) {
        return ex.getMessage();
    }
}
