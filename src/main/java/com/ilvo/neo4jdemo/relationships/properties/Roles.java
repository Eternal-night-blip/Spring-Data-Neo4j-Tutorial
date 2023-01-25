package com.ilvo.neo4jdemo.relationships.properties;

import com.ilvo.neo4jdemo.utils.EmptyUtil;

public class Roles {

    private final String[] roles;

    private Roles(final String[] roles){
        this.roles = roles;
    }


    public static Roles of(final String[] roles){
        
        
        if(roles == null || roles.length == 0){

            throw new IllegalArgumentException("Nill or empty roles is not allowed");

        }else {

            for(int i=0;i<roles.length;i++){

                if(EmptyUtil.isEmpty(roles[i])){

                    throw new IllegalArgumentException("empty role or roles is not allowed");
                    
            }

        }
    }

        return new Roles(roles);
    }

    public String[] getRoles(){
        return roles;
    }
    
}
