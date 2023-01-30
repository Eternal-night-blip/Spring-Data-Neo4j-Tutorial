package com.ilvo.neo4jdemo.core.nodes.properties;

import com.ilvo.neo4jdemo.core.utils.EmptyUtil;

public class Title {

    private final String description;

    private Title(final String description){
        this.description = description;
    }

    public static Title of(final String description){
        
        if(EmptyUtil.isEmpty(description)){
            throw new IllegalArgumentException("Empty title is not allowed");
        }

        return new Title(description);
    }
    
    public String get(){
        return description;
    }

}
