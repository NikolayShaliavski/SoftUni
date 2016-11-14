package app.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import app.domain.labels.ClassicLabel;

@Repository
public interface ClassicLabelRepository extends JpaRepository<ClassicLabel,Long> {
}