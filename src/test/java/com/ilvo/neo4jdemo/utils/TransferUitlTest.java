package com.ilvo.neo4jdemo.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.ilvo.neo4jdemo.nodes.Person;
import com.ilvo.neo4jdemo.nodes.properties.Name;


public class TransferUitlTest {

    @Test
    public void should_work_for_persons_to_names(){
        List<Person> people = new ArrayList<>();
        people.add(new Person("foo",2002));
        people.add(new Person("bar",2003));

        List<Name> names= new ArrayList<>();
        names.add(Name.of("foo"));
        names.add(Name.of("bar"));

        List<Name> names_checked = TransferUtil.personsToNames(people);
           
        assertThat(EquivalentUtil.doesEquivalent(names_checked, names));
        
        names.remove(Name.of("bar"));
        names.add(Name.of("bae"));

        assertThat(!EquivalentUtil.doesEquivalent(names_checked, names));

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
