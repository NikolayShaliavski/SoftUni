package com.jsonexercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import repository.builder.lib.ANSRBuilder;
import repository.builder.lib.enums.BuilderStrategy;

@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        ANSRBuilder.build(BuilderStrategy.REPOSITORY_AND_SERVICES);
        SpringApplication.run(MainApplication.class, args);
    }
}
