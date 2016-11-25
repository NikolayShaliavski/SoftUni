package massdefect.app.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import massdefect.app.domain.entities.persons.Person;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {

    @Query(value = "SELECT v FROM Anomaly AS a " +
            "RIGHT OUTER JOIN a.victims AS v " +
            "INNER JOIN v.homePlanet AS pl " +
            "WHERE a.id = NULL ")
    List<Person> findPersonsNoVictims();

    Person findByName(String name);
}