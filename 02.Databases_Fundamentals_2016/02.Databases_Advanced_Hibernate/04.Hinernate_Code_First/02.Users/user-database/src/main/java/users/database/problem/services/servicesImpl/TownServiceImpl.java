package users.database.problem.services.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import users.database.problem.entities.Town;
import users.database.problem.repositories.TownRepository;
import users.database.problem.services.TownService;

@Service
public class TownServiceImpl implements TownService {

    @Autowired
    private TownRepository townRepository;

    @Override
    public void register(Town town) {
        this.townRepository.saveAndFlush(town);
    }
}
