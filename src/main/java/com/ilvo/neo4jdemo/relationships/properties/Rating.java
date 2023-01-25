package com.ilvo.neo4jdemo.relationships.properties;

public class Rating {

    private final Integer rating;

    private Rating(final Integer rating){
        this.rating = rating;
    }

    public static Rating of(final Integer rating){
        
        if(rating == null){
            throw new IllegalArgumentException("null rating 0f movie is not allowed");
        }

        if(rating < 0 || rating > 100){
            throw new IllegalArgumentException("rating of the movie can't exceed the range 0 to 100");
        }

        return new Rating(rating);
    }

    public Integer getRating(){
        return rating;
    }
    
}
