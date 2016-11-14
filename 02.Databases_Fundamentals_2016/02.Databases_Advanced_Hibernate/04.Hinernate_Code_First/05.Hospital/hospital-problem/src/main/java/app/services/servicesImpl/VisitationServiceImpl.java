package app.services.servicesImpl;

import app.entities.Visitation;
import org.springframework.beans.factory.annotation.Autowired;
import app.repositories.VisitationRepository;
import app.services.VisitationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VisitationServiceImpl implements VisitationService {

    @Autowired
    private VisitationRepository visitationRepository;

    @Override
    public void registerVisitation(Visitation visitation) {
        this.visitationRepository.save(visitation);
    }
}
