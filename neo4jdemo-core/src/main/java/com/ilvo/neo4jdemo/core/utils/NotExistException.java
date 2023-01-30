package com.ilvo.neo4jdemo.core.utils;

public class NotExistException extends NullPointerException {

    public NotExistException(){
        super();
    }

    public NotExistException(String str){
        super(str);
    }
    
}
