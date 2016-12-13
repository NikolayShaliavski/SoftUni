package photography.domain.entities.cameras;

import photography.domain.entities.photographers.Photographer;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Set;

@Entity
@Table(name = "cameras")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class BasicCamera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "make", nullable = false)
    private String make;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "is_full_name_or_not")
    private Boolean isFullNameOrNot;

    @Column(name = "min_iso", nullable = false)
    @Min(100)
    private Integer minISO;

    @Column(name = "max_iso")
    private Integer maxISO;

    public BasicCamera() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return this.make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Boolean getFullNameOrNot() {
        return this.isFullNameOrNot;
    }

    public void setFullNameOrNot(Boolean fullNameOrNot) {
        isFullNameOrNot = fullNameOrNot;
    }

    public Integer getMinISO() {
        return this.minISO;
    }

    public void setMinISO(Integer minISO) {
        this.minISO = minISO;
    }

    public Integer getMaxISO() {
        return this.maxISO;
    }

    public void setMaxISO(Integer maxISO) {
        this.maxISO = maxISO;
    }
}
