package com.okaru.springboot.exception;

public class ResourceNoFoundException extends  RuntimeException{

    public ResourceNoFoundException(String message){
        super(message);
    }

    public  ResourceNoFoundException(String message, Throwable cause){
        super(message, cause);
    }

}
