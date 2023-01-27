package com.ilvo.neo4jdemo.service;

import java.util.List;

import com.ilvo.neo4jdemo.nodes.Movie;
import com.ilvo.neo4jdemo.nodes.Person;
import com.ilvo.neo4jdemo.nodes.properties.ReleasedYear;
import com.ilvo.neo4jdemo.nodes.properties.TagLine;
import com.ilvo.neo4jdemo.nodes.properties.Title;

public interface MovieServiceInterface {

    public Movie addMovie(Title title, TagLine tagLine, ReleasedYear releasedYear);

    public boolean deleteMovie(Title title);

    public Movie getMovie(Title title);    
    

    public List<Person> getRelativePeople(Title title);

    public List<Person> getDirectors(Title title);

    public List<Person> getActors(Title title);

    public List<Person> getScreenWriters(Title title);

    public List<Person> getProducers(Title title);

    public List<Person> getReviewers(Title title);
    
}
