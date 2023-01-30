package com.ilvo.neo4jdemo.core.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.transaction.annotation.Transactional;

import com.ilvo.neo4jdemo.core.Bootstrap;
import com.ilvo.neo4jdemo.core.nodes.Movie;
import com.ilvo.neo4jdemo.core.nodes.Person;
import com.ilvo.neo4jdemo.core.nodes.properties.Name;
import com.ilvo.neo4jdemo.core.nodes.properties.ReleasedYear;
import com.ilvo.neo4jdemo.core.nodes.properties.TagLine;
import com.ilvo.neo4jdemo.core.nodes.properties.Title;
import com.ilvo.neo4jdemo.core.utils.EquivalentUtil;
import com.ilvo.neo4jdemo.core.utils.ExistException;
import com.ilvo.neo4jdemo.core.utils.TransferUtil;
import com.ilvo.neo4jdemo.core.utils.EmptyException;

@Transactional   // @SpringBootTest 默认启动事务后，自动回滚,并且是给每一个方法都加上了事务管理，其实这没必要的
@SpringBootTest(classes =Bootstrap.class,webEnvironment = WebEnvironment.NONE)
public class MovieServiceTest {

    private MovieService movieService;

    public MovieServiceTest(@Autowired MovieService movieService){
        this.movieService = movieService;
    }
   
