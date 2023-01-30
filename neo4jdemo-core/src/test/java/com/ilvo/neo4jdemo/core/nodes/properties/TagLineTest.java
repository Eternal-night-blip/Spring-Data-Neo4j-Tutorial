package com.ilvo.neo4jdemo.core.nodes.properties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.Test;

public class TagLineTest {

    @Test
    public void should_tag_line(){

        TagLine tagLine = TagLine.of("foo");
        assertThat(tagLine.get()).isEqualTo("foo");
    }

    @Test
    public void should_not_create_tag_line(){

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> TagLine.of(null));
        
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> TagLine.of(""));  

    }
    
}
