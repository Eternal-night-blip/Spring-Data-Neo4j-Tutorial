package com.ilvo.neo4jdemo.utils;

import java.util.ArrayList;
import java.util.List;

import com.ilvo.neo4jdemo.nodes.Person;
import com.ilvo.neo4jdemo.nodes.properties.Name;

public final class TransferUtil {
    
    private TransferUtil(){}
    
    public static List<Name> personsToNames(List<Person> people){
        
        if(EmptyUtil.isEmpty(people)){
            throw new IllegalArgumentException("Null or empty people is not allowed");
        }

        List<Name> people_names = new ArrayList<>();
        people.forEach(person-> people_names.add(Name.of(person.getName())));
        return people_names;
    }
    
}
