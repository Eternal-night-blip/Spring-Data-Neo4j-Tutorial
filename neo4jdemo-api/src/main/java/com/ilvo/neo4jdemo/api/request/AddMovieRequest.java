package com.ilvo.neo4jdemo.api.request;

import com.fasterxml.jackson.annotation.JsonCreator;

public record AddMovieRequest(String title,String tagLine,Integer releasedYear) {
    
}
