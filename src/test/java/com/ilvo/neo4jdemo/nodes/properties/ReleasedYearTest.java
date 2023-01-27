package com.ilvo.neo4jdemo.nodes.properties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Random;

import org.junit.jupiter.api.Test;

public class ReleasedYearTest {

    @Test
    public void should_create_released_year(){
        ReleasedYear releasedYear = ReleasedYear.of(2000);
        assertThat(releasedYear.get()).isEqualTo(2000);
    }

    @Test
    public void should_not_create_released_year(){

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> ReleasedYear.of(null));
        
        Random rand = new Random();
        int errorYear = rand.nextInt(0, 1895);
        
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> ReleasedYear.of(errorYear));      

    }
    
}
