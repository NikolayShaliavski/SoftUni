package users.database.problem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import users.database.problem.entities.Town;

@Repository
public interface TownRepository extends JpaRepository<Town, Long> {
}
