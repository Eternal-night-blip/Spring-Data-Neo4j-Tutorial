package com.ilvo.neo4jdemo.cli;


import java.util.List;

import org.jline.terminal.Terminal;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.transaction.annotation.Transactional;

import com.ilvo.neo4jdemo.core.dto.ActedInProperty;
import com.ilvo.neo4jdemo.core.dto.ReviewedProperties;
import com.ilvo.neo4jdemo.core.nodes.Movie;
import com.ilvo.neo4jdemo.core.nodes.Person;
import com.ilvo.neo4jdemo.core.nodes.properties.BornYear;
import com.ilvo.neo4jdemo.core.nodes.properties.Name;
import com.ilvo.neo4jdemo.core.nodes.properties.ReleasedYear;
import com.ilvo.neo4jdemo.core.nodes.properties.TagLine;
import com.ilvo.neo4jdemo.core.nodes.properties.Title;
import com.ilvo.neo4jdemo.core.relationships.properties.Rating;
import com.ilvo.neo4jdemo.core.relationships.properties.Roles;
import com.ilvo.neo4jdemo.core.relationships.properties.Summary;
import com.ilvo.neo4jdemo.core.service.PersonService;
import com.ilvo.neo4jdemo.core.service.MovieService;

@ShellComponent
public class Commands {
    
    private final Terminal terminal;
    private final PersonService personService;
    private final MovieService movieService;
    
    public Commands(Terminal terminal,PersonService personService, MovieService MovieService){
        this.terminal = terminal;
        this.personService = personService;
        this.movieService = MovieService; 
    }
    
    @Transactional
    @ShellMethod(value = "add a person node,you should input name then born year",group = "Node Basic Commands")
    public void addPerson(String name, Integer bornYear){
        
        personService.addPerson(Name.of(name), BornYear.of(bornYear));
        terminal.writer().println("you add the person :"+name);
    }
    
    @Transactional
    @ShellMethod(value = "delete a person node,you should input name",group = "Node Basic Commands")
    public void deletePerson(String name){

        personService.deletePerson(Name.of(name));
        terminal.writer().println("you delete the person :"+name);
    }

    @Transactional
    @ShellMethod(value = "add a movie node,you should input title, tagline then released year",group = "Node Basic Commands")
    public void addMovie(String title, String tagline, Integer releasedYear){

        movieService.addMovie(Title.of(title),TagLine.of(tagline),ReleasedYear.of(releasedYear));
        terminal.writer().println("you add the movie :"+ title);

    }

    @Transactional
    @ShellMethod(value = "delete a movie node,you should input title",group = "Node Basic Commands")
    public void deleteMovie(String title){

        movieService.deleteMovie(Title.of(title));
        terminal.writer().println("you delete the movie :"+title);

    }

    @Transactional
    @ShellMethod(value = "add a ACTED_In relationship, you should input person's name, movie's title then the roles",group = "Relationship Basic Commands")
    public void actedIn(String name, String title, String[] roles){

        personService.actedIn(Name.of(name),Title.of(title),Roles.of(roles));
        terminal.writer().println("you add an ACTED_IN relationship between "+name+" and "+title);
        
    }

    @Transactional
    @ShellMethod(value = "delete a ACTED_In relationship, you should input person's name, movie's title",group = "Relationship Basic Commands")
    public void deleteActedIn(String name, String title){
        
        personService.deleteActedIn(Name.of(name),Title.of(title));
        terminal.writer().println("you delete an ACTED_IN relationship between "+name+" and "+title);
        
    }

    @Transactional
    @ShellMethod(value = "add a REVIEWED relationship, you should input person's name, movie's title, summary and rating",group = "Relationship Basic Commands")
    public void reviewed(String name, String title, String summary, Integer rating){

        personService.reviewed(Name.of(name),Title.of(title),Summary.of(summary),Rating.of(rating));
        terminal.writer().println("you add an REVIEWED relationship between "+name+" and "+title);
    
    }
    
    @Transactional
    @ShellMethod(value = "delete a REVIEWED relationship, you should input person's name, movie's title",group = "Relationship Basic Commands")
    public void deleteReviewed(String name, String title){
       
        personService.deleteReviewed(Name.of(name),Title.of(title));
        terminal.writer().println("you delete an REVIEWED relationship between "+name+" and "+title);

    }

    @Transactional
    @ShellMethod(value = "add a FOLLOWS relationship, you should input follower's name, master's name",group = "Relationship Basic Commands")
    public void follows(String name_follower, String name_master){

        personService.follows(Name.of(name_follower),Name.of(name_master));
        terminal.writer().println("you add an FOLLOWS relationship between "+name_follower+" and "+name_master);

    }

    @Transactional
    @ShellMethod(value = "delete a FOLLOWS relationship, you should input follower's name, master's name",group = "Relationship Basic Commands")
    public void deleteFollows(String name_follower, String name_master){
        
        personService.deleteFollows(Name.of(name_follower),Name.of(name_master));
        terminal.writer().println("you delete an FOLLOWS relationship between "+name_follower+" and "+name_master);

    }

    @Transactional
    @ShellMethod(value = "add a DIRECTED relationship, you should input person's name, movie's title",group = "Relationship Basic Commands")
    public void directed(String name, String title){

        personService.directed(Name.of(name),Title.of(title));
        terminal.writer().println("you add an DIRECTED relationship between "+name+" and "+title);

    }

    @Transactional
    @ShellMethod(value = "delete a DIRECTED relationship, you should input person's name, movie's title",group = "Relationship Basic Commands")
    public void deleteDirected(String name, String title){
        
        personService.deleteDirected(Name.of(name),Title.of(title));
        terminal.writer().println("you delete an DIRECTED relationship between "+name+" and "+title);

    }

