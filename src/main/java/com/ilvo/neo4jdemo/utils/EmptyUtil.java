package com.ilvo.neo4jdemo.utils;

import java.util.List;

public final class EmptyUtil {

    public static boolean isEmpty(String str){

        if(str == null || str.length() == 0){
            return true;
        }
        return false;
    }

    public static <T> boolean isEmpty(List<T> list){

        if(list == null || list.size() == 0){
            return true;
        }
        return false;
    }
 
}
