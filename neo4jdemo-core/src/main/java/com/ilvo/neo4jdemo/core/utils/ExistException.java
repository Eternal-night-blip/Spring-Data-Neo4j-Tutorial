package com.ilvo.neo4jdemo.core.utils;

public class ExistException extends RuntimeException {

    public ExistException(){
        super();
    }

    public ExistException(String str){
        super(str);
    }
    
}
