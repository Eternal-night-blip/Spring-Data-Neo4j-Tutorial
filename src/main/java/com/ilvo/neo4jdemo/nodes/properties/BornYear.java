package com.ilvo.neo4jdemo.nodes.properties;

import java.util.Calendar;

public final class BornYear {

    private final Integer bornYear;

    private BornYear(final Integer bornYear){
        this.bornYear = bornYear;
    }

    public static BornYear of(final Integer bornYear){
        // the director of the first movie Lunch Hour at the Lumiere Factory
        if(bornYear == null){
            throw new IllegalArgumentException("null year of birth is not allowed");
        }
        
        int born_year_of_Louis_Lumiere = 1864;

        if(bornYear < born_year_of_Louis_Lumiere){
            throw new IllegalArgumentException("the year of birth can't be less than "+born_year_of_Louis_Lumiere);
        }
        
        Calendar calendar  = Calendar.getInstance();
        int thisYear = calendar.get(Calendar.YEAR); 
        
        if(bornYear>thisYear){
            throw new IllegalArgumentException("the year of birth can't exceed this year "+thisYear);
        }

        return new BornYear(bornYear);
    }

    public Integer getBornYear(){
        return bornYear;
    }
    
}
