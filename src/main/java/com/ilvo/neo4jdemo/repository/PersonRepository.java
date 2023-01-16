package com.ilvo.neo4jdemo.repository;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import com.ilvo.neo4jdemo.nodes.Person;

public interface PersonRepository extends Neo4jRepository<Person,String> {

    public Person findByName(String name);

    @Query("MATCH (followers:Person) -[:FOLLOWS]-> (followed:Person) WHERE followed.name = $followed_name RETURN followers")
    public List<Person> findFollowers(String followed_name);

    @Query("MATCH (follower:Person) -[:FOLLOWS]-> (masters:Person) WHERE follower.name = $follower_name RETURN masters")
    public List<Person> findMasters(String follower_name);

    @Query("MATCH (persons:Person) -[r]-> (movie:Movie) WHERE movie.title = $movie_title RETURN persons")
    public List<Person> findAllRelativePeopleOfMovie(String movie_title);

}
