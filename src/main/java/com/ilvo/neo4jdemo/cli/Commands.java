package com.ilvo.neo4jdemo.cli;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import com.ilvo.neo4jdemo.dto.ActedInProperty;
import com.ilvo.neo4jdemo.dto.ReviewedProperties;
import com.ilvo.neo4jdemo.nodes.Movie;
import com.ilvo.neo4jdemo.nodes.Person;
import com.ilvo.neo4jdemo.repository.MovieRepository;
import com.ilvo.neo4jdemo.repository.PersonRepository;

@ShellComponent
public class Commands {

    final private PersonRepository personRepository;
    final private MovieRepository movieRepository;
    
    public Commands(@Autowired PersonRepository personRepository,@Autowired MovieRepository movieRepository){
        this.personRepository = personRepository;
        this.movieRepository = movieRepository; 
    }

    @ShellMethod(value = "add a person node,you should input name then born year",group = "Node Basic Commands")
    public void addPerson(String name, Integer bornYear){

        Person person = new Person(name,bornYear);
        personRepository.save(person);

        person = personRepository.findByName(person.getName());
        System.out.println("you add the person :\n"+person.infomation());

    }

    @ShellMethod(value = "delete a person node,you should input name",group = "Node Basic Commands")
    public void deletePerson(String name){

        Person person = personRepository.findByName(name);
        
        personRepository.delete(person);
        System.out.println("you delete the person :\n"+person.infomation());

    }

    @ShellMethod(value = "add a movie node,you should input title, tagline then released year",group = "Node Basic Commands")
    public void addMovie(String title, String tagline, Integer releasedYear){

        Movie movie = new Movie(title,tagline,releasedYear);
        movieRepository.save(movie);

        movie = movieRepository.findByTitle(movie.getTitle());
        System.out.println("you add the movie :\n"+ movie.infomation());

    }

    @ShellMethod(value = "delete a movie node,you should input title",group = "Node Basic Commands")
    public void deleteMovie(String title){

        Movie movie = movieRepository.findByTitle(title);

        movieRepository.delete(movie);
        System.out.println("you delete the movie :\n"+movie.infomation());

    }

    @ShellMethod(value = "add a ACTED_In relationship, you should input person's name, movie's title then the roles",group = "Relationship Basic Commands")
    public void actedIn(String name, String title, String[] roles){

        Person person = personRepository.findByName(name);
        Movie movie = movieRepository.findByTitle(title);

        person.actedIn(movie, roles);
        personRepository.save(person);

        System.out.println("you add an ACTED_IN relationship between "+person.getName()+" and "+movie.getTitle()+",roles is : ");
        roles = person.getActedInProperty(movie).getRoles();
        int length = roles.length;
			for(int i=0;i< length;i++){
				System.out.println(roles[i]);
			}
        
    }

    @ShellMethod(value = "delete a ACTED_In relationship, you should input person's name, movie's title",group = "Relationship Basic Commands")
    public void deleteActedIn(String name, String title){
        
        personRepository.deleteActedInRelationship(name, title);
        System.out.println("you delete an ACTED_IN relationship between "+name+" and "+title);
        
    }

    @ShellMethod(value = "add a REVIEWED relationship, you should input person's name, movie's title, summary and rating",group = "Relationship Basic Commands")
    public void reviewed(String name, String title, String summaty, Integer rating){

        Person person = personRepository.findByName(name);
        Movie movie = movieRepository.findByTitle(title);

        person.reviewed(movie, summaty,rating);
        personRepository.save(person);
        System.out.println("you add an REVIEWED relationship between "+person.getName()+" and "+movie.getTitle()+", is "+person.getReviewedProperties(movie));
    
    }

    @ShellMethod(value = "delete a REVIEWED relationship, you should input person's name, movie's title",group = "Relationship Basic Commands")
    public void deleteReviewed(String name, String title){

        personRepository.deleteReviewedRelationship(name,title);
        System.out.println("you delete an REVIEWED relationship between "+name+" and "+title);

    }

    @ShellMethod(value = "add a FOLLOWS relationship, you should input follower's name, master's name",group = "Relationship Basic Commands")
    public void follows(String name_follower, String name_master){

        Person follower = personRepository.findByName(name_follower);
        Person master = personRepository.findByName(name_master);

        follower.follows(master);
        personRepository.save(follower);
        System.out.println("you add an FOLLOWS relationship between "+follower.getName()+" and "+master.getName());

    }

    @ShellMethod(value = "delete a FOLLOWS relationship, you should input follower's name, master's name",group = "Relationship Basic Commands")
    public void deleteFollows(String name_follower, String name_master){

        personRepository.deleteFollowsRelationship(name_follower,name_master);
        System.out.println("you delete an FOLLOWS relationship between "+name_follower+" and "+name_master);

    }

    @ShellMethod(value = "add a DIRECTED relationship, you should input person's name, movie's title",group = "Relationship Basic Commands")
    public void directed(String name, String title){

        Person person = personRepository.findByName(name);
        Movie movie = movieRepository.findByTitle(title);

        person.directed(movie);
        personRepository.save(person);
        System.out.println("you add an DIRECTED relationship between "+person.getName()+" and "+movie.getTitle()+", is "+person.getReviewedProperties(movie));

    }

    @ShellMethod(value = "delete a DIRECTED relationship, you should input person's name, movie's title",group = "Relationship Basic Commands")
    public void deleteDirected(String name, String title){

        personRepository.deleteDirectedRelationship(name,title);
        System.out.println("you delete an DIRECTED relationship between "+name+" and "+title);

    }

