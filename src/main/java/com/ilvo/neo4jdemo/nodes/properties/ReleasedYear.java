package com.ilvo.neo4jdemo.nodes.properties;

import java.util.Calendar;

public final class ReleasedYear {

    private final Integer releasedYear;

    private ReleasedYear(final Integer releasedYear){
        this.releasedYear = releasedYear;
    }

    public static ReleasedYear of(final Integer releasedYear){
        
        if(releasedYear == null){
            throw new IllegalArgumentException("null released year is not allowed");
        }
        // the first movie in the world
        int released_year_of_Lunch_Hour_at_the_Lumiere_Factory  = 1895;

        if(releasedYear < released_year_of_Lunch_Hour_at_the_Lumiere_Factory){
            throw new IllegalArgumentException("the released year of movie can't be less than "+released_year_of_Lunch_Hour_at_the_Lumiere_Factory);
        }

        Calendar calendar  = Calendar.getInstance();
        int thisYear = calendar.get(Calendar.YEAR); 

        if(releasedYear>thisYear){
            throw new IllegalArgumentException("the year of movie released can't exceed this year "+thisYear);
        }

        return new ReleasedYear(releasedYear);
    }

    public Integer getReleasedYear(){
        return releasedYear;
    } 
    
}
