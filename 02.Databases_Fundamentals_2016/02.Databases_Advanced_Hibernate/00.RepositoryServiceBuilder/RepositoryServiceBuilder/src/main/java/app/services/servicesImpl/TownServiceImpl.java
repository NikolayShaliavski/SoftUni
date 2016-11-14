package app.services.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.entities.Town;
import app.repositories.TownRepository;
import app.services.TownService;

@Service
public class TownServiceImpl implements TownService {

    @Autowired
    private TownRepository townRepository;

    @Override
    public void save(Town town) {

        this.townRepository.saveAndFlush(town);
    }
}