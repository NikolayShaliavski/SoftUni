package app.entities;

import javax.persistence.*;

@Entity(name = "doctors")
public class Doctor {

    private Long doctor_id;
    private String name;
    private String speciality;
    //private Set<Visitation> visitations;

    public Doctor() {
        //this.setVisitations(new HashSet<>());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(Long doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(length = 100)
    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

//    @OneToMany(mappedBy = "doctor", targetEntity = Visitation.class,
//    fetch = FetchType.EAGER)
//    public Set<Visitation> getVisitations() {
//        return Collections.unmodifiableSet(this.visitations);
//    }
//
//    public void setVisitations(Set<Visitation> visitations) {
//        this.visitations = visitations;
//    }
//
//    public void addVisitation(Visitation visitation) {
//        this.visitations.add(visitation);
//    }
}
