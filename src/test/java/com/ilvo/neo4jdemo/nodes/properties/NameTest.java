package com.ilvo.neo4jdemo.nodes.properties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    public void should_create_name(){

        Name name = Name.of("foo");
        assertThat(name.getName() == "foo");
    }

    @Test
    public void should_not_create_name(){

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> Name.of(null));
        
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> Name.of(""));  

    }
    
}