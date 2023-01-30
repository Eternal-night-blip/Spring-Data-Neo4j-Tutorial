package com.ilvo.neo4jdemo.core.utils;

import java.util.List;

public final class EmptyUtil {
    
    private EmptyUtil(){}

    public static boolean isEmpty(String str){

        return str == null || str.isEmpty() || str.trim().isEmpty();
    }

    public static <T> boolean isEmpty(List<T> list){
        
        return list == null || list.isEmpty();
    }
               
}
