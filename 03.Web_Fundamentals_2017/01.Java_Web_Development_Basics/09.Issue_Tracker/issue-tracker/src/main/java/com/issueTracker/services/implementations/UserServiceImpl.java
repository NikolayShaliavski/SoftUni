package com.issueTracker.services.implementations;

import com.issueTracker.entities.user.Role;
import com.issueTracker.entities.user.User;
import com.issueTracker.models.bindingModels.user.LoginUserModel;
import com.issueTracker.models.bindingModels.user.RegisterUserModel;
import com.issueTracker.models.viewModels.user.LoggedUserModel;
import com.issueTracker.repositories.UserRepository;
import com.issueTracker.services.UserService;
import com.issueTracker.utils.parser.interfaces.ModelParser;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserServiceImpl implements UserService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private ModelParser modelParser;

    @Override
    public void register(RegisterUserModel registerUserModel) {
        User user = this.modelParser.convert(registerUserModel, User.class);
        long usersCount = this.userRepository.getUsersCount();
        Role role = Role.USER;
        if (usersCount == 0) {
            role = Role.ADMIN;
        }
        user.setRole(role);
        this.userRepository.register(user);
    }

    @Override
    public LoggedUserModel findLoggedUser(LoginUserModel loginUserModel) {
        User user = this.userRepository.findByUsernameAndPassword(
                loginUserModel.getUsername(), loginUserModel.getPassword());
        LoggedUserModel userView = null;
        if (user != null) {
            userView = this.modelParser.convert(user, LoggedUserModel.class);
        }
        return userView;
    }
}
