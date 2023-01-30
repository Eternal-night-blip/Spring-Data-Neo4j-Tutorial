package com.ilvo.neo4jdemo.core.service;

import java.util.List;

import org.springframework.stereotype.Service;

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
import com.ilvo.neo4jdemo.core.repository.MovieRepository;
import com.ilvo.neo4jdemo.core.repository.PersonRepository;
import com.ilvo.neo4jdemo.core.utils.EmptyException;
import com.ilvo.neo4jdemo.core.utils.EmptyUtil;
import com.ilvo.neo4jdemo.core.utils.ExistException;


@Service
public class PersonService implements PersonServiceInterface{
    
    private PersonRepository personRepository;
    private MovieRepository movieRepository;

    public PersonService(PersonRepository personRepository,MovieRepository movieRepository){
        this.personRepository = personRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public Person addPerson(Name name, BornYear bornYear) {
        
        if(name == null || bornYear == null){
            throw new IllegalArgumentException("null or empty name/bornYear is not allowed");
        }
        
        Person person_exist = personRepository.findByName(name.get());
        if(person_exist != null ){
            throw new ExistException("the person "+name+" has already exist");
        }
        
        final Person person_new = new Person(name.get(),bornYear.get());
        return personRepository.save(person_new);
    
    }

    @Override
    public boolean deletePerson(Name name) {
        
        if (name == null) {
            throw new IllegalArgumentException("Null or empty name is not allowed");
        }

        Person person = personRepository.findByName(name.get());
        if(person == null){
            throw new EmptyException("the person which whill be deleted could not exist");
        }

        personRepository.delete(person);
        return doesDeletePerson(name);
        
    }

    public boolean doesDeletePerson(Name name){
        
        boolean delete = true;
        boolean not_delete = false;
        
        Person person = personRepository.findByName(name.get());
        if (person != null){
            return not_delete;
        }
        return delete;
        
    }
    
    @Override
    public Person getPerson(Name name){
        
        if (name == null){
            throw new IllegalArgumentException("null name is not allowed");
        }

        Person person = personRepository.findByName(name.get());
        if(person == null){
            throw new EmptyException("person could not exist");
        }
        
        return person;

    }

    @Override
    public Person follows(Name follower_name, Name master_name) {
        
        if(follower_name == null || master_name == null){
            throw new IllegalArgumentException("null follower or master is not allowed");
        }

        Person follower = personRepository.findByName(follower_name.get());
        Person master = personRepository.findByName(master_name.get());
        
        if(follower == null || master == null){
            throw new EmptyException("follower or master could not exist");
        }
        
        master = follower.follows(master);
        personRepository.save(follower);
        
        return master;
    }

    @Override
    public boolean deleteFollows(Name follower_name, Name master_name) {
        
        if(follower_name == null || master_name == null){
            throw new IllegalArgumentException("null follower or master is not allowed");
        }
        
        Person follower = personRepository.findByName(follower_name.get());
        Person master = personRepository.findByName(master_name.get());

        if(follower == null || master == null){
            throw new EmptyException("follower or master could not exist");
        }

        return follower.deleteFollows(master);

    }

    @Override
    public List<Person> getMasters(Name follower_name) {
        
        if(follower_name == null){
            throw new IllegalArgumentException("null follower is not allowed");
        }
        
        Person follower = personRepository.findByName(follower_name.get());
        if(follower == null){
            throw new EmptyException("follower could not exist");
        }

        return personRepository.findMasters(follower.getName());
    }

    @Override
    public List<Person> getFollowers(Name master_name) {
        
        if(master_name == null){
            throw new IllegalArgumentException("null master is not allowed");
        }
        
        Person master = personRepository.findByName(master_name.get());
        if(master == null){
            throw new EmptyException("master could not exist");
        }

        return personRepository.findMasters(master.getName());
    }

    @Override
    public ActedIn actedIn(Name person_name, Title title, Roles roles) {
        
        if(person_name == null || title == null || roles == null){
            throw new IllegalArgumentException("null person/movie/roles is not allowed");
        }

        Person person = personRepository.findByName(person_name.get());
        Movie movie = movieRepository.findByTitle(title.get());

        if(person == null || movie == null){
            throw new EmptyException("person or movie could not exist");
        }
        
        return person.actedIn(movie, roles.get());

    }

    @Override
    public boolean deleteActedIn(Name name, Title  title) {
        
        if(name == null || title == null){
            throw new IllegalArgumentException("null person or movie is not allowed");
        }
        
        Person person = personRepository.findByName(name.get());
        Movie movie = movieRepository.findByTitle(title.get());

        if(person == null || movie == null){
            throw new EmptyException("person or movie could not exist");
        }

        return person.deleteActedIn(movie);

    }

    @Override
    public List<Movie> getActedInMovies(Name name) {
        
        if(name == null){
            throw new IllegalArgumentException("null person is not allowed");
        }
        
        Person person = personRepository.findByName(name.get());
        if(person == null){
            throw new EmptyException("person don't exist");
        }

        return movieRepository.findActedInMovies(name.get());
    }

    @Override
    public Reviewed reviewed(Name name, Title title, Summary summary, Rating rating) {
        
        if(name == null || title == null || summary == null || rating == null){
            throw new IllegalArgumentException("null person/movie/summary/rating is not allowed");
        }

        Person person = personRepository.findByName(name.get());
        Movie movie = movieRepository.findByTitle(title.get());

        if(person == null || movie == null){
            throw new EmptyException("person or movie could not exist");
        }
        
        return person.reviewed(movie, summary.get(),rating.get());

    }

    @Override
    public boolean deleteReviewed(Name name, Title title) {

        if(name == null || title == null){
            throw new IllegalArgumentException("null person or movie is not allowed");
        }
        
        Person person = personRepository.findByName(name.get());
        Movie movie = movieRepository.findByTitle(title.get());

        if(person == null || movie == null){
            throw new EmptyException("person or movie could not exist");
        }

        return person.deleteReviewed(movie);

    }

    @Override
    public List<Movie> getReviewedMovies(Name name) {

        if(name == null){
            throw new IllegalArgumentException("null person is not allowed");
        }
        
        Person person = personRepository.findByName(name.get());
        if(person == null){
            throw new EmptyException("person don't exist");
        }

        return movieRepository.findReviewedMovies(name.get());
    }

    @Override
    public Movie directed(Name name, Title title) {
        
        if(name == null || title == null){
            throw new IllegalArgumentException("null person or movie is not allowed");
        }

        Person person = personRepository.findByName(name.get());
        Movie movie = movieRepository.findByTitle(title.get());

        if(person == null || movie == null){
            throw new EmptyException("person or movie could not exist");
        }
        
        return person.directed(movie);

    }

    @Override
    public boolean deleteDirected(Name name, Title title) {
        
        if(name == null || title == null){
            throw new IllegalArgumentException("null person or movie is not allowed");
        }
        
        Person person = personRepository.findByName(name.get());
        Movie movie = movieRepository.findByTitle(title.get());

        if(person == null || movie == null){
            throw new EmptyException("person or movie could not exist");
        }

        return person.deleteDirected(movie);

    }

    @Override
    public List<Movie> getDirectedMovies(Name name) {
        
        if(name == null){
            throw new IllegalArgumentException("null person is not allowed");
        }
        
        Person person = personRepository.findByName(name.get());
        if(person == null){
            throw new EmptyException("person don't exist");
        }

        return movieRepository.findDirectedMovies(name.get());
    }

    @Override
    public Movie produced(Name name, Title title) {
        
        if(name == null || title == null){
            throw new IllegalArgumentException("null person or movie is not allowed");
        }

        Person person = personRepository.findByName(name.get());
        Movie movie = movieRepository.findByTitle(title.get());

        if(person == null || movie == null){
            throw new EmptyException("person or movie could not exist");
        }
        
        return person.produced(movie);

    }

    @Override
    public boolean deleteProduced(Name name, Title title) {
        
        if(name == null || title == null){
            throw new IllegalArgumentException("null person or movie is not allowed");
        }
        
        Person person = personRepository.findByName(name.get());
        Movie movie = movieRepository.findByTitle(title.get());

        if(person == null || movie == null){
            throw new EmptyException("person or movie could not exist");
        }

        return person.deleteProduced(movie);

    }

    @Override
    public List<Movie> getProducedMovies(Name name) {
        
        if(name == null){
            throw new IllegalArgumentException("null person is not allowed");
        }
        
        Person person = personRepository.findByName(name.get());
        if(person == null){
            throw new EmptyException("person don't exist");
        }

        return movieRepository.findProducedMovies(name.get());

    }

    @Override
    public Movie wrote(Name name, Title title) {
        
        if(name == null || title == null){
            throw new IllegalArgumentException("null person or movie is not allowed");
        }

        Person person = personRepository.findByName(name.get());
        Movie movie = movieRepository.findByTitle(title.get());

        if(person == null || movie == null){
            throw new EmptyException("person or movie could not exist");
        }
        
        return person.wrote(movie);
    }

    @Override
    public boolean deleteWrote(Name name, Title title) {
        
        if(name == null || title == null){
            throw new IllegalArgumentException("null person or movie is not allowed");
        }
        
        Person person = personRepository.findByName(name.get());
        Movie movie = movieRepository.findByTitle(title.get());

        if(person == null || movie == null){
            throw new EmptyException("person or movie could not exist");
        }

        return person.deleteWrote(movie);

    }

    @Override
    public List<Movie> getWrittenMovies(Name name) {
        
        if(name == null){
            throw new IllegalArgumentException("null person is not allowed");
        }
        
        Person person = personRepository.findByName(name.get());
        if(person == null){
            throw new EmptyException("person don't exist");
        }

        return movieRepository.findWrittenMovies(name.get());
    }

    @Override
    public List<Movie> getRelativeMovies(Name name) {
        
        if(name == null){
            throw new IllegalArgumentException("null person is not allowed");
        }
        
        Person person = personRepository.findByName(name.get());
        if(person == null){
            throw new EmptyException("person don't exist");
        }

        return movieRepository.findAllRelativeMovies(name.get());
        
    }

    @Override
    public boolean deleteRelativeRelationshipsToAllMovies(Name name) {
        
        if(name == null){
            throw new IllegalArgumentException("null person is not allowed");
        }
        
        Person person = personRepository.findByName(name.get());
        if(person == null){
            throw new EmptyException("person could not exist");
        }
        
        movieRepository.deleteAllRelationshipsToAllMovies(person.getName());
        return doesDeleteRelativeRelationshipsToAllMovies(name);
    }

    public boolean doesDeleteRelativeRelationshipsToAllMovies(Name name){

        boolean delete = true;
        boolean not_delete = false;
        
        List<Movie> relativeMovies =  getRelativeMovies(name);
        if (EmptyUtil.isEmpty(relativeMovies)){
            return not_delete;
        }
        return delete;

    }

    @Override
    public ActedInProperty getActedInProperty(Name name, Title title) {
        
        if(name == null || title == null){
            throw new IllegalArgumentException("null person or movie is not allowed");
        }

        Person person = personRepository.findByName(name.get());
        Movie movie = movieRepository.findByTitle(title.get());
        if(person == null || movie == null){
            throw new EmptyException("person or movie don't exist");
        }

        return person.getActedInProperty(movie);
    }

    @Override
    public ReviewedProperties getReviewedProperties(Name name, Title title) {
        
        if(name == null || title == null){
            throw new IllegalArgumentException("null person or movie is not allowed");
        }

        Person person = personRepository.findByName(name.get());
        Movie movie = movieRepository.findByTitle(title.get());
        if(person == null || movie == null){
            throw new EmptyException("person or movie don't exist");
        }

        return person.getReviewedProperties(movie);
    }
    
}
