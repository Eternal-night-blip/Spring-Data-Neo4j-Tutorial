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
    }

    @Test
    public void shoud_work_for_list(){

        List<Integer> emptyList = null;
        assertThat(EmptyUtil.isEmpty(emptyList)).isTrue();
        
        emptyList = new ArrayList<>() {};
        assertThat(EmptyUtil.isEmpty(emptyList)).isTrue();
        
        emptyList.add(1);
        assertThat(EmptyUtil.isEmpty(emptyList)).isFalse();
    }
    
}
