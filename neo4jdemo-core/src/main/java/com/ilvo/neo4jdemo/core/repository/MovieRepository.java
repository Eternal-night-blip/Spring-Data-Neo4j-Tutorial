package com.ilvo.neo4jdemo.core.repository;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import com.ilvo.neo4jdemo.core.nodes.Movie;


public interface MovieRepository extends Neo4jRepository<Movie,Long>{

    public Movie findByTitle(String title);
    
    @Query("MATCH (movies:Movie) <-[:DIRECTED]- (director:Person) WHERE director.name = $director_name RETURN movies")
    public List<Movie> findDirectedMovies(String director_name);

    @Query("MATCH (movies:Movie) <-[:ACTED_IN]- (actor:Person) WHERE actor.name = $actor_name RETURN movies")
    public List<Movie> findActedInMovies(String actor_name);

    @Query("MATCH (movies:Movie) <-[:REVIEWED]- (reviewer:Person) WHERE reviewer.name = $reviewer_name RETURN movies")
    public List<Movie> findReviewedMovies(String reviewer_name);

    @Query("MATCH (movies:Movie) <-[:PRODUCED]- (producer:Person) WHERE producer.name = $producer_name RETURN movies")
    public List<Movie> findProducedMovies(String producer_name);

    @Query("MATCH (movies:Movie) <-[:WROTE]- (writer:Person) WHERE writer.name = $writer_name RETURN movies")
    public List<Movie> findWrittenMovies(String writer_name);
    
    @Query("MATCH (movies:Movie) <-[]- (person:Person) WHERE person.name = $person_name RETURN movies")
    public List<Movie> findAllRelativeMovies(String person_name);
    
    @Query("MATCH (person:Person) -[r]-> (:Movie) WHERE person.name = $person_name DELETE r")
    public void deleteAllRelationshipsToAllMovies(String person_name);
    
}
