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
        
        Movie movie_exist = movieRepository.findByTitle(title.get());
        if (movie_exist != null){
            throw new ExistException("the movie "+title+" has already exist");
        }
        
        final Movie new_movie = new Movie(title.get(),tagLine.get(),releasedYear.get());
        return movieRepository.save(new_movie);
        
    }

    @Override
    public boolean deleteMovie(Title title) {
        
        if (title == null) {
            throw new IllegalArgumentException("Null or empty title is not allowed");
        }

        Movie movie = movieRepository.findByTitle(title.get());
        
        if(movie == null){
            throw new EmptyException("the movie which whill be deleted could not be found");
        }

        movieRepository.delete(movie);
        return doesDeleteMovie(title);

    }

    public boolean doesDeleteMovie(Title title){
        
        boolean delete = true;
        boolean not_delete = false;
        
        Movie movie = movieRepository.findByTitle(title.get());
        if (movie != null){
            return not_delete;
        }
        return delete;
    }
    
    @Override
    public Movie getMovie(Title title){
        
        if( title == null ){
            throw new IllegalArgumentException("null title is not allowed");
        }

        Movie movie = movieRepository.findByTitle(title.get());

        if(movie == null){
            throw new EmptyException("movie could not be found");
        }

        return movie;
    }

    @Override
    public List<Person> getRelativePeople(Title title) {
        
        if (title == null) {
            throw new IllegalArgumentException("Null or empty title is not allowed");
        }

        Movie movie = movieRepository.findByTitle(title.get());
        if(movie == null){
            throw new EmptyException("movie don't exist");
        }
        
        return personRepository.findAllRelativePeopleOfMovie(movie.getTitle());
    }

    @Override
    public List<Person> getDirectors(Title title) {

        if (title == null) {
            throw new IllegalArgumentException("Null or empty title is not allowed");
        }

        Movie movie = movieRepository.findByTitle(title.get());
        if(movie == null){
            throw new EmptyException("movie don't exist");
        }
        
        return personRepository.findDirectorsOfMovie(movie.getTitle());
    }

    @Override
    public List<Person> getActors(Title title) {

        if (title == null) {
            throw new IllegalArgumentException("Null or empty title is not allowed");
        }

        Movie movie = movieRepository.findByTitle(title.get());
        if(movie == null){
            throw new EmptyException("movie don't exist");
        }
        
        return personRepository.findActorsOfMovie(movie.getTitle());
    }

    @Override
    public List<Person> getScreenWriters(Title title) {

        if (title == null) {
            throw new IllegalArgumentException("Null or empty title is not allowed");
        }

        Movie movie = movieRepository.findByTitle(title.get());
        if(movie == null){
            throw new EmptyException("movie don't exist");
        }

        return personRepository.findScreenWritersOfMovie(movie.getTitle());
    }

    @Override
    public List<Person> getProducers(Title title) {

        if (title == null) {
            throw new IllegalArgumentException("Null or empty title is not allowed");
        }

        Movie movie = movieRepository.findByTitle(title.get());
        if(movie == null){
            throw new EmptyException("movie don't exist");
        }

        return personRepository.findProducersOfMovie(movie.getTitle());
    }

    @Override
    public List<Person> getReviewers(Title title) {

        if (title == null) {
            throw new IllegalArgumentException("Null or empty title is not allowed");
        }
        
        Movie movie = movieRepository.findByTitle(title.get());
        if(movie == null){
            throw new EmptyException("movie don't exist");
        }

        return personRepository.findReviewersOfMovie(movie.getTitle());
    }
    
}
