package com.ilvo.neo4jdemo.core.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.ilvo.neo4jdemo.core.nodes.Person;
import com.ilvo.neo4jdemo.core.nodes.properties.Name;


public class TransferUitlTest {

    @Test
    public void should_work_for_persons_to_names(){
        List<Person> people = new ArrayList<>();
        Person p1 = new Person("foo",2002);
        Person p2 = new Person("bar",2003);
        people.add(p1);
        people.add(p2);

        List<Name> names= new ArrayList<>();
        Name n1 = Name.of("foo");
        Name n2 = Name.of("bar");
        names.add(n1);
        names.add(n2);

        List<Name> names_checked = TransferUtil.personsToNames(people);
           
        assertThat(EquivalentUtil.doesEquivalent(names_checked, names)).isTrue();
        
        names.remove(n2);
        Name n3 = Name.of("bae");
        names.add(n3);

        assertThat(EquivalentUtil.doesEquivalent(names_checked, names)).isFalse();

    }

    @Test
    public void should_throw_exception_for_null_parameter_in_persons_to_names(){

    assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> TransferUtil.personsToNames(null));
    
    List<Person> emptyList = new ArrayList<>();
    assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> TransferUtil.personsToNames(emptyList));
    }
    
}
