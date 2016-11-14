package app.services.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import app.repositories.ProductionBatchRepository;
import app.services.ProductionBatchService;

@Service
@Transactional
public class ProductionBatchServiceImpl implements ProductionBatchService {

    @Autowired
    private ProductionBatchRepository productionBatchRepository;

}