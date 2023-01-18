package com.ilvo.neo4jdemo.cli;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import com.ilvo.neo4jdemo.nodes.Movie;
import com.ilvo.neo4jdemo.nodes.Person;
import com.ilvo.neo4jdemo.repository.MovieRepository;
import com.ilvo.neo4jdemo.repository.PersonRepository;

@ShellComponent
public class Commands {

    private final static Logger log = LoggerFactory.getLogger(Commands.class);

    final private PersonRepository personRepository;
    final private MovieRepository movieRepository;
    
    public Commands(@Autowired PersonRepository personRepository,@Autowired MovieRepository movieRepository){
        this.personRepository = personRepository;
        this.movieRepository = movieRepository; 
    }

    @ShellMethod(value = "add a person node,you should input name then born year")
    public void addPerson(String name, Integer bornYear){

        Person person = new Person(name,bornYear);
        personRepository.save(person);

        person = personRepository.findByName(person.getName());
        log.info("you add the person :\n"+person.infomation());

    }

    @ShellMethod(value = "delete a person node,you should input name")
    public void deletePerson(String name){

        Person person = personRepository.findByName(name);
        log.info(person.infomation());
        
        personRepository.delete(person);
        log.info("you delete the person :\n"+person.infomation());

    }

    @ShellMethod(value = "add a movie node,you should input title, tagline then released year")
    public void addMovie(String title, String tagline, Integer releasedYear){

        Movie movie = new Movie(title,tagline,releasedYear);
        movieRepository.save(movie);

        movie = movieRepository.findByTitle(movie.getTitle());
        log.info("you add the movie :\n"+ movie.infomation());
    }

    @ShellMethod(value = "delete a movie node,you should input title")
    public void deleteMovie(String title){

        Movie movie = movieRepository.findByTitle(title);
        log.info(movie.infomation());

        movieRepository.delete(movie);
        log.info("you delete the movie :\n"+movie.infomation());

    }

    @ShellMethod(value = "add a ACTED_In relationship, you should input person's name , movie's title then the roles")
    public void actedIn(String name, String title, String[] roles){

        Person person = personRepository.findByName(name);
        Movie movie = movieRepository.findByTitle(title);

        person.actedIn(movie, roles);
        personRepository.save(person);
        log.info("you add an ACTED_IN relationship between "+person.getName()+" and "+movie.getTitle()+",roles is "+person.getActedInProperty(movie).getRoles());
        
    }

    @ShellMethod(value = "delete a ACTED_In relationship, you should input person's name , movie's title")
    public void deleteActedIn(String name, String title){

        Person person = personRepository.findByName(name);
        Movie movie = movieRepository.findByTitle(title);

        
        person.getActedIns().forEach(actedIn->{
            if(actedIn.getMovie().getTitle().equals(movie.getTitle())){

                log.info(actedIn.getMovie().getTitle());
                log.info(movie.infomation());
                log.info("ActedIn between person "+name+" and movie "+title+" now exists because not delete the relationship");
            }
             
            
        });
    
        if(person.deleteActedIn(movie)){
            person.getActedIns().forEach(actedIn->{
            if(actedIn.getMovie().getTitle().equals(movie.getTitle())){

                log.info(actedIn.getMovie().getTitle());
                log.info(movie.infomation());
                log.info("ActedIn between person "+name+" and movie "+title+" still exists");
                }
            });

            personRepository.save(person);   // personRepository.save(person) failed,it doesn't update which means doesn't execute the operation : delete the ACTED_IN relationship in Neo4j Database
            log.info("you delete an ACTED_IN relationship between "+person.getName()+" and "+movie.getTitle());
        }else{
            log.info("failed! you don't delete an ACTED_IN relationship between "+person.getName()+" and "+movie.getTitle());
        }

    }

    @ShellMethod(value = "add a REVIEWED relationship, you should input person's name , movie's title, summary and rating")
    public void reviewed(String name, String title, String summaty, Integer rating){

        Person person = personRepository.findByName(name);
        Movie movie = movieRepository.findByTitle(title);

        person.reviewed(movie, summaty,rating);
        personRepository.save(person);
        log.info("you add an REVIEWED relationship between "+person.getName()+" and "+movie.getTitle()+", is "+person.getReviewedProperties(movie));
    
    }

    @ShellMethod
    public void check(String name,String title){
        Person person = personRepository.findByName(name);
        Movie movie = movieRepository.findByTitle(title);
        
        person.getActedIns().forEach(actedIn->{
            if(actedIn.getMovie().getTitle().equals(movie.getTitle())){

                log.info(actedIn.getMovie().getTitle());
                log.info(movie.infomation());
                log.info("ActedIn between person "+name+" and movie "+title+" still exists");
            }
             
            
        });

    }


    
}
