package com.ilvo.neo4jdemo.relationships.properties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.Test;

public class RolesTest {

    @Test
    public void should_creat_roles(){
        String[] r = {"foo","bar"};
        Roles roles = Roles.of(r);
        assertThat(roles.getRoles()[0].equals(r[0]));
        assertThat(roles.getRoles()[1].equals(r[1]));
    }

    @Test
    public void should_not_create_roles(){

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> Roles.of(null));
        
        String[] empty = {};
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> Roles.of(empty));

        String[] emptyRoles = {"",""};
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> Roles.of(emptyRoles));  

        

    }


    
}
