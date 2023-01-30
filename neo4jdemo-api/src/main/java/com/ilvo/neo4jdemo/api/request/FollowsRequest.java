package com.ilvo.neo4jdemo.api.request;

import com.fasterxml.jackson.annotation.JsonCreator;

public record FollowsRequest (String followerName,String masterName){

}
