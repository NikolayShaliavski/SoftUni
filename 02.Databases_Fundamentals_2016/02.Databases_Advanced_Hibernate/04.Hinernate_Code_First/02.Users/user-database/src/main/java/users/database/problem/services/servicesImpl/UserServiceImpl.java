package users.database.problem.services.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import users.database.problem.entities.User;
import users.database.problem.repositories.UserRepository;
import users.database.problem.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void register(User user) {
        this.userRepository.saveAndFlush(user);
    }
}
