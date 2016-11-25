package app.services.servicesImpl;

import app.domain.shampoos.BasicShampoo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import app.repositories.BasicShampooRepository;
import app.services.BasicShampooService;

@Service
@Transactional
public class BasicShampooServiceImpl implements BasicShampooService {

    @Autowired
    private BasicShampooRepository basicShampooRepository;

    @Override
    public void create(BasicShampoo basicShampoo) {
        this.basicShampooRepository.saveAndFlush(basicShampoo);
    }
}