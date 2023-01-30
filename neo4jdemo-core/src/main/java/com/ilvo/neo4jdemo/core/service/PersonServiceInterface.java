package com.ilvo.neo4jdemo.core.service;

import java.util.List;

import com.ilvo.neo4jdemo.core.dto.ActedInProperty;
import com.ilvo.neo4jdemo.core.dto.ReviewedProperties;
import com.ilvo.neo4jdemo.core.nodes.Movie;
import com.ilvo.neo4jdemo.core.nodes.Person;
import com.ilvo.neo4jdemo.core.nodes.properties.BornYear;
import com.ilvo.neo4jdemo.core.nodes.properties.Name;
import com.ilvo.neo4jdemo.core.nodes.properties.Title;
import com.ilvo.neo4jdemo.core.relationships.ActedIn;
import com.ilvo.neo4jdemo.core.relationships.Reviewed;
import com.ilvo.neo4jdemo.core.relationships.properties.Rating;
import com.ilvo.neo4jdemo.core.relationships.properties.Roles;
import com.ilvo.neo4jdemo.core.relationships.properties.Summary;

public interface PersonServiceInterface {

    public Person addPerson(Name name, BornYear bornYear);

    public boolean deletePerson(Name name);
    
    public Person getPerson(Name name);


    public Person follows(Name follower, Name master);

    public boolean deleteFollows(Name follower, Name master);

    public List<Person> getMasters(Name follower);

    public List<Person> getFollowers(Name master);

    

    public ActedIn actedIn(Name person, Title movie, Roles roles);

    public boolean deleteActedIn(Name person, Title movie);

    public List<Movie> getActedInMovies(Name person);



    public Reviewed reviewed(Name person, Title movie, Summary summary, Rating rating);
    
    public boolean deleteReviewed(Name person, Title movie);
    
    public List<Movie> getReviewedMovies(Name person);

    

    public Movie directed(Name person, Title movie);

    public boolean deleteDirected(Name person, Title movie);

    public List<Movie> getDirectedMovies(Name person);

    

    public Movie produced(Name person, Title movie);

    public boolean deleteProduced(Name person, Title movie);

    public List<Movie> getProducedMovies(Name person);


    
    public Movie wrote(Name person, Title movie);

    public boolean deleteWrote(Name person, Title movie);

    public List<Movie> getWrittenMovies(Name person);
    
    

    public List<Movie> getRelativeMovies(Name person);

    public boolean deleteRelativeRelationshipsToAllMovies(Name person);



    public ActedInProperty getActedInProperty(Name person, Title movie);

    public ReviewedProperties getReviewedProperties(Name person, Title movie);
    
}
