package photography.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import photography.domain.entities.workshhops.Workshop;

import java.util.List;

@Repository
public interface WorkshopRepository extends JpaRepository<Workshop,Long> {

    @Query(value = "SELECT w, (w.pricePerParticipant * w.participants.size - " +
            "(w.pricePerParticipant * w.participants.size * 0.2)) FROM Workshop AS w " +
            "WHERE w.participants.size >= 5 ")
    List<Object[]> findWorkshopsByLocation();
}