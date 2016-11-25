package app.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import app.domain.Homework;

@Repository
public interface HomeworkRepository extends JpaRepository<Homework,Long> {
}