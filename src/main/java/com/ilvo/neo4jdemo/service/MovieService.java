package com.ilvo.neo4jdemo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ilvo.neo4jdemo.nodes.Movie;
import com.ilvo.neo4jdemo.nodes.Person;
import com.ilvo.neo4jdemo.nodes.properties.ReleasedYear;
import com.ilvo.neo4jdemo.nodes.properties.TagLine;
import com.ilvo.neo4jdemo.nodes.properties.Title;
import com.ilvo.neo4jdemo.repository.MovieRepository;
import com.ilvo.neo4jdemo.repository.PersonRepository;
import com.ilvo.neo4jdemo.utils.EmptyException;
import com.ilvo.neo4jdemo.utils.EmptyUtil;
import com.ilvo.neo4jdemo.utils.ExistException;

@Service
public class MovieService implements MovieServiceInterface{

    private final MovieRepository movieRepository;
    private final PersonRepository personRepository;

    public MovieService(MovieRepository movieRepository, PersonRepository personRepository){
        this.movieRepository = movieRepository;
        this.personRepository = personRepository;
    }

    @Override
    public Movie addMovie(Title title, TagLine tagLine, ReleasedYear releasedYear) {

        if (title == null || tagLine == null || releasedYear == null) {
            throw new IllegalArgumentException("Null or empty title/tagline/released year is not allowed");
        }
        
        Movie movie_exist = movieRepository.findByTitle(title.getTitle());
        if (movie_exist != null){
            throw new ExistException("the movie "+title+" has already exist");
        }
        
        final Movie new_movie = new Movie(title.getTitle(),tagLine.getTagLine(),releasedYear.getReleasedYear());
        return movieRepository.save(new_movie);
        
    }

    @Override
    public boolean deleteMovie(Title title) {
        
        if (title == null) {
            throw new IllegalArgumentException("Null or empty title is not allowed");
        }

        Movie movie = movieRepository.findByTitle(title.getTitle());
        
        if(movie == null){
            throw new NullPointerException("the movie which whill be deleted could not be found");
        }

        movieRepository.delete(movie);
        return doesDeleteMovie(title);

    }

    public boolean doesDeleteMovie(Title title){

        Movie movie = movieRepository.findByTitle(title.getTitle());
        if (movie == null){
            return false;
        }
        return true;
    }

    @Override
    public List<Person> getRelativePeople(Title title) {
        
        if (title == null) {
            throw new IllegalArgumentException("Null or empty title is not allowed");
        }
        
        List<Person> people =personRepository.findAllRelativePeopleOfMovie(title.getTitle());
        
        if(EmptyUtil.isEmpty(people)){
            throw new EmptyException("could not find any relative people of the movie "+title);
        }
        return people;
    }

    @Override
    public List<Person> getDirectors(Title title) {

        if (title == null) {
            throw new IllegalArgumentException("Null or empty title is not allowed");
        }
        
        List<Person> directors = personRepository.findDirectorsOfMovie(title.getTitle());
        
        if(EmptyUtil.isEmpty(directors)){
            throw new EmptyException("could not find any directors of the movie "+title);
        }
        return directors;
    }

    @Override
    public List<Person> getActors(Title title) {

        if (title == null) {
            throw new IllegalArgumentException("Null or empty title is not allowed");
        }
        
        List<Person> actors = personRepository.findActorsOfMovie(title.getTitle());
        
        if(EmptyUtil.isEmpty(actors)){
            throw new EmptyException("could not find any actors of the movie "+title);
        }
        return actors;
    }

    @Override
    public List<Person> getScreenWriters(Title title) {

        if (title == null) {
            throw new IllegalArgumentException("Null or empty title is not allowed");
        }
        
        List<Person> screenWriters = personRepository.findScreenWritersOfMovie(title.getTitle());
        
        if(EmptyUtil.isEmpty(screenWriters)){
            throw new EmptyException("could not find any screen writers of the movie "+title);
        }
        return screenWriters;
    }

    @Override
    public List<Person> getProducers(Title title) {

        if (title == null) {
            throw new IllegalArgumentException("Null or empty title is not allowed");
        }
        
        List<Person> producers = personRepository.findProducersOfMovie(title.getTitle());
        
        if(EmptyUtil.isEmpty(producers)){
            throw new EmptyException("could not find any producers of the movie "+title);
        }
        return producers;
    }

    @Override
    public List<Person> getReviewers(Title title) {

        if (title == null) {
            throw new IllegalArgumentException("Null or empty title is not allowed");
        }

        List<Person> reviewers = personRepository.findReviewersOfMovie(title.getTitle());
        
        if(EmptyUtil.isEmpty(reviewers)){
            throw new EmptyException("could not find any reviewers of the movie "+title);
        }
        return reviewers;
    }
    
}
