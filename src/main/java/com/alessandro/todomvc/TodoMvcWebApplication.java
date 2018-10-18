package com.alessandro.todomvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class TodoMvcWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoMvcWebApplication.class, args);
	}
}
