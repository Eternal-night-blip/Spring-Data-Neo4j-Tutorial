package com.ilvo.neo4jdemo.nodes.properties;

import com.ilvo.neo4jdemo.utils.EmptyUtil;

public class Title {

    private final String title;

    private Title(final String title){
        this.title = title;
    }

    public static Title of(final String title){
        
        if(EmptyUtil.isEmpty(title)){
            throw new IllegalArgumentException("Empty Title is not allowed");
        }

        return new Title(title);
    }
    
    public String getTitle(){
        return title;
    }

}
