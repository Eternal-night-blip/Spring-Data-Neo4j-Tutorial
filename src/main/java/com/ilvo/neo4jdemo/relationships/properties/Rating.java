package com.ilvo.neo4jdemo.relationships.properties;

public class Rating {

    private final Integer rank;

    private Rating(final Integer rank){
        this.rank = rank;
    }

    public static Rating of(final Integer rank){
        
        if(rank == null){
            throw new IllegalArgumentException("null rating 0f movie is not allowed");
        }

        if(rank < 0 || rank > 100){
            throw new IllegalArgumentException("rating of the movie can't exceed the range 0 to 100");
        }

        return new Rating(rank);
    }

    public Integer get(){
        return rank;
    }
    
}
