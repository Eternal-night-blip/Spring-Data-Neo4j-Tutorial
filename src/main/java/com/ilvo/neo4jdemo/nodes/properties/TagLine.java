package com.ilvo.neo4jdemo.nodes.properties;

import com.ilvo.neo4jdemo.utils.EmptyUtil;

public class TagLine {

    private final String description;

    private TagLine(final String description){
        this.description = description;
    }

    public static TagLine of(final String description){
        
        if(EmptyUtil.isEmpty(description)){
            throw new IllegalArgumentException("Empty TagLine is not allowed");
        }

        return new TagLine(description);
    }

    
    public String get(){
        return description;
    }
    
}
