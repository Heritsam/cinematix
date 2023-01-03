package com.datscie.cinematix.utils;

public class ApplicationException extends Exception {
    private String message;
    
    public ApplicationException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
