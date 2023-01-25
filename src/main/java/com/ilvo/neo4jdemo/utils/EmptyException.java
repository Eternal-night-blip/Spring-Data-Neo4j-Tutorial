package com.ilvo.neo4jdemo.utils;

public class EmptyException extends NullPointerException {

    public EmptyException(){
        super();
    }

    public EmptyException(String str){
        super(str);
    }
    
}
