package com.ilvo.neo4jdemo.nodes.properties;

import com.ilvo.neo4jdemo.utils.EmptyUtil;

public final class Name {

    private final String name;

    private Name(final String name){
        this.name = name;
    }

    public static Name of(final String name){
        
        if(EmptyUtil.isEmpty(name)){
            throw new IllegalArgumentException("Empty name is not allowed");
        }

        return new Name(name);
    }

    public String getName(){
        return name;
    }
    
}
