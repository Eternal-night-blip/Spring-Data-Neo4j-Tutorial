package com.ilvo.neo4jdemo.relationships.properties;

import com.ilvo.neo4jdemo.utils.EmptyUtil;

public class Roles {

    private final String[] characters;

    private Roles(final String[] characters){
        this.characters = characters;
    }


    public static Roles of(final String[] characters){
        
        
        if(characters == null || characters.length == 0){

            throw new IllegalArgumentException("Nill or empty roles is not allowed");

        }else {

            for(int i=0;i<characters.length;i++){

                if(EmptyUtil.isEmpty(characters[i])){

                    throw new IllegalArgumentException("empty role or roles is not allowed");
                    
            }

        }
    }

        return new Roles(characters);
    }

    public String[] get(){
        return characters;
    }
    
}
