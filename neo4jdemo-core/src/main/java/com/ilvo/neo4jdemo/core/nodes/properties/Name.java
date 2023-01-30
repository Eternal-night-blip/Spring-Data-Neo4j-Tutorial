package com.ilvo.neo4jdemo.core.nodes.properties;

import com.ilvo.neo4jdemo.core.utils.EmptyUtil;

public final class Name {

    private final String appellation;

    private Name(final String appellation){
        this.appellation = appellation;
    }

    public static Name of(final String appellation){
        
        if(EmptyUtil.isEmpty(appellation)){
            throw new IllegalArgumentException("Empty name is not allowed");
        }

        return new Name(appellation);
    }

    public String get(){
        return appellation;
    }
    
}
