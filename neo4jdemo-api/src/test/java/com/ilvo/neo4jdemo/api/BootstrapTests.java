package com.ilvo.neo4jdemo.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(classes =Bootstrap.class,webEnvironment = WebEnvironment.NONE)
class BootstrapTests {

	@Test
	void contextLoads() {
	}

}
