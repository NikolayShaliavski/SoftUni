package massdefect.app.domain.entities.solarSystems;

import com.google.gson.annotations.Expose;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "solar_systems")
public class SolarSystem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    public SolarSystem() {
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

    @Required
    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Error: Invalid data.");
        }
        this.name = name;
    }
}
