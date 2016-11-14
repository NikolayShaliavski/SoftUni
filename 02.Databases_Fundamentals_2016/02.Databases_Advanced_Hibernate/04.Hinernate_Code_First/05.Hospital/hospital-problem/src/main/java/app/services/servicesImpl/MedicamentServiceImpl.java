package app.services.servicesImpl;

import app.entities.Medicament;
import org.springframework.beans.factory.annotation.Autowired;
import app.repositories.MedicamentRepository;
import app.services.MedicamentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MedicamentServiceImpl implements MedicamentService {

    @Autowired
    private MedicamentRepository medicamentRepository;

    @Override
    public void registerMedicament(Medicament medicament) {
        this.medicamentRepository.save(medicament);
    }
}
