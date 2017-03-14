package com.softuni.store.services.implementations;

import com.softuni.store.entities.user.Role;
import com.softuni.store.entities.user.User;
import com.softuni.store.models.bindingModels.UserLoginModel;
import com.softuni.store.models.bindingModels.UserRegisterModel;
import com.softuni.store.models.viewModels.LoggedUserView;
import com.softuni.store.repositories.UserRepository;
import com.softuni.store.services.UserService;
import com.softuni.store.utils.parser.interfaces.ModelParser;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserServiceImpl implements UserService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private ModelParser modelParser;

    @Override
    public void register(UserRegisterModel userModel) {
        User user = this.modelParser.convert(userModel, User.class);
        Long usersCount = this.userRepository.getUsersCount();
        Role role = Role.USER;
        if (usersCount == 0) {
            role = Role.ADMIN;
        }
        user.setRole(role);
        this.userRepository.register(user);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public LoggedUserView findByEmailAndPassword(UserLoginModel userLoginModel) {
        String email = userLoginModel.getEmail();
        String password = userLoginModel.getPassword();
        User user = this.userRepository.findByEmailAndPassword(email, password);
        LoggedUserView loggedUser = null;
        if (user != null) {
            loggedUser = this.modelParser.convert(user, LoggedUserView.class);
        }
        return loggedUser;
    }
}
