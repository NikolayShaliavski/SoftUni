package app.services.servicesImpl;

import app.domain.labels.ClassicLabel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import app.repositories.ClassicLabelRepository;
import app.services.ClassicLabelService;

@Service
@Transactional
public class ClassicLabelServiceImpl implements ClassicLabelService {

    @Autowired
    private ClassicLabelRepository classicLabelRepository;

    @Override
    public void create(ClassicLabel classicLabel) {
        this.classicLabelRepository.saveAndFlush(classicLabel);
    }
}