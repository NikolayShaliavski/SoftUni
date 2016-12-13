package photography.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import photography.domain.entities.photographers.Photographer;

import java.util.List;

@Repository
public interface PhotographerRepository extends JpaRepository<Photographer,Long> {

    @Query(value = "SELECT ph FROM Photographer AS ph " +
            "ORDER BY ph.firstName ASC, ph.lastName DESC")
    List<Photographer> findAllPhotographs();

    Photographer findByFirstNameAndLastName(String firstName, String lastName);

    @Query(value = "SELECT * FROM photographers AS ph " +
            "INNER JOIN cameras AS c " +
            "ON ph.primary_camera = c.id " +
            "WHERE c.type = 'DSLR' ", nativeQuery = true)
    List<Photographer> findLandscapePhotographers();

    @Query(value = "SELECT ph FROM Photographer AS ph " +
            "WHERE ph.primaryCamera.make = ph.secondaryCamera.make ")
    List<Photographer> findAllPhotographersWithSameCameras();
}