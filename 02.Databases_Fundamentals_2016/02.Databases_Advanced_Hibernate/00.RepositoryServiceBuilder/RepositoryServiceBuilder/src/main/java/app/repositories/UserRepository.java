package app.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import app.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}