    @Transactional
    @ShellMethod(value = "add a PRODUCED relationship, you should input person's name , movie's title",group = "Relationship Basic Commands")
    public void produced(String name, String title){

        personService.produced(Name.of(name),Title.of(title));
        terminal.writer().println("you add an PRODUCED relationship between "+name+" and "+title);

    }

    @Transactional
    @ShellMethod(value = "delete a PRODUCED relationship, you should input person's name , movie's title",group = "Relationship Basic Commands")
    public void deleteProduced(String name, String title){
         
        personService.deleteProduced(Name.of(name),Title.of(title));
        terminal.writer().println("you delete an PRODUCED relationship between "+name+" and "+title);

    }

    @Transactional
    @ShellMethod(value = "add a WROTE relationship, you should input person's name , movie's title",group = "Relationship Basic Commands")
    public void wrote(String name, String title){

        personService.wrote(Name.of(name),Title.of(title));
        terminal.writer().println("you add an WROTE relationship between "+name+" and "+ title);

    }

    @Transactional
    @ShellMethod(value = "delete a WROTE relationship, you should input person's name, movie's title",group = "Relationship Basic Commands")
    public void deleteWrote(String name, String title){
        
        personService.deleteWrote(Name.of(name),Title.of(title));
        terminal.writer().println("you delete an WROTE relationship between "+name+" and "+title);

    }

    @ShellMethod(value = "query property:roles of ACTED_IN, you should input person's name, movie's title",group = "Relationship Basic Commands")
    public void queryPropertiesActedIn(String name, String title){

        ActedInProperty actedInProperty =  personService.getActedInProperty(Name.of(name),Title.of(title));
        terminal.writer().println("the roles of ActedIn relationship between "+name+" and "+title+" : ");
			String[] roles = actedInProperty.getRoles();
			int length = roles.length;
			for(int i=0;i< length;i++){
				terminal.writer().print(roles[i]+" ");
			}
            terminal.writer().println();

    }

    @ShellMethod(value = "query property:summary,rating of REVIEWED, you should input person's name, movie's title",group = "Relationship Basic Commands")
    public void queryPropertiesReviewed(String name, String title){

        ReviewedProperties reviewedProperties =  personService.getReviewedProperties(Name.of(name),Title.of(title));
        terminal.writer().println("the summary of Reviewed relationship between "+name+" and "+title+" :");
		terminal.writer().println(reviewedProperties.getSummary());
		terminal.writer().println("the rating of Reviewed relationship between "+name+" and "+title+" :");
		terminal.writer().println(reviewedProperties.getRating().toString());

    }

    @ShellMethod(value = "query movies which person acted in, you should input person's name",group = "Advanced Query Commands")
    public void queryActedInMovies(String name){

		List<Movie> actedInMovies =  personService.getActedInMovies(Name.of(name));
		terminal.writer().println("Movies acted by "+name+" :");
		actedInMovies.forEach(movie->terminal.writer().println(movie));

    }

    @ShellMethod(value = "query movies which person reviewed, you should input person's name",group = "Advanced Query Commands")
    public void queryReviewedMovies(String name){

		List<Movie> reviewedMovies =  personService.getReviewedMovies(Name.of(name));
		terminal.writer().println("Movies reviewed by "+name+" :");
		reviewedMovies.forEach(movie->terminal.writer().println(movie));

    }

    @ShellMethod(value = "query masters of person, you should input person's name",group = "Advanced Query Commands")
    public void queryMasters(String name){

		List<Person> masters =  personService.getMasters(Name.of(name));
		terminal.writer().println("Masters followed by "+name+" :");
		masters.forEach(person->terminal.writer().println(person));

    }

    @ShellMethod(value = "query followers of person, you should input person's name",group = "Advanced Query Commands")
    public void queryFollowers(String name){

        List<Person> followers =  personService.getFollowers(Name.of(name));
		terminal.writer().println("Followers of "+name+" :");
		followers.forEach(person->terminal.writer().println(person));

    }

    @ShellMethod(value = "query movies which person directed, you should input person's name",group = "Advanced Query Commands")
    public void queryDirectedMovies(String name){

		List<Movie> directedMovies =  personService.getDirectedMovies(Name.of(name));
		terminal.writer().println("Movies directed by "+name+" :");
		directedMovies.forEach(movie->terminal.writer().println(movie));

    }

    @ShellMethod(value = "query movies which person produced, you should input person's name",group = "Advanced Query Commands")
    public void queryProducedMovies(String name){

		List<Movie> producedMovies =  personService.getProducedMovies(Name.of(name));
		terminal.writer().println("Movies produced by "+name+" :");
		producedMovies.forEach(movie->terminal.writer().println(movie));

    }

    @ShellMethod(value = "query movies which person wrote, you should input person's name",group = "Advanced Query Commands")
    public void queryWrittenMovies(String name){

		List<Movie> writtenMovies =  personService.getWrittenMovies(Name.of(name));
		terminal.writer().println("Movies written by "+name+" :");
		writtenMovies.forEach(movie->terminal.writer().println(movie));

    }

    @ShellMethod(value = "query all relative people of movie, you should input movie's title",key = "query-relative-people-movie",group = "Advanced Query Commands")
    public void queryRelativePeopleOfMovie(String title){

        List<Person> allRelativePeople = movieService.getRelativePeople(Title.of(title));
		terminal.writer().println("All relative people of the movie The Greeb Mile :");
		allRelativePeople.forEach(person->terminal.writer().println(person));
        
    }

}
