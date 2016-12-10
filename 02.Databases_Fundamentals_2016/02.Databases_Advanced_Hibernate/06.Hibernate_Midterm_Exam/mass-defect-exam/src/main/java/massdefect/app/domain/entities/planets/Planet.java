package massdefect.app.domain.entities.planets;

import com.google.gson.annotations.Expose;
import massdefect.app.domain.entities.solarSystems.SolarSystem;
import massdefect.app.domain.entities.stars.Star;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "planets")
public class Planet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "sun_id", referencedColumnName = "id")
    private Star sun;

    @ManyToOne
    @JoinColumn(name = "solar_system_id", referencedColumnName = "id")
    private SolarSystem solarSystem;

    public Planet() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Error: Invalid data.");
        }
        this.name = name;
    }

    public Star getSun() {
        return this.sun;
    }

    public void setSun(Star sun) {
        if (sun == null) {
            throw new IllegalArgumentException("Error: Invalid data.");
        }
        this.sun = sun;
    }

    public SolarSystem getSolarSystem() {
        return this.solarSystem;
    }

    public void setSolarSystem(SolarSystem solarSystem) {
        if (solarSystem == null) {
            throw new IllegalArgumentException("Error: Invalid data.");
        }
        this.solarSystem = solarSystem;
    }
}
