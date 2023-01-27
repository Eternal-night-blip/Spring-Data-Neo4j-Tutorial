package com.ilvo.neo4jdemo.nodes.properties;

import java.util.Calendar;

public final class ReleasedYear {

    private final Integer year;

    private ReleasedYear(final Integer year){
        this.year = year;
    }

    public static ReleasedYear of(final Integer year){
        
        if(year == null){
            throw new IllegalArgumentException("null released year is not allowed");
        }
        // the first movie in the world
        int released_year_of_Lunch_Hour_at_the_Lumiere_Factory  = 1895;

        if(year < released_year_of_Lunch_Hour_at_the_Lumiere_Factory){
            throw new IllegalArgumentException("the released year of movie can't be less than "+released_year_of_Lunch_Hour_at_the_Lumiere_Factory);
        }

        Calendar calendar  = Calendar.getInstance();
        int thisYear = calendar.get(Calendar.YEAR); 

        if(year>thisYear){
            throw new IllegalArgumentException("the year of movie released can't exceed this year "+thisYear);
        }

        return new ReleasedYear(year);
    }

    public Integer get(){
        return year;
    } 
    
}
