package com.example.EmployeeAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.SpringVersion;

@SpringBootApplication
public class EmployeeApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeApiApplication.class, args);
		System.out.println("-----------------------------------------");
		System.out.println("Hello Chaymae");
		System.out.println("Welcome to Employee MySQL API");
        System.out.println("version: " + SpringVersion.getVersion());
	}

}
