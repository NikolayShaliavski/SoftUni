package photography.domain.entities.workshhops;

import photography.domain.entities.photographers.Photographer;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "workshops")
public class Workshop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "price_per_participant", nullable = false)
    private BigDecimal pricePerParticipant;

    @ManyToOne
    @JoinColumn(name = "trainer", referencedColumnName = "id")
    private Photographer trainer;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "workshops_photographers",
    joinColumns = @JoinColumn(name = "workshop_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "photographer_id", referencedColumnName = "id"))
    private Set<Photographer> participants;

    public Workshop() {
        this.setParticipants(new HashSet<>());
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
        this.name = name;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getPricePerParticipant() {
        return this.pricePerParticipant;
    }

    public void setPricePerParticipant(BigDecimal pricePerParticipant) {
        this.pricePerParticipant = pricePerParticipant;
    }

    public Photographer getTrainer() {
        return this.trainer;
    }

    public void setTrainer(Photographer trainer) {
        this.trainer = trainer;
    }

    public Set<Photographer> getParticipants() {
        return this.participants;
    }

    public void setParticipants(Set<Photographer> participants) {
        this.participants = participants;
    }

    public void addParticipant(Photographer participant) {
        this.getParticipants().add(participant);
    }
}
