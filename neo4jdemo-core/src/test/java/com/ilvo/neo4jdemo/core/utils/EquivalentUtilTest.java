package com.ilvo.neo4jdemo.core.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.ilvo.neo4jdemo.core.nodes.properties.Name;

public class EquivalentUtilTest {
    
    @Test
    public void should_work_in_does_equivalent(){
        
        List<Name> people_checked = new ArrayList<>();
        Name p1 = Name.of("Tom Hanks");
        Name p2 = Name.of("Ron Howard");
        Name p3 = Name.of("Ed Harris");
        Name p4 = Name.of("Gary Sinise");
        Name p5 = Name.of("Kevin Bacon");
        Name p6 = Name.of("Bill Paxton");
        people_checked.add(p1);
        people_checked.add(p2);
        people_checked.add(p3);
        people_checked.add(p4);
        people_checked.add(p5);
        people_checked.add(p6);
        
        List<Name> people = new ArrayList<>();
        people.add(p4);
        people.add(p6);
        people.add(p3);
        people.add(p1);
        people.add(p5);
        people.add(p2);
        
        assertThat(EquivalentUtil.doesEquivalent(people, people_checked)).isTrue();
        
        people.remove(p3);
        assertThat(EquivalentUtil.doesEquivalent(people, people_checked)).isFalse();
    
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
