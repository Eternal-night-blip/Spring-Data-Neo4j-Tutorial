package com.ilvo.neo4jdemo.core.relationships.properties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.Test;

public class RolesTest {

    @Test
    public void should_creat_roles(){
        String[] r = {"foo","bar"};
        Roles roles = Roles.of(r);
        assertThat(roles.get()[0]).isEqualTo(r[0]);
        assertThat(roles.get()[1]).isEqualTo(r[1]);
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
