package app.services;

import app.entities.Doctor;

public interface DoctorService {

    void registerDoctor(Doctor doctor);

    Doctor retrieve(Long id);
}
