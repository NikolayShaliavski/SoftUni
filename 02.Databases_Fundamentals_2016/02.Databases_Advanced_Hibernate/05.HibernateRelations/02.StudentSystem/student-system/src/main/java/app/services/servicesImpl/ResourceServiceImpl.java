package app.services.servicesImpl;

import app.domain.Resource;
import app.repositories.ResourceRepository;
import app.services.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    @Override
    public void create(Resource resource) {
        this.resourceRepository.saveAndFlush(resource);
    }
}