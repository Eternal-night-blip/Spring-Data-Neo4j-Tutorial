package com.ilvo.neo4jdemo;


import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import org.springframework.shell.jline.PromptProvider;

@SpringBootApplication
@EnableNeo4jRepositories
public class Neo4jdemoApplication {
	public static void main(String[] args){
		SpringApplication.run(Neo4jdemoApplication.class, args);
	}
    
    @Bean
	public PromptProvider promptProvider() {
		return () -> new AttributedString("shell:>", AttributedStyle.DEFAULT.foreground(AttributedStyle.CYAN));
	}

}
