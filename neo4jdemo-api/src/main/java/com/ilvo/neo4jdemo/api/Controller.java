package com.ilvo.neo4jdemo.api;


import java.beans.AppletInitializer;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ilvo.neo4jdemo.core.dto.ActedInProperty;
import com.ilvo.neo4jdemo.core.dto.ReviewedProperties;
import com.ilvo.neo4jdemo.core.nodes.Movie;
import com.ilvo.neo4jdemo.core.nodes.Person;
import com.ilvo.neo4jdemo.core.nodes.properties.BornYear;
import com.ilvo.neo4jdemo.core.nodes.properties.Name;
import com.ilvo.neo4jdemo.core.nodes.properties.ReleasedYear;
import com.ilvo.neo4jdemo.core.nodes.properties.TagLine;
import com.ilvo.neo4jdemo.core.nodes.properties.Title;
import com.ilvo.neo4jdemo.core.relationships.ActedIn;
import com.ilvo.neo4jdemo.core.relationships.Reviewed;
import com.ilvo.neo4jdemo.core.relationships.properties.Rating;
import com.ilvo.neo4jdemo.core.relationships.properties.Roles;
import com.ilvo.neo4jdemo.core.relationships.properties.Summary;
import com.ilvo.neo4jdemo.core.service.PersonService;
import com.ilvo.neo4jdemo.core.service.MovieService;
import com.ilvo.neo4jdemo.api.request.*;
import com.ilvo.neo4jdemo.api.ApiResponse;

@RestController
@RequestMapping("/com.ilvo.neo4jdemo")
public class Controller {
    
    private final PersonService personService;
    private final MovieService movieService;
    
    public Controller(PersonService personService, MovieService MovieService){
        this.personService = personService;
        this.movieService = MovieService; 
    }
    

    @Transactional
    @PostMapping("/person")
    public ApiResponse addPerson(@RequestBody AddPersonRequest request){
        
        final String name = request.name();
        final Integer bornYear = request.bornYear();
        
        Person person = personService.addPerson(Name.of(name), BornYear.of(bornYear));
        return ApiResponse.ok(person);

    }
    
    @Transactional
    @DeleteMapping("/person")
    public ApiResponse deletePerson(@RequestParam(value = "name") String person){
        
        personService.deletePerson(Name.of(person));
        return ApiResponse.ok("you delete the person :"+person);

    }

    @Transactional
    @PostMapping("/movie")
    public ApiResponse addMovie(@RequestBody AddMovieRequest request){
        
        final String title = request.title();
        final String tagLine = request.tagLine();
        final Integer releasedYear = request.releasedYear();
        Movie movie = movieService.addMovie(Title.of(title),TagLine.of(tagLine),ReleasedYear.of(releasedYear));
        return ApiResponse.ok(movie);

    }

    @Transactional
    @DeleteMapping("/movie")
    public ApiResponse deleteMovie(@RequestParam(value = "title") String movie){
        
        movieService.deleteMovie(Title.of(movie));
        return ApiResponse.ok("you delete the movie :"+movie);

    }

    @Transactional
    @PostMapping("/actedIn")
    public ApiResponse actedIn(@RequestBody ActedInRequest request){
        
        final String name = request.name();
        final String title = request.title();
        final String[] roles = request.roles();
        ActedIn actedIn = personService.actedIn(Name.of(name),Title.of(title),Roles.of(roles));
        return ApiResponse.ok(actedIn);
        
    }

    @Transactional
    @DeleteMapping("/actedIn")
    public ApiResponse deleteActedIn(@RequestParam(value = "name") String person,@RequestParam(value="title") String movie){
        
        personService.deleteActedIn(Name.of(person),Title.of(movie));
        return ApiResponse.ok("you delete an ACTED_IN relationship between "+person+" and "+movie);
        
    }

    @Transactional
    @PostMapping("/reviewed")
    public ApiResponse reviewed(@RequestBody ReviewedRequest request){

        final String name = request.name();
        final String title = request.title();
        final String summary = request.summary();
        final Integer rating = request.rating();
        Reviewed reviewed = personService.reviewed(Name.of(name),Title.of(title),Summary.of(summary),Rating.of(rating));
        return ApiResponse.ok(reviewed);
    
    }
    
    @Transactional
    @DeleteMapping("/reviewed")
    public ApiResponse deleteReviewed(@RequestParam(value = "name") String person,@RequestParam(value="title") String movie){
       
        personService.deleteReviewed(Name.of(person),Title.of(movie));
        return ApiResponse.ok("you delete an REVIEWED relationship between "+person+" and "+movie);

    }

    @Transactional
    @PostMapping("/follows")
    public ApiResponse follows(@RequestBody FollowsRequest request){

        final String follower = request.followerName();
        final String master = request.masterName();
        Person master_follows = personService.follows(Name.of(follower),Name.of(master));
        return ApiResponse.ok(master_follows);

    }

