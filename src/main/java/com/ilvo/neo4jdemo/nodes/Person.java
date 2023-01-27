package com.ilvo.neo4jdemo.nodes;

import java.util.LinkedList;
import java.util.List;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.stringtemplate.v4.compiler.CodeGenerator.listElement_return;

import com.ilvo.neo4jdemo.dto.ActedInProperty;
import com.ilvo.neo4jdemo.dto.ReviewedProperties;
import com.ilvo.neo4jdemo.relationships.ActedIn;
import com.ilvo.neo4jdemo.relationships.Reviewed;


@Node("Person")
public class Person {

    @Id@GeneratedValue
    private Long id;

    @Property("name")
    private String name;
    
    @Property("born")
    private Integer bornYear;

    @Relationship("FOLLOWS")  //默认是OUTGOING方向
    List<Person> masters;

    @Relationship("ACTED_IN")
    List<ActedIn> actedIns;

    @Relationship("REVIEWED")
    List<Reviewed> revieweds;
    
    @Relationship("DIRECTED")    
    List<Movie> directedMovies;
    
    @Relationship("PRODUCED")
    List<Movie> producedMovies;

    @Relationship("WROTE")
    List<Movie> writtenMovies;
    

    public Person(String name,Integer bornYear){
        this.id = null;
        this.name = name;
        this.bornYear = bornYear;
    }


    public String getName(){
        return name;
    }

    public Integer getBornYear(){
        return bornYear;
    }

    public Person follows(Person master){
        if (masters == null){
            masters = new LinkedList<>();
        }
        masters.add(master);
        return master;
    }

    public ActedIn actedIn(Movie movie,String[] roles){
        if ( actedIns == null){
            actedIns = new LinkedList<>();
        }
        ActedIn actedIn = new ActedIn(movie,roles);
        actedIns.add(actedIn);
        return actedIn;
    }
    
    public Reviewed reviewed(Movie movie,String summary, Integer rating){
        if ( revieweds == null){
            revieweds = new LinkedList<>();
        }
        Reviewed reviewed = new Reviewed(movie, summary, rating);
        revieweds.add(reviewed);
        return reviewed;
    }

    public Movie directed(Movie movie){
        if ( directedMovies == null){
            directedMovies = new LinkedList<>();
        }
        directedMovies.add(movie);
        return movie;
    }

    public Movie produced(Movie movie){
        if ( producedMovies == null){
            producedMovies = new LinkedList<>();
        }
        producedMovies.add(movie);
        return movie;
    }

    public Movie wrote(Movie movie){
        if ( writtenMovies == null){
            writtenMovies = new LinkedList<>();
        }
        writtenMovies.add(movie);
        return movie;
    }

    public boolean deleteActedIn(Movie movie){
        for(ActedIn acted : actedIns){
            if(acted.getMovie().getTitle().equals(movie.getTitle())){
                actedIns.remove(acted);
                return true;
            }
        }
        return false;
    }

    public boolean deleteReviewed(Movie movie){
        for(Reviewed reviewed : revieweds){
            if(reviewed.getMovie().getTitle().equals(movie.getTitle())){
                revieweds.remove(reviewed);
                return true;
            }
        }
        return false;
    }

    public boolean deleteFollows(Person person){
        for(Person master : masters){
            if(master.getName().equals(person.getName())){
                masters.remove(master);
                return true;
            }
        }
        return false;
    }

    public boolean deleteDirected(Movie movie){
        for(Movie directedMovie : directedMovies){
            if(directedMovie.getTitle().equals(movie.getTitle())){
                directedMovies.remove(directedMovie);
                return true;
            }
        }
        return false;
    }

    public boolean deleteProduced(Movie movie){
        for(Movie producedMovie : producedMovies){
            if(producedMovie.getTitle().equals(movie.getTitle())){
                producedMovies.remove(producedMovie);
                return true;
            }
        }
        return false;
    }

    public boolean deleteWrote(Movie movie){
        for(Movie writtenMovie : writtenMovies){
            if(writtenMovie.getTitle().equals(movie.getTitle())){
                writtenMovies.remove(writtenMovie);
                return true;
            }
        }
        return false;
    }

    public ActedInProperty getActedInProperty(Movie movie){

        ActedInProperty result = null;
        
        for(ActedIn actedIn : actedIns){
            if(actedIn.getMovie().getTitle().equals(movie.getTitle())){
                result = new ActedInProperty(actedIn.getRoles());
                break;
            }

        }

        return result;
    }

    public ReviewedProperties getReviewedProperties(Movie movie){

        ReviewedProperties result = null;

        for(Reviewed reviewed : revieweds){

            if(reviewed.getMovie().getTitle().equals(movie.getTitle())){
                result = new ReviewedProperties(reviewed.getSummary(), reviewed.getRating());
                break;
            }
        }

        return result;
    }
    
    @Override
    public boolean equals(Object obj){

        if(!(obj instanceof Person)){
            return false;
        }
        
        Person another = (Person)obj;
        return this.name.equals(another.name) && this.bornYear.equals(another.bornYear);    
    }
    
    @Override
    public int hashCode(){
        int result = name.hashCode();
        result = 31*result+bornYear.hashCode();
        return result;
    }
    
    @Override
    public String toString(){
        return "name: "+ name + ", born: "+ bornYear +",id: " + id;
    }

}
