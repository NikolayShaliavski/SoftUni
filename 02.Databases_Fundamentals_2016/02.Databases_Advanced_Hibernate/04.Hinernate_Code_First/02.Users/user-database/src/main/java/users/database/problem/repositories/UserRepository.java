package users.database.problem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import users.database.problem.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
