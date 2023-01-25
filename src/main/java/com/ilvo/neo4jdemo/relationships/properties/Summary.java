package com.ilvo.neo4jdemo.relationships.properties;

import com.ilvo.neo4jdemo.utils.EmptyUtil;

public class Summary {

    private final String summary;

    private Summary(final String summary){
        this.summary = summary;
    }

    public static Summary of(final String summary){
        
        if(EmptyUtil.isEmpty(summary)){
            throw new IllegalArgumentException("Empty summary is not allowed");
        }

        return new Summary(summary);
    }

    public String getSummary(){
        return summary;
    }
    
}
