package massdefect.app.domain.entities.persons;

import com.google.gson.annotations.Expose;
import massdefect.app.domain.entities.anomalies.Anomaly;
import massdefect.app.domain.entities.planets.Planet;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "persons")
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "home_planet_id", referencedColumnName = "id")
    private Planet homePlanet;

//    @ManyToMany(mappedBy = "victims", targetEntity = Anomaly.class,
//    cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private Set<Anomaly> anomalies;

    public Person() {
        //this.setAnomalies(new HashSet<>());
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

    public Planet getHomePlanet() {
        return this.homePlanet;
    }

    public void setHomePlanet(Planet homePlanet) {
        if (homePlanet == null) {
            throw new IllegalArgumentException("Error: Invalid data.");
        }
        this.homePlanet = homePlanet;
    }

//    public Set<Anomaly> getAnomalies() {
//        return Collections.unmodifiableSet(this.anomalies);
//    }
//
//    public void setAnomalies(Set<Anomaly> anomalies) {
//        this.anomalies = anomalies;
//    }
}
