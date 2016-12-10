package massdefect.app.domain.entities.solarSystems;

import massdefect.app.domain.entities.planets.Planet;
import massdefect.app.domain.entities.stars.Star;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "solar_systems")
public class SolarSystem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "solarSystem")
    private Set<Star> stars;

    @OneToMany(mappedBy = "solarSystem")
    private Set<Planet> planets;

    public SolarSystem() {
        this.setStars(new HashSet<>());
        this.setPlanets(new HashSet<>());
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

    public Set<Star> getStars() {
        return this.stars;
    }

    public void setStars(Set<Star> stars) {
        this.stars = stars;
    }

    public Set<Planet> getPlanets() {
        return this.planets;
    }

    public void setPlanets(Set<Planet> planets) {
        this.planets = planets;
    }
}
