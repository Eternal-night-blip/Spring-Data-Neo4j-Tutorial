package com.ilvo.neo4jdemo.dto;

public class ActedInProperty {

    private final String[] roles;

    public ActedInProperty(String[] roles){
        this.roles = roles;
    }

    public String[] getRoles(){
        return roles;
    }
    
}
