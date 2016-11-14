package app.services.servicesImpl;

import app.entities.Diagnose;
import org.springframework.beans.factory.annotation.Autowired;
import app.repositories.DiagnoseRepository;
import app.services.DiagnoseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DiagnoseServiceImpl implements DiagnoseService {

    @Autowired
    private DiagnoseRepository diagnoseRepository;

    @Override
    public void registerDiagnose(Diagnose diagnose) {
        this.diagnoseRepository.save(diagnose);
    }
}
