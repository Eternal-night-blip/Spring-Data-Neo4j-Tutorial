package com.ilvo.neo4jdemo.repository;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import com.ilvo.neo4jdemo.nodes.Person;

public interface PersonRepository extends Neo4jRepository<Person,Long> {

    public Person findByName(String name);

    @Query("MATCH (followers:Person) -[:FOLLOWS]-> (followed:Person) WHERE followed.name = $followed_name RETURN followers")
    public List<Person> findFollowers(String followed_name);

    @Query("MATCH (follower:Person) -[:FOLLOWS]-> (masters:Person) WHERE follower.name = $follower_name RETURN masters")
    public List<Person> findMasters(String follower_name);

    @Query("MATCH (persons:Person) -[r]-> (movie:Movie) WHERE movie.title = $movie_title RETURN persons")
    public List<Person> findAllRelativePeopleOfMovie(String movie_title);
    
    @Query("MATCH (:Person{name:$name}) -[r:ACTED_IN]-> (:Movie{title:$title}) DELETE r")
    public void deleteActedInRelationship(String name, String title);

    @Query("MATCH (:Person{name:$name}) -[r:REVIEWED]-> (:Movie{title:$title}) DELETE r")
    public void deleteReviewedRelationship(String name, String title);

    @Query("MATCH (:Person{name:$name}) -[r:FOLLOWS]-> (:Movie{title:$title}) DELETE r")
    public void deleteFollowsRelationship(String name_follower, String name_master);
    
    @Query("MATCH (:Person{name:$name}) -[r:DIRECTED]-> (:Movie{title:$title}) DELETE r")
    public void deleteDirectedRelationship(String name, String title);
   
    @Query("MATCH (:Person{name:$name}) -[r:PRODUCED]-> (:Movie{title:$title}) DELETE r")
    public void deleteProducedRelationship(String name, String title);
    
    @Query("MATCH (:Person{name:$name}) -[r:WROTE]-> (:Movie{title:$title}) DELETE r")
    public void deleteWroteRelationship(String name, String title);

}
