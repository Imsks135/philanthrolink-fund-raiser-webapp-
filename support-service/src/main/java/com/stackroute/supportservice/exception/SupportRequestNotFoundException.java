package com.stackroute.supportservice.exception;

public class SupportRequestNotFoundException extends RuntimeException{

    public SupportRequestNotFoundException(String message) {
        super(message);
    }

}
