package app.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "diagnoses")
public class Diagnose implements Serializable {

    private Long diagnoseId;
    private String name;
    private String comments;
    private Set<Patient> patients;

    public Diagnose() {
        this.setPatients(new HashSet<>());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diagnose_id")
    public Long getDiagnoseId() {
        return diagnoseId;
    }

    public void setDiagnoseId(Long diagnoseId) {
        this.diagnoseId = diagnoseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @ManyToMany(mappedBy = "diagnoses", targetEntity = Patient.class)
    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }

    public void addPatient(Patient patient) {
        this.getPatients().add(patient);
    }
}
