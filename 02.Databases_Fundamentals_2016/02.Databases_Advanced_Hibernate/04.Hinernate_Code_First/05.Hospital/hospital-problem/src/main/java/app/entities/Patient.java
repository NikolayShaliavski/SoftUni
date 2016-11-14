package app.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "patients")
public class Patient implements Serializable {

    private Long patientId;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private Date dateOfBirth;
    private byte[] picture;
    private Boolean hasInsurance;
    //private Set<Visitation> visitations;
    private Set<Diagnose> diagnoses;
    private Set<Medicament> medicaments;

    public Patient() {
        this.setDiagnoses(new HashSet<>());
        this.setMedicaments(new HashSet<>());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Column(columnDefinition = "LONGBLOB")
    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public Boolean getHasInsurance() {
        return hasInsurance;
    }

    public void setHasInsurance(Boolean hasInsurance) {
        this.hasInsurance = hasInsurance;
    }

//    @OneToMany()
//    public Set<Visitation> getVisitations() {
//        return Collections.unmodifiableSet(this.visitations);
//    }

//    public void setVisitations(Set<Visitation> visitations) {
//        this.visitations = visitations;
//    }

    @ManyToMany
    @JoinTable(name = "patients_diagnoses",
    joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "patient_id"),
    inverseJoinColumns = @JoinColumn(name = "diagnose_id", referencedColumnName = "diagnose_id"))
    public Set<Diagnose> getDiagnoses() {
        return Collections.unmodifiableSet(this.diagnoses);
    }

    public void setDiagnoses(Set<Diagnose> diagnoses) {
        this.diagnoses = diagnoses;
    }

    @ManyToMany
    @JoinTable(name = "patients_medicaments",
    joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "patient_id"),
    inverseJoinColumns = @JoinColumn(name = "medicament_id", referencedColumnName = "medicament_id"))
    public Set<Medicament> getMedicaments() {
        return Collections.unmodifiableSet(this.medicaments);
    }

    public void setMedicaments(Set<Medicament> medicaments) {
        this.medicaments = medicaments;
    }

//    public void addVisitation(Visitation visitation) {
//        this.visitations.add(visitation);
//    }

    public void addDiagnose(Diagnose diagnose) {
        this.diagnoses.add(diagnose);
    }

    public void addMedicament(Medicament medicament) {
        this.medicaments.add(medicament);
    }
}