    @Test
    public void should_throw_exception_for_null_parameter_in_all_method(){
        
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> movieService.addMovie(null, null, null));
        
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> movieService.deleteMovie(null));
        
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(()-> movieService.getMovie(null));
        
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> movieService.getRelativePeople(null));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> movieService.getDirectors(null));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> movieService.getActors(null));
        
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> movieService.getScreenWriters(null));
        
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> movieService.getProducers(null));   
                
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> movieService.getReviewers(null));        
    }

    @Test
    public void should_add_movie(){
        
        Movie movie = movieService.addMovie(Title.of("fooTitle"),TagLine.of("fooTagLine"),ReleasedYear.of(2002));
        
        assertThat(movie.getTitle()).isEqualTo("fooTitle");
        assertThat(movie.getTagLine()).isEqualTo("fooTagLine");
        assertThat(movie.getReleasedYear()).isEqualByComparingTo(2002);
    
    }
    
    @Test
    public void should_throw_exception_for_exist_movie_in_add_movie(){
        assertThatExceptionOfType(ExistException.class)
                .isThrownBy(() -> movieService.addMovie(Title.of("The Da Vinci Code"),TagLine.of("Break The Codes"),ReleasedYear.of(2006)));
        
    }
    
    @Test
    public void should_delete_movie(){

        boolean does_delete_movie = movieService.deleteMovie(Title.of("The Devil's Advocate"));
        assertThat(does_delete_movie).isTrue();
    
    }
   
    @Test
    public void should_throw_exception_for_null_movie_in_delete_movie(){
        
        assertThatExceptionOfType(EmptyException.class)
                .isThrownBy(() -> movieService.deleteMovie(Title.of("null_movie_for_delete")));

    }

    @Test
    public void should_does_delete_movie(){
        boolean does_delete_movie = movieService.doesDeleteMovie(Title.of("The Devil's Advocate"));
        assertThat(does_delete_movie).isFalse();
    }
    
    @Test
    public void should_get_movie(){
        
        Movie movie = movieService.getMovie(Title.of("The Devil's Advocate"));
        boolean doGetMovie = movie.getTitle().equals("The Devil's Advocate");
        assertThat(doGetMovie).isTrue();
    }

    @Test
    public void should_throw_exception_for_null_movie_in_get_movie(){
        assertThatExceptionOfType(EmptyException.class)
                .isThrownBy(()->movieService.getMovie(Title.of("null_movie")));
    }

    @Test
    public void should_get_relative_people(){

        List<Name> names = new ArrayList<>();
        names.add(Name.of("Tom Hanks"));
        names.add(Name.of("Ron Howard"));
        names.add(Name.of("Ed Harris"));
        names.add(Name.of("Gary Sinise"));
        names.add(Name.of("Kevin Bacon"));
        names.add(Name.of("Bill Paxton"));
        
        List<Person> people = movieService.getRelativePeople(Title.of("Apollo 13"));
        
        List<Name> people_names_checked = TransferUtil.personsToNames(people); 
        
        assertThat(EquivalentUtil.doesEquivalent(people_names_checked, names)).isTrue(); 
    }
    
    @Test
    public void should_throw_exception_for_null_movie_in_get_relative_people(){
        assertThatExceptionOfType(EmptyException.class)
                .isThrownBy(() -> movieService.getRelativePeople(Title.of("null_movie_for_get")));
    }

    @Test
    public void should_get_directors(){
 
        List<Name> names = new ArrayList<>();
        names.add(Name.of("Ron Howard"));

        List<Person> directors_checked = movieService.getDirectors(Title.of("Apollo 13"));
        
        List<Name> directors_names_checked = TransferUtil.personsToNames(directors_checked);
        assertThat(EquivalentUtil.doesEquivalent(directors_names_checked,names)).isTrue(); 
    }
    
    @Test
    public void should_throw_exception_for_null_movie_in_get_directors(){
        assertThatExceptionOfType(EmptyException.class)
                .isThrownBy(() -> movieService.getDirectors(Title.of("null_movie_for_get")));
    }

    @Test
    public void should_get_actors(){

        List<Name> names = new ArrayList<>();
        names.add(Name.of("Tom Hanks"));
        names.add(Name.of("Ed Harris"));
        names.add(Name.of("Gary Sinise"));
        names.add(Name.of("Kevin Bacon"));
        names.add(Name.of("Bill Paxton"));

        List<Person> actors_checked = movieService.getActors(Title.of("Apollo 13"));
        
        List<Name> actors_names_checked = TransferUtil.personsToNames(actors_checked);
        assertThat(EquivalentUtil.doesEquivalent(actors_names_checked,names)).isTrue();   
    }

    @Test
    public void should_throw_exception_for_null_movie_in_get_actors(){
        assertThatExceptionOfType(EmptyException.class)
                .isThrownBy(() -> movieService.getActors(Title.of("null_movie_for_get")));
    }

    @Test
    public void should_get_screen_writers(){

        List<Name> names = new ArrayList<>();
        names.add(Name.of("Aaron Sorkin"));

        List<Person> screenWriters_checked = movieService.getScreenWriters(Title.of("A Few Good Men"));
        
        List<Name> screenWriters_names_checked = TransferUtil.personsToNames(screenWriters_checked);
        assertThat(EquivalentUtil.doesEquivalent(screenWriters_names_checked,names)).isTrue();   
    }

    @Test
    public void should_throw_exception_for_null_movie_in_get_screen_writers(){
        assertThatExceptionOfType(EmptyException.class)
                .isThrownBy(() -> movieService.getScreenWriters(Title.of("null_movie_for_get")));
    }

    @Test
    public void should_get_producers(){
        List<Name> names = new ArrayList<>();
        names.add(Name.of("Joel Silver"));
        names.add(Name.of("Lana Wachowski"));
        names.add(Name.of("Lilly Wachowski"));

        List<Person> producers_checked = movieService.getProducers(Title.of("Ninja Assassin"));
        List<Name> producers_names_checked = TransferUtil.personsToNames(producers_checked);
        assertThat(EquivalentUtil.doesEquivalent(producers_names_checked,names)).isTrue();   
    }
    
    @Test
    public void should_throw_exception_for_null_movie_in_get_producers(){
        assertThatExceptionOfType(EmptyException.class)
                .isThrownBy(() -> movieService.getProducers(Title.of("null_movie_for_get")));
    }

    @Test
    public void should_get_reviewers(){

        List<Name> names = new ArrayList<>();
        names.add(Name.of("James Thompson"));
        names.add(Name.of("Jessica Thompson"));

        List<Person> reviewers_checked = movieService.getReviewers(Title.of("The Da Vinci Code"));
        List<Name> reviewers_names_checked = TransferUtil.personsToNames(reviewers_checked);
        assertThat(EquivalentUtil.doesEquivalent(reviewers_names_checked,names)).isTrue();   
    }

    @Test
    public void should_throw_exception_for_null_movie_in_get_reviewers(){
        assertThatExceptionOfType(EmptyException.class)
                .isThrownBy(() -> movieService.getReviewers(Title.of("null_movie_for_get")));
    }


}
