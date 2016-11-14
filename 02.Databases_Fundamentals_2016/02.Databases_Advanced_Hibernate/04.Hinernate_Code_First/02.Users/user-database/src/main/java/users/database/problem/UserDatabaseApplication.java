package users.database.problem;

import builder.RepositoryServiceBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserDatabaseApplication {

	public static void main(String[] args) {
		RepositoryServiceBuilder.build();
		SpringApplication.run(UserDatabaseApplication.class, args);
	}
}
