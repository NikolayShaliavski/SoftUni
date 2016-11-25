package massdefect.app.domain.entities.anomalies;

import com.google.gson.annotations.Expose;
import massdefect.app.domain.entities.persons.Person;
import massdefect.app.domain.entities.planets.Planet;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "anomalies")
public class Anomaly implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "origin_planet_id", referencedColumnName = "id")
    private Planet originPlanet;

    @ManyToOne
    @JoinColumn(name = "teleport_planet_id", referencedColumnName = "id")
    private Planet teleportPlanet;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "anomalies_victims",
    joinColumns = @JoinColumn(name = "anomaly_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id"))
    private Set<Person> victims;

    public Anomaly() {
        this.setVictims(new HashSet<>());
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Planet getOriginPlanet() {
        return this.originPlanet;
    }

    public void setOriginPlanet(Planet originPlanet) {
        if (originPlanet == null) {
            throw new IllegalArgumentException("Error: Invalid data.");
        }
        this.originPlanet = originPlanet;
    }

    public Planet getTeleportPlanet() {
        return this.teleportPlanet;
    }

    public void setTeleportPlanet(Planet teleportPlanet) {
        if (teleportPlanet == null) {
            throw new IllegalArgumentException("Error: Invalid data.");
        }
        this.teleportPlanet = teleportPlanet;
    }

    public Set<Person> getVictims() {
        return Collections.unmodifiableSet(this.victims);
    }

    public void setVictims(Set<Person> victims) {
        this.victims = victims;
    }

    public void addVictim(Person person) {
        this.victims.add(person);
    }
}
