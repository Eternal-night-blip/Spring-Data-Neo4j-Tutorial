package com.ilvo.neo4jdemo.nodes.properties;

import java.util.Calendar;

public final class BornYear {

    private final Integer year;

    private BornYear(final Integer year){
        this.year = year;
    }

    public static BornYear of(final Integer year){
        // the director of the first movie Lunch Hour at the Lumiere Factory
        if(year == null){
            throw new IllegalArgumentException("null year of birth is not allowed");
        }
        
        int born_year_of_Louis_Lumiere = 1864;

        if(year < born_year_of_Louis_Lumiere){
            throw new IllegalArgumentException("the year of birth can't be less than "+born_year_of_Louis_Lumiere);
        }
        
        Calendar calendar  = Calendar.getInstance();
        int thisYear = calendar.get(Calendar.YEAR); 
        
        if(year>thisYear){
            throw new IllegalArgumentException("the year of birth can't exceed this year "+thisYear);
        }

        return new BornYear(year);
    }

    public Integer get(){
        return year;
    }
    
}
