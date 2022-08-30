package com.revature.byron_fedele_p0.util.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(){}
    public ResourceNotFoundException(String s) {
        super(s);
    }
}