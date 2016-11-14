package app.repositories;

import app.entities.Visitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitationRepository extends JpaRepository<Visitation, Long> {
}
