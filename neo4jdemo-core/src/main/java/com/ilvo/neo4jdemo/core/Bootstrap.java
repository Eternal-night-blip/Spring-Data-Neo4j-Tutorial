package com.ilvo.neo4jdemo.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages={"com.ilvo.neo4jdemo.core"})
@EnableNeo4jRepositories
@EnableTransactionManagement
public class Bootstrap {
    public static void main(String[] args){
		SpringApplication.run(Bootstrap.class, args);
	}
}
