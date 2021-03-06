package com.weddings_planner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import app.builder.RepositoryServiceBuilder;

@SpringBootApplication
public class WeddingPlannerMain {
	public static void main(String[] args) {
		RepositoryServiceBuilder.build();
		SpringApplication.run(WeddingPlannerMain.class, args);
	}
}