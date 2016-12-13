package photography.domain.entities.photographers;

import org.springframework.context.annotation.Primary;
import photography.domain.entities.accessories.Accessory;
import photography.domain.entities.cameras.BasicCamera;
import photography.domain.entities.lenses.Lens;
import photography.domain.entities.workshhops.Workshop;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "photographers")
public class Photographer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @Size(min = 2, max = 50)
    private String lastName;

    @Basic
    private String phone;

    @ManyToOne
    @JoinColumn(name = "primary_camera", referencedColumnName = "id", nullable = false)
    private BasicCamera primaryCamera;

    @ManyToOne
    @JoinColumn(name = "secondary_camera", referencedColumnName = "id", nullable = false)
    private BasicCamera secondaryCamera;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private Set<Lens> lenses;

    @OneToMany(mappedBy = "owner")
    private Set<Accessory> accessories;

    public Photographer() {
        this.setLenses(new HashSet<>());
        this.setAccessories(new HashSet<>());
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BasicCamera getPrimaryCamera() {
        return this.primaryCamera;
    }

    public void setPrimaryCamera(BasicCamera primaryCamera) {
        this.primaryCamera = primaryCamera;
    }

    public BasicCamera getSecondaryCamera() {
        return this.secondaryCamera;
    }

    public void setSecondaryCamera(BasicCamera secondaryCamera) {
        this.secondaryCamera = secondaryCamera;
    }

    public Set<Lens> getLenses() {
        return this.lenses;
    }

    public void setLenses(Set<Lens> lenses) {
        this.lenses = lenses;
    }

    public Set<Accessory> getAccessories() {
        return this.accessories;
    }

    public void setAccessories(Set<Accessory> accessories) {
        this.accessories = accessories;
    }

    public void addLens(Lens lens) {
        this.getLenses().add(lens);
    }

    public void addAccessory(Accessory accessory) {
        this.getAccessories().add(accessory);
    }
}
