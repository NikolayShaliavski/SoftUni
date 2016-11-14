package app.services.servicesImpl;

import app.entities.Doctor;
import app.repositories.DoctorRepository;
import app.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public void registerDoctor(Doctor doctor) {
        this.doctorRepository.save(doctor);
    }

    @Override
    public Doctor retrieve(Long id) {
        return this.doctorRepository.findOne(id);
    }
}
