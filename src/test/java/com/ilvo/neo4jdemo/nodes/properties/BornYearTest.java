package com.ilvo.neo4jdemo.nodes.properties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Random;

import org.junit.jupiter.api.Test;

public class BornYearTest {
    
    @Test
    public void should_create_born_year(){
        BornYear bornYear = BornYear.of(2003);
        assertThat(bornYear.get()).isEqualTo(2003);
    }

    @Test
    public void should_not_create_born_year(){

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> BornYear.of(null));
        
        Random rand = new Random();
        int errorYear = rand.nextInt(0, 1864);
        
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> BornYear.of(errorYear));      

    }
    
}
