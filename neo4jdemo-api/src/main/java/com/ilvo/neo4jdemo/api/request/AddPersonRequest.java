package com.ilvo.neo4jdemo.api.request;

import com.fasterxml.jackson.annotation.JsonCreator;

public record AddPersonRequest(String name,Integer bornYear){

}
