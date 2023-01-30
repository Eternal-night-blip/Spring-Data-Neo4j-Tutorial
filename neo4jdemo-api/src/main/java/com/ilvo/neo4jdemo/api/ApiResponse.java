package com.ilvo.neo4jdemo.api;

import java.util.HashMap;

public record  ApiResponse(Object data, Integer code, String errorMessage) {
  
    public static ApiResponse ok() {
    return new ApiResponse(new HashMap<>(),200, "");
    }
        
    public static ApiResponse ok(Object data) {
    return new ApiResponse(data,200, "");
    }
        
    public static ApiResponse error(Integer code,String errorMsg) {
    return new ApiResponse(new HashMap<>(),code, errorMsg);
    }
}

