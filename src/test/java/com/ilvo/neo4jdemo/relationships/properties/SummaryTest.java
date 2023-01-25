package com.ilvo.neo4jdemo.relationships.properties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.Test;

public class SummaryTest {

    @Test
    public void should_creat_summary(){

        Summary summary = Summary.of("foo");
        assertThat(summary.getSummary() == "foo");
    }

    @Test
    public void should_not_create_tag_line(){

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> Summary.of(null));
        
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> Summary.of(""));  

    }
    
}
