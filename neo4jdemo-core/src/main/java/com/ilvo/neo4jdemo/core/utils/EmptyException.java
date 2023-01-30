package com.ilvo.neo4jdemo.core.utils;

public class EmptyException extends NullPointerException {

    public EmptyException(){
        super();
    }

    public EmptyException(String str){
        super(str);
    }
    
}