    @ShellMethod(value = "add a PRODUCED relationship, you should input person's name , movie's title",group = "Relationship Basic Commands")
    public void produced(String name, String title){

        Person person = personRepository.findByName(name);
        Movie movie = movieRepository.findByTitle(title);

        person.produced(movie);
        personRepository.save(person);
        System.out.println("you add an PRODUCED relationship between "+person.getName()+" and "+movie.getTitle()+", is "+person.getReviewedProperties(movie));

    }

    @ShellMethod(value = "delete a PRODUCED relationship, you should input person's name , movie's title",group = "Relationship Basic Commands")
    public void deleteProduced(String name, String title){

        personRepository.deleteProducedRelationship(name,title);
        System.out.println("you delete an PRODUCED relationship between "+name+" and "+title);

    }

    @ShellMethod(value = "add a WROTE relationship, you should input person's name , movie's title",group = "Relationship Basic Commands")
    public void wrote(String name, String title){

        Person person = personRepository.findByName(name);
        Movie movie = movieRepository.findByTitle(title);

        person.wrote(movie);
        personRepository.save(person);
        System.out.println("you add an WROTE relationship between "+person.getName()+" and "+movie.getTitle()+", is "+person.getReviewedProperties(movie));

    }

    @ShellMethod(value = "delete a WROTE relationship, you should input person's name, movie's title",group = "Relationship Basic Commands")
    public void deleteWrote(String name, String title){

        personRepository.deleteWroteRelationship(name,title);
        System.out.println("you delete an WROTE relationship between "+name+" and "+title);

    }

    @ShellMethod(value = "query property:roles of ACTED_IN, you should input person's name, movie's title",group = "Relationship Basic Commands")
    public void queryPropertiesActedIn(String name, String title){

        Person person = personRepository.findByName(name);
        Movie movie = movieRepository.findByTitle(title);

        ActedInProperty actedInProperty = person.getActedInProperty(movie);
        System.out.println("the roles of ActedIn relationship between "+name+" and "+title+" :");
			String[] roles = actedInProperty.getRoles();
			int length = roles.length;
			for(int i=0;i< length;i++){
				System.out.println(roles[i]);
			}

    }

    @ShellMethod(value = "query property:summary,rating of REVIEWED, you should input person's name, movie's title",group = "Relationship Basic Commands")
    public void queryPropertiesReviewed(String name, String title){

        Person person = personRepository.findByName(name);
        Movie movie = movieRepository.findByTitle(title);

        ReviewedProperties reviewedProperties = person.getReviewedProperties(movie);
        System.out.println("the summary of Reviewed relationship between "+name+" and "+title+" :");
		System.out.println(reviewedProperties.getSummary());
		System.out.println("the rating of Reviewed relationship between "+name+" and "+title+" :");
		System.out.println(reviewedProperties.getRating().toString());

    }

    @ShellMethod(value = "query movies which person acted in, you should input person's name",group = "Advanced Query Commands")
    public void queryActedInMovies(String name){

		List<Movie> actedInMovies = movieRepository.findActedInMovies(name);
		System.out.println("Movies acted by "+name+" :");
		actedInMovies.forEach(movie->System.out.println(movie.infomation()));

    }

    @ShellMethod(value = "query movies which person reviewed, you should input person's name",group = "Advanced Query Commands")
    public void queryReviewedMovies(String name){

		List<Movie> reviewedMovies = movieRepository.findReviewedMovies(name);
		System.out.println("Movies reviewed by "+name+" :");
		reviewedMovies.forEach(movie->System.out.println(movie.infomation()));

    }

    @ShellMethod(value = "query masters of person, you should input person's name",group = "Advanced Query Commands")
    public void queryMasters(String name){

		List<Person> masters = personRepository.findMasters(name);
		System.out.println("Masters followed by "+name+" :");
		masters.forEach(person->System.out.println(person.infomation()));

    }

    @ShellMethod(value = "query followers of person, you should input person's name",group = "Advanced Query Commands")
    public void queryFollowers(String name){

        List<Person> followers = personRepository.findFollowers(name);
		System.out.println("Followers of "+name+" :");
		followers.forEach(person->System.out.println(person.infomation()));

    }

    @ShellMethod(value = "query movies which person directed, you should input person's name",group = "Advanced Query Commands")
    public void queryDiectedMovies(String name){

		List<Movie> directedMovies = movieRepository.findDirectedMovies(name);
		System.out.println("Movies directed by "+name+" :");
		directedMovies.forEach(movie->System.out.println(movie.infomation()));

    }

    @ShellMethod(value = "query movies which person produced, you should input person's name",group = "Advanced Query Commands")
    public void queryProducedMovies(String name){

		List<Movie> producedMovies = movieRepository.findProducedMovies(name);
		System.out.println("Movies produced by "+name+" :");
		producedMovies.forEach(movie->System.out.println(movie.infomation()));

    }

    @ShellMethod(value = "query movies which person wrote, you should input person's name",group = "Advanced Query Commands")
    public void queryWrittenMovies(String name){

		List<Movie> writtenMovies = movieRepository.findWrittenMovies(name);
		System.out.println("Movies written by "+name+" :");
		writtenMovies.forEach(movie->System.out.println(movie.infomation()));

    }

    @ShellMethod(value = "query all relative people of movie, you should input movie's title",key = "query-relative-people-movie",group = "Advanced Query Commands")
    public void queryRelativePeopleOfMovie(String title){

        List<Person> allRelativePeople = personRepository.findAllRelativePeopleOfMovie(title);
		System.out.println("All relative people of the movie The Greeb Mile :");
		allRelativePeople.forEach(person->System.out.println(person.infomation()));
        
    }
    
}
