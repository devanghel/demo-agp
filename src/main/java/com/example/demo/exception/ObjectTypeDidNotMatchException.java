package com.example.demo.exception;


public class ObjectTypeDidNotMatchException extends Exception{


    public ObjectTypeDidNotMatchException(ObjectType objectType) {
        super("");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
