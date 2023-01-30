package com.ilvo.neo4jdemo.core.dto;

public class ReviewedProperties {

    private final String summary;
    private final Integer rating;

    public ReviewedProperties(String summary, Integer rating){
        this.summary = summary;
        this.rating = rating;
    }

    public String getSummary(){
        return summary;
    }

    public Integer getRating(){
        return rating;
    }
    
}
