package app.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "visitations")
public class Visitation implements Serializable {

    private Long visitationId;
    private Date visitationDate;
    private String comments;
    private Patient patient;
    private Doctor doctor;

    public Visitation() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visitation_id")
    public Long getVisitationId() {
        return visitationId;
    }

    public void setVisitationId(Long visitationId) {
        this.visitationId = visitationId;
    }

    public Date getVisitationDate() {
        return visitationDate;
    }

    public void setVisitationDate(Date visitationDate) {
        this.visitationDate = visitationDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "patient_id")
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id")
    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
