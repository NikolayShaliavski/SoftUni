package massdefect.app.repositories;

import massdefect.app.domain.entities.planets.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanetRepository extends JpaRepository<Planet,Long> {

    Planet findByName(String name);

    @Query(value = "SELECT pl FROM Anomaly AS a " +
            "RIGHT OUTER JOIN a.originPlanet AS pl " +
            "WHERE a.originPlanet = NULL")
    List<Planet> findPlanetsWithoutTeleports();
}