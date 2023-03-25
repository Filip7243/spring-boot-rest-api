package com.example.springbootrestapi.exception;

public class LimitsReachedException extends RuntimeException {

    public LimitsReachedException() {
        super("Limits are reached!");
    }
}
