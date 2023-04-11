package com.java.devops3.devops3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableZipkinServer
public class Devops3Application {

	public static void main(String[] args) {
		SpringApplication.run(Devops3Application.class, args);
	}

}
