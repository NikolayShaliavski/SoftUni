package photography.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import photography.domain.entities.accessories.Accessory;

@Repository
public interface AccessoryRepository extends JpaRepository<Accessory,Long> {
}