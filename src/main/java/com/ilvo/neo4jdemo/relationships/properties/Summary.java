package com.ilvo.neo4jdemo.relationships.properties;

import com.ilvo.neo4jdemo.utils.EmptyUtil;

public class Summary {

    private final String description;

    private Summary(final String description){
        this.description = description;
    }

    public static Summary of(final String description){
        
        if(EmptyUtil.isEmpty(description)){
            throw new IllegalArgumentException("Empty summary is not allowed");
        }

        return new Summary(description);
    }

    public String get(){
        return description;
    }
    
}
