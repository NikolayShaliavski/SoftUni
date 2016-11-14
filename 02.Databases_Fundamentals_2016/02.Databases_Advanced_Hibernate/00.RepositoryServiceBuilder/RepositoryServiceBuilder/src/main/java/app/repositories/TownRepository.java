package app.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import app.entities.Town;

@Repository
public interface TownRepository extends JpaRepository<Town,Long> {
}