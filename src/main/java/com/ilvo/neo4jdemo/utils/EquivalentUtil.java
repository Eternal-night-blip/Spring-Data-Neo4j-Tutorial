package com.ilvo.neo4jdemo.utils;

import java.util.Iterator;
import java.util.List;

import com.ilvo.neo4jdemo.nodes.properties.Name;

public final class EquivalentUtil {
    
    private EquivalentUtil(){}

    public static boolean doesEquivalent(List<Name> people,List<Name> another_people){
        
        if (EmptyUtil.isEmpty(people) || EmptyUtil.isEmpty(another_people)){
            throw new IllegalArgumentException("Null or empty people/another_people is not allowed");
        }

        int size_people = people.size();
        int size_another_people = another_people.size();
        if(size_people != size_another_people){
            return false;
        }

        for(Name person : people){

            Iterator<Name> iterator = another_people.iterator();
            int now_index_iterator = 0;

            while(iterator.hasNext()){

                now_index_iterator ++;
                Name person_checked = iterator.next();
                if(person.get().equals(person_checked.get())){
                    break;
                }
                
                if(now_index_iterator == size_another_people){
                    return false;
                }
            }   

        }
        
        return true;

    }
    
}
