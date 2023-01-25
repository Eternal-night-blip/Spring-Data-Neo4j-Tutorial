package com.ilvo.neo4jdemo.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.ilvo.neo4jdemo.nodes.properties.Name;

public class EquivalentUtilTest {
    
    @Test
    public void should_work_in_does_equivalent(){
        
        List<Name> people_checked = new ArrayList<>();
        people_checked.add(Name.of("Tom Hanks"));
        people_checked.add(Name.of("Ron Howard"));
        people_checked.add(Name.of("Ed Harris"));
        people_checked.add(Name.of("Gary Sinise"));
        people_checked.add(Name.of("Kevin Bacon"));
        people_checked.add(Name.of("Bill Paxton"));
        
        List<Name> people = new ArrayList<>();
        people.add(Name.of("Gary Sinise"));
        people.add(Name.of("Bill Paxton"));
        people.add(Name.of("Ed Harris"));
        people.add(Name.of("Tom Hanks"));
        people.add(Name.of("Kevin Bacon"));
        people.add(Name.of("Ron Howard"));
        
        assertThat(EquivalentUtil.doesEquivalent(people, people_checked));
        
        people.remove(Name.of("Ed Harris"));
        assertThat(!(EquivalentUtil.doesEquivalent(people, people_checked)));
    
    }
    
    @Test 
    void should_throw_exception_for_null_parameter_in_does_equivalent(){
        
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> EquivalentUtil.doesEquivalent(null, null));
        
        List<Name> emptyList1 = new ArrayList<>();
        List<Name> emptyList2 = new ArrayList<>();
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> EquivalentUtil.doesEquivalent(emptyList1, emptyList2));
    }
    
}
