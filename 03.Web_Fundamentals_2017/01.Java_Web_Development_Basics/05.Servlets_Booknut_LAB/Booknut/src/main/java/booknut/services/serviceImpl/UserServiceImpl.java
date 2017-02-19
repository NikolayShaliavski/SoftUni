package booknut.services.serviceImpl;

import booknut.entities.User;
import booknut.models.bindingModels.LoginModel;
import booknut.repositories.UserRepository;
import booknut.repositories.repositoriesImpl.UserRepositoryImpl;
import booknut.services.UserService;
import org.modelmapper.ModelMapper;

public class UserServiceImpl implements UserService {

    private static final UserRepository userRepository =
            new UserRepositoryImpl();
    private static final ModelMapper mapper = new ModelMapper();

    @Override
    public void createUser(LoginModel loginModel) {
        User user = mapper.map(loginModel, User.class);
        userRepository.createUser(user);
    }

    @Override
    public LoginModel findByUsernameAndPassword(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        LoginModel userModel = null;
        if (user != null) {
            userModel = mapper.map(user, LoginModel.class);
        }
        return userModel;
    }
}
