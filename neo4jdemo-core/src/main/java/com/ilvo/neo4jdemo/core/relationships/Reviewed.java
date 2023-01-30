package com.ilvo.neo4jdemo.core.relationships;

import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import com.ilvo.neo4jdemo.core.nodes.Movie;



@RelationshipProperties
public class Reviewed {
    
    @RelationshipId
    private Long id;  
    
    @Property("summary")
    private String summary;

    @Property("rating")
    private Integer rating;
 
    @TargetNode
    private Movie movie;


    public Reviewed(Movie movie, String summary, Integer rating){
        this.movie = movie;
        this.summary = summary;
        this.rating = rating;
    }

    public String getSummary(){
        return summary;
    }

    public Integer getRating(){
        return rating;
    }

    public Movie getMovie(){
        return movie;
    }
    
}
