package com.ilvo.neo4jdemo.api;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableNeo4jRepositories("com.ilvo.neo4jdemo.core.repository")
@EnableTransactionManagement
@SpringBootApplication
@ComponentScan(basePackages = {"com.ilvo.neo4jdemo.api","com.ilvo.neo4jdemo.core"})
public class Bootstrap {
	public static void main(String[] args){
		SpringApplication.run(Bootstrap.class, args);
	}

}
