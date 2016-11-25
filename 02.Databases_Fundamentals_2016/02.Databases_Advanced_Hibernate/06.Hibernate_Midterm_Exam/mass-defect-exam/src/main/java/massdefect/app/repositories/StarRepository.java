package massdefect.app.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import massdefect.app.domain.entities.stars.Star;

@Repository
public interface StarRepository extends JpaRepository<Star,Long> {

    Star findByName(String name);
}