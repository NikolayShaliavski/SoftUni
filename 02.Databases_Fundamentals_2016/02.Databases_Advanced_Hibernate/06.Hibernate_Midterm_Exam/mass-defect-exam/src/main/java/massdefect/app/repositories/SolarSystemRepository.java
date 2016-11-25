package massdefect.app.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import massdefect.app.domain.entities.solarSystems.SolarSystem;

@Repository
public interface SolarSystemRepository extends JpaRepository<SolarSystem,Long> {

    SolarSystem findByName(String name);
}