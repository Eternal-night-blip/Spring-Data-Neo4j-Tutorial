package com.ilvo.neo4jdemo.core.relationships;


import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import com.ilvo.neo4jdemo.core.nodes.Movie;

@RelationshipProperties
public class ActedIn {

    @RelationshipId
    private Long id;  
     
    @Property("roles")
    private String[] roles;

    @TargetNode
    private Movie movie;

    public ActedIn(Movie movie,String[] roles){
        this.movie = movie;
        this.roles = roles;
    }

    public String[] getRoles(){
        return roles;
    }

    public Movie getMovie(){
        return movie;
    }

}
