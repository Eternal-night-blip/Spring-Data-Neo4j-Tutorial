package com.ilvo.neo4jdemo.dto;

public class ReviewedProperties {

    final private String summary;
    final private Integer rating;

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
