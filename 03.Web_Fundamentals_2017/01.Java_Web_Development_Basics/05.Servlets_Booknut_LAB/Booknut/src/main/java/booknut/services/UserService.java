package booknut.services;

import booknut.models.bindingModels.LoginModel;

public interface UserService {

    void createUser(LoginModel loginModel);

    LoginModel findByUsernameAndPassword(String username, String password);
}