    @Transactional
    @DeleteMapping("/follows")
    public ApiResponse deleteFollows(@RequestParam(value = "follower_name") String follower,@RequestParam(value="matser_name") String master){

        personService.deleteFollows(Name.of(follower),Name.of(master));
        return ApiResponse.ok("you delete an FOLLOWS relationship between "+follower+" and "+master);

    }

    @Transactional
    @PostMapping("/directed")
    public ApiResponse directed(@RequestBody NameTitleRequest request){

        final String person = request.name();
        final String movie = request.title();
        Movie directedMovie = personService.directed(Name.of(person),Title.of(movie));
        return ApiResponse.ok(directedMovie);

    }

    @Transactional
    @DeleteMapping("/directed")
    public ApiResponse deleteDirected(@RequestParam(value = "name") String person,@RequestParam(value="title") String movie){
        
        personService.deleteDirected(Name.of(person),Title.of(movie));
        return ApiResponse.ok("you delete an DIRECTED relationship between "+person+" and "+movie);

    }

    @Transactional
    @PostMapping("/produced")
    public ApiResponse produced(@RequestBody NameTitleRequest request){

        final String person = request.name();
        final String movie = request.title();
        Movie producedMovie = personService.produced(Name.of(person),Title.of(movie));
        return ApiResponse.ok(producedMovie);

    }

    @Transactional
    @DeleteMapping("/produced")
    public ApiResponse deleteProduced(@RequestParam(value = "name") String person,@RequestParam(value="title") String movie){
         
        personService.deleteProduced(Name.of(person),Title.of(movie));
        return ApiResponse.ok("you delete an PRODUCED relationship between "+person+" and "+movie);

    }

    @Transactional
    @PostMapping("/wrote")
    public ApiResponse wrote(@RequestBody NameTitleRequest request){

        final String person = request.name();
        final String movie = request.title();
        Movie writtenMovie = personService.wrote(Name.of(person),Title.of(movie));
        return ApiResponse.ok(writtenMovie);

    }

    @Transactional
    @DeleteMapping("/wrote")
    public ApiResponse deleteWrote(@RequestParam(value = "name") String person,@RequestParam(value="title") String movie){
    
        personService.deleteWrote(Name.of(person),Title.of(movie));
        return ApiResponse.ok("you delete an WROTE relationship between "+person+" and "+movie);

    }

    @GetMapping("/actedIn/properties")
    public ApiResponse queryPropertiesActedIn(@RequestParam(value = "name") String person,@RequestParam(value="title") String movie){

        ActedInProperty actedInProperty =  personService.getActedInProperty(Name.of(person),Title.of(movie));
        return ApiResponse.ok(actedInProperty);

    }

    @GetMapping("/reviewed/properties")
    public ApiResponse queryPropertiesReviewed(@RequestParam(value = "name") String person,@RequestParam(value="title") String movie){

        ReviewedProperties reviewedProperties =  personService.getReviewedProperties(Name.of(person),Title.of(movie));
        return ApiResponse.ok(reviewedProperties);

    }

    @GetMapping("/person/acted_in_movies")
    public ApiResponse queryActedInMovies(@RequestParam(value = "name") String person){

		List<Movie> actedInMovies =  personService.getActedInMovies(Name.of(person));
		return ApiResponse.ok(actedInMovies);

    }

    @GetMapping("/person/reviewed_movies")
    public ApiResponse queryReviewedMovies(@RequestParam(value = "name") String person){

		List<Movie> reviewedMovies =  personService.getReviewedMovies(Name.of(person));
		return ApiResponse.ok(reviewedMovies);

    }

    @GetMapping("/person/masters")
    public ApiResponse queryMasters(@RequestParam(value = "name") String person){

		List<Person> masters =  personService.getMasters(Name.of(person));
		return ApiResponse.ok(masters);
        

    }

    @GetMapping("/person/followers")
    public ApiResponse queryFollowers(@RequestParam(value = "name") String person){

        List<Person> followers =  personService.getFollowers(Name.of(person));
		return ApiResponse.ok(followers);
        

    }

    @GetMapping("/person/directed_movies")
    public ApiResponse queryDirectedMovies(@RequestParam(value = "name") String person){

		List<Movie> directedMovies =  personService.getDirectedMovies(Name.of(person));
		return ApiResponse.ok(directedMovies);

    }

    @GetMapping("/person/produced_movies")
    public ApiResponse queryProducedMovies(@RequestParam(value = "name") String person){

		List<Movie> producedMovies =  personService.getProducedMovies(Name.of(person));
		return ApiResponse.ok(producedMovies);

    }

    @GetMapping("/person/written_movies")
    public ApiResponse queryWrittenMovies(@RequestParam(value = "name") String person){
 
		List<Movie> writtenMovies =  personService.getWrittenMovies(Name.of(person));
		return ApiResponse.ok(writtenMovies);

    }

    @GetMapping("/movie/relative_people")
    public ApiResponse queryRelativePeopleOfMovie(@RequestParam(value = "title") String movie){

        List<Person> allRelativePeople = movieService.getRelativePeople(Title.of(movie));
		return ApiResponse.ok(allRelativePeople);
        
    }

}
