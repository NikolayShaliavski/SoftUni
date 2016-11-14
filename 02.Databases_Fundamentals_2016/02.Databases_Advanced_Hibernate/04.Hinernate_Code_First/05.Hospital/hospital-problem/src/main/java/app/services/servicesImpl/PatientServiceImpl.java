package app.services.servicesImpl;

import app.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import app.repositories.PatientRepository;
import app.services.PatientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public void registerPatient(Patient patient) {
        this.patientRepository.save(patient);
    }

    @Override
    public Patient retrieve(Long id) {
        return this.patientRepository.findOne(id);
    }
}
