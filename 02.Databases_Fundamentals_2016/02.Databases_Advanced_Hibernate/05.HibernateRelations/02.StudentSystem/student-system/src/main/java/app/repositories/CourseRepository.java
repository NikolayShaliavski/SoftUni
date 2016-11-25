package app.repositories;

import app.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

    //3.2
    @Query(value = "SELECT c FROM Course AS c " +
            "ORDER BY c.startDate ASC, c.endDate DESC")
    List<Course> getCoursesAndResources();

    //3.3
    @Query(value = "SELECT c.name, COUNT(r.name) FROM Course AS c " +
            "INNER JOIN c.resources AS r " +
            "GROUP BY c.courseId " +
            "HAVING COUNT(r.name) > 5 " +
            "ORDER BY COUNT(r.name) DESC, c.startDate ASC")
    List<Object[]> getCoursesMoreThanFiveRes();
}