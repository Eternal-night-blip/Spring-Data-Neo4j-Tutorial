package com.ilvo.neo4jdemo.core.relationships.properties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Random;

import org.junit.jupiter.api.Test;

public class RatingTest {

    @Test
    public void should_create_rating(){
        Rating rating = Rating.of(18);
        assertThat(rating.get()).isSameAs(18);
    }

    @Test
    public void should_not_create_rating(){

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> Rating.of(null));
        
        Random rand = new Random();
        int exceedRatring = rand.nextInt(100,200);
        
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> Rating.of(exceedRatring));
        
        int lessRating = -1;
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> Rating.of(lessRating));
                  

    }
    
}
