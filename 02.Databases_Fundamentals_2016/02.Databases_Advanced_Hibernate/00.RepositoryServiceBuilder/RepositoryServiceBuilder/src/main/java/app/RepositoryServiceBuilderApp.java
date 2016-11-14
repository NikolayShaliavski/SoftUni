package app;

import app.builder.RepositoryServiceBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RepositoryServiceBuilderApp {

    public static void main(String[] args) {
        RepositoryServiceBuilder.build();
    }
}
