package com.ilvo.neo4jdemo.core.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.transaction.annotation.Transactional;

import com.ilvo.neo4jdemo.core.Bootstrap;
import com.ilvo.neo4jdemo.core.nodes.Movie;
import com.ilvo.neo4jdemo.core.nodes.Person;
import com.ilvo.neo4jdemo.core.nodes.properties.BornYear;
import com.ilvo.neo4jdemo.core.nodes.properties.Name;
import com.ilvo.neo4jdemo.core.nodes.properties.ReleasedYear;
import com.ilvo.neo4jdemo.core.nodes.properties.TagLine;
import com.ilvo.neo4jdemo.core.nodes.properties.Title;
import com.ilvo.neo4jdemo.core.relationships.ActedIn;
import com.ilvo.neo4jdemo.core.relationships.properties.Roles;
import com.ilvo.neo4jdemo.core.utils.EmptyException;
import com.ilvo.neo4jdemo.core.utils.ExistException;


@Transactional   // @SpringBootTest 默认启动事务后，自动回滚,并且是给每一个方法都加上了事务管理，其实这没必要的
@SpringBootTest(classes =Bootstrap.class,webEnvironment = WebEnvironment.NONE)
public class PersonServiceTest {

    private PersonService personService;
    private MovieService movieService;

    public PersonServiceTest(@Autowired PersonService personService,@Autowired MovieService movieService){
        this.personService = personService;
        this.movieService = movieService;
    }
    
    @Test
    public void should_throw_exception_for_null_parameter_in_all_method(){
        
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> personService.addPerson(null, null));
        
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> personService.deletePerson(null));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(()-> personService.getPerson(null));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> personService.follows(null,null));
        
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> personService.deleteFollows(null,null));
        
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> personService.getMasters(null));
        
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> personService.getFollowers(null));
        
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> personService.actedIn(null,null,null));
       
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> personService.deleteActedIn(null,null));
       
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> personService.getActedInMovies(null));
       
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> personService.reviewed(null,null,null,null));
       
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> personService.deleteReviewed(null,null));
       
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> personService.getReviewedMovies(null));
       
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> personService.directed(null,null));
       
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> personService.deleteDirected(null,null));
       
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> personService.getDirectedMovies(null));
       
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> personService.produced(null,null));
       
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> personService.deleteProduced(null,null));
       
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> personService.getProducedMovies(null));
       
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> personService.wrote(null,null));
       
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> personService.deleteWrote(null,null));
       
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> personService.getWrittenMovies(null));
       
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> personService.getRelativeMovies(null));
       
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> personService.deleteRelativeRelationshipsToAllMovies(null));
       
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> personService.getActedInProperty(null,null));
       
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> personService.getReviewedProperties(null,null));

    }
    
    @Test
    public void should_add_person(){

        Person person = personService.addPerson(Name.of("foo"), BornYear.of(2000));
        assertThat(person.getName()).isEqualTo("foo");
        assertThat(person.getBornYear()).isEqualTo(2000);
    }

    @Test
    public void should_throw_exception_for_exist_person_in_add_person(){
        assertThatExceptionOfType(ExistException.class)
                .isThrownBy(() -> personService.addPerson(Name.of("Lilly Wachowski"),BornYear.of(1967)));
    }

    @Test
    public void should_delete_person(){
        boolean does_delete = personService.deletePerson(Name.of("Demi Moore"));
        assertThat(does_delete).isTrue();
    }

    @Test
    public void should_throw_exception_for_null_person_in_delete_person(){
        assertThatExceptionOfType(EmptyException.class)
                .isThrownBy(() -> personService.deletePerson(Name.of("null_people_to_delete")));
    }

    @Test
    public void should_does_delete_person(){
        boolean does_delete_movie = personService.doesDeletePerson(Name.of("Demi Moore"));
        assertThat(does_delete_movie).isFalse();
    }
    
    @Test
    public void should_get_person(){
        Person person = personService.getPerson(Name.of("Demi Moore"));
        assertThat(person.getName()).isEqualTo("Demi Moore");
    }

    @Test
    public void should_follows(){
        
        personService.addPerson(Name.of("foo"), BornYear.of(2002));
        personService.addPerson(Name.of("bar"), BornYear.of(2001));
        Person new_master = personService.follows(Name.of("foo"), Name.of("bar"));
        
        List<Person> masters = personService.getMasters(Name.of("foo"));
        
        boolean  doesContain = masters.contains(new_master);
        assertThat(doesContain).isTrue();
    }

    @Test
    public void should_throw_exception_for_null_person_in_follows(){
        assertThatExceptionOfType(EmptyException.class)
            .isThrownBy(()-> personService.follows(Name.of("null_follower"), Name.of("null_master")));

    }

    @Test
    public void should_delete_follows(){
        
        personService.addPerson(Name.of("foo"), BornYear.of(2002));
        personService.addPerson(Name.of("bar"), BornYear.of(2001));
        personService.follows(Name.of("foo"), Name.of("bar"));
        boolean doesDeleteFollows = personService.deleteFollows(Name.of("foo"),Name.of("bar"));
        assertThat(doesDeleteFollows).isTrue();
    }

    @Test
    public void should_throw_exception_for_null_person_in_delete_follows(){
        assertThatExceptionOfType(EmptyException.class)
            .isThrownBy(()-> personService.deleteFollows(Name.of("null_follower"), Name.of("null_master")));

    }

    @Test
    public void should_acted_in(){
        
        personService.addPerson(Name.of("foo"), BornYear.of(2002));
        movieService.addMovie(Title.of("bar"),TagLine.of("bar_taglin"),ReleasedYear.of(2023));
        String[] roles = {"role1","role2"};
        ActedIn actedIn = personService.actedIn(Name.of("foo"), Title.of("bar"),Roles.of(roles));
        
        Movie bar = movieService.getMovie(Title.of("bar"));
        boolean  doesActedIn = bar.equals(actedIn.getMovie());
        
        assertThat(doesActedIn).isTrue();
    }

    @Test
    public void should_throw_exception_for_null_person_or_movie_in_acted_in(){
        String[] r = {"null_role1","null_role2"};
        Roles roles = Roles.of(r);
        assertThatExceptionOfType(EmptyException.class)
               .isThrownBy(()-> personService.actedIn(Name.of("null_person"),Title.of("null_title"), roles));
    }

    






}
