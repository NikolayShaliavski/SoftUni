package com.softuni.store.services;

import com.softuni.store.models.bindingModels.UserLoginModel;
import com.softuni.store.models.bindingModels.UserRegisterModel;
import com.softuni.store.models.viewModels.LoggedUserView;

public interface UserService {

    void register(UserRegisterModel userModel);

    void delete(Long id);

    LoggedUserView findByEmailAndPassword(UserLoginModel userLoginModel);
}
