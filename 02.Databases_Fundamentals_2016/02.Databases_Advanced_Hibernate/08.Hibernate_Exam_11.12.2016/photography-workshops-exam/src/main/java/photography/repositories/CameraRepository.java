package photography.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import photography.domain.entities.cameras.BasicCamera;

@Repository
public interface CameraRepository extends JpaRepository<BasicCamera,Long> {
}