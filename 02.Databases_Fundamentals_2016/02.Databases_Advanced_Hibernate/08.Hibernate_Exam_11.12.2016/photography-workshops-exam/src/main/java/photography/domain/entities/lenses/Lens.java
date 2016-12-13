package photography.domain.entities.lenses;

import photography.domain.entities.photographers.Photographer;

import javax.persistence.*;

@Entity
@Table(name = "lenses")
public class Lens {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private String make;

    @Column(name = "focal_length")
    private Integer focalLength;

    @Column(name = "max_aperture", precision = 1)
    private Double maxAperture;

    @Column(name = "compatible_with")
    private String compatibleWith;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner", referencedColumnName = "id")
    private Photographer owner;

    public Lens() {
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

    public Integer getFocalLength() {
        return this.focalLength;
    }

    public void setFocalLength(Integer focalLength) {
        this.focalLength = focalLength;
    }

    public Double getMaxAperture() {
        return this.maxAperture;
    }

    public void setMaxAperture(Double maxAperture) {
        this.maxAperture = maxAperture;
    }

    public String getCompatibleWith() {
        return this.compatibleWith;
    }

    public void setCompatibleWith(String compatibleWith) {
        this.compatibleWith = compatibleWith;
    }

    public Photographer getOwner() {
        return this.owner;
    }

    public void setOwner(Photographer owner) {
        this.owner = owner;
    }

    //    •	Make
//•	Focal Length – integer number, represents the focal lentgth in milimeters
//•	Max Aperture – floating point number precise 1 digit after decimal point
//•	Compatible With – make of the camera that the lens is compatible with
//•	Owner – could be any photographer

}
