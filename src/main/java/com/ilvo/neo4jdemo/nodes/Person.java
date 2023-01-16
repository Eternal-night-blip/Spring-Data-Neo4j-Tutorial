package com.ilvo.neo4jdemo.nodes;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import com.ilvo.neo4jdemo.dto.ActedInProperty;
import com.ilvo.neo4jdemo.dto.ReviewedProperties;
import com.ilvo.neo4jdemo.relationships.ActedIn;
import com.ilvo.neo4jdemo.relationships.Reviewed;


@Node("Person")
public class Person {

    @Id
    private String name;
    
    @Property("born")
    private Integer bornYear;

    @Relationship("FOLLOWS")  //默认是OUTGOING方向
    Set<Person> masters;

    @Relationship("ACTED_IN")
    Set<ActedIn> actedIns;

    @Relationship("REVIEWED")
    Set<Reviewed> revieweds;
    
    @Relationship("DIRECTED")    
    Set<Movie> directedMovies;
    
    @Relationship("PRODUCED")
    Set<Movie> producedMovies;

    @Relationship("WROTE")
    Set<Movie> writtenMovies;
    

    public Person(String name,Integer bornYear){
        this.name = name;
        this.bornYear = bornYear;
    }


    public String getName(){
        return name;
    }

    public void follows(Person master){
        if (masters == null){
            masters = new HashSet<>();
        }
        masters.add(master);
    }

    public void actedIn(Movie movie,String[] roles){
        if ( actedIns == null){
            actedIns = new HashSet<>();
        }
        ActedIn actedIn = new ActedIn(movie,roles);
        actedIns.add(actedIn);
    }
    
    public void reviewed(Movie movie,String summary, Integer rating){
        if ( revieweds == null){
            revieweds = new HashSet<>();
        }
        Reviewed reviewed = new Reviewed(movie, summary, rating);
        revieweds.add(reviewed);
    }

    public void directed(Movie movie){
        if ( directedMovies == null){
            directedMovies = new HashSet<>();
        }
        directedMovies.add(movie);
    }

    public void produced(Movie movie){
        if ( producedMovies == null){
            producedMovies = new HashSet<>();
        }
        producedMovies.add(movie);
    }

    public void wrote(Movie movie){
        if ( writtenMovies == null){
            writtenMovies = new HashSet<>();
        }
        writtenMovies.add(movie);
    }

    public ActedInProperty getActedInProperty(Movie movie){

        ActedInProperty result = null;
        
        for(ActedIn actedIn : actedIns){
            if(actedIn.getMovie().getTitle().equals(movie.getTitle())){
                result = new ActedInProperty(actedIn.getRoles());
                break;
            }

        }

        return result;
    }

    public ReviewedProperties getReviewedProperties(Movie movie){

        ReviewedProperties result = null;

        for(Reviewed reviewed : revieweds){

            if(reviewed.getMovie().getTitle().equals(movie.getTitle())){
                result = new ReviewedProperties(reviewed.getSummary(), reviewed.getRating());
                break;
            }
        }

        return result;
    }
    
    public String infomation(){
        return "name: "+ this.name + ", born: "+ this.bornYear;
    }
    
}
