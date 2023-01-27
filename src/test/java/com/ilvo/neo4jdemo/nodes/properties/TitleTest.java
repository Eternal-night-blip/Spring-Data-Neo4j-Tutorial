package com.ilvo.neo4jdemo.nodes.properties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.Test;

public class TitleTest {

    @Test
    public void should_creat_tag_line(){

        Title title = Title.of("foo");
        assertThat(title.get()).isEqualTo("foo");
    }

    @Test
    public void should_not_create_tag_line(){

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> Title.of(null));
        
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> Title.of(""));  

    }
    
}
