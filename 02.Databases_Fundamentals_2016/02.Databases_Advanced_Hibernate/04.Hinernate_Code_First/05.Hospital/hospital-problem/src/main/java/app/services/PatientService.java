package app.services;

import app.entities.Patient;

public interface PatientService {

    void registerPatient(Patient patient);

    Patient retrieve(Long id);
}
