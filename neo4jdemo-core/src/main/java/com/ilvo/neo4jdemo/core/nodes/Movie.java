package com.ilvo.neo4jdemo.core.nodes;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node("Movie")    // @Node用来说明 这个类对应Neo4j的节点,用类名标记该节点的标签,如果类名与节点标签不一致则需要在@Node("标签名")
public class Movie {

    @Id@GeneratedValue
    private Long id;

    @Property("title")
    private String title;

    @Property("tagline")
    private String tagline;
    
    @Property("released")
    private Integer releasedYear;

    public Movie(String title, String tagline, Integer releasedYear){
        this.id = null;
        this.title = title;
        this.tagline = tagline;
        this.releasedYear = releasedYear;

    }

    public String getTitle(){
        return title;
    }

    public String getTagLine(){
        return tagline;
    }

    public Integer getReleasedYear(){
        return releasedYear;
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj == this){
            return true;
        }
        if(!(obj instanceof Movie)){
            return false;
        }

        Movie anothor = (Movie)obj;
        return this.title.equals(anothor.title) && this.tagline.equals(anothor.tagline) && this.releasedYear.equals(anothor.releasedYear);     
    }
    
    @Override
    public int hashCode(){
        int result  = this.title.hashCode();
        result = 31 * result +this.tagline.hashCode();
        result = 31 * result + this.releasedYear.hashCode();
        return result;
    }

    @Override
    public String toString(){
        return "title: " + title + ", tagline: " + tagline + ", released: "+ releasedYear+", id: "+id; 
    }

}
