package app;

import app.builder.RepositoryServiceBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShampooCompanyApplication {

    public static void main(String[] args) {
        RepositoryServiceBuilder.build();
        SpringApplication.run(ShampooCompanyApplication.class, args);
    }
}
