package com.ilvo.neo4jdemo.api.request;

import com.fasterxml.jackson.annotation.JsonCreator;

public record ReviewedRequest(String name,String title, String summary, Integer rating) {
    
}
