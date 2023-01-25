package com.ilvo.neo4jdemo.nodes.properties;

import com.ilvo.neo4jdemo.utils.EmptyUtil;

public class TagLine {

    private final String tagLine;

    private TagLine(final String tagLine){
        this.tagLine = tagLine;
    }

    public static TagLine of(final String tagLine){
        
        if(EmptyUtil.isEmpty(tagLine)){
            throw new IllegalArgumentException("Empty TagLine is not allowed");
        }

        return new TagLine(tagLine);
    }

    
    public String getTagLine(){
        return tagLine;
    }
    
}
