package massdefect.app.repositories;

import massdefect.app.domain.entities.anomalies.Anomaly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnomalyRepository extends JpaRepository<Anomaly,Long> {

    @Query(value = "SELECT a FROM Anomaly AS a " +
            "INNER JOIN a.victims AS v " +
            "GROUP BY a.id " +
            "ORDER BY COUNT(v.id) DESC")
    List<Anomaly> finAnomaliesAffectedPeople();
}