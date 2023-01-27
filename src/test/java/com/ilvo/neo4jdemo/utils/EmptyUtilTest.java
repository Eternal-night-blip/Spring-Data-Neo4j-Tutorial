package com.ilvo.neo4jdemo.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class EmptyUtilTest {

    @Test
    public void should_work_for_str(){

        assertThat(EmptyUtil.isEmpty("foo")).isFalse();
        String str = null;
        assertThat(EmptyUtil.isEmpty(str)).isTrue();
        assertThat(EmptyUtil.isEmpty("")).isTrue();
        assertThat(EmptyUtil.isEmpty("   ")).isTrue();
    }

    @Test
    public void shoud_work_for_list(){

        List<Integer> emptyList = null;
        assertThat(EmptyUtil.isEmpty(emptyList)).isTrue();
        
        List<Integer> emptyIntegerList = new ArrayList<>() {};
        assertThat(EmptyUtil.isEmpty(emptyIntegerList)).isTrue();
        
        emptyIntegerList.add(1);
        assertThat(EmptyUtil.isEmpty(emptyIntegerList)).isFalse();

        List<String> stringList = new ArrayList<>();
        stringList.add("");
        assertThat(EmptyUtil.isEmpty(stringList)).isFalse();
    }
    
}
