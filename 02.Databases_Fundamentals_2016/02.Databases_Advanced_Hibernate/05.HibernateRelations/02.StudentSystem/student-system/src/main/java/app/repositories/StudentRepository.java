package app.repositories;

import app.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    @Query(value = "SELECT s.name, h.content, h.contentType FROM Student AS s " +
            "INNER JOIN s.homeworks AS h")
    List<Object[]> getAllStudentsAndHomeworks();
}