package com.softuni.store.services;

import com.softuni.store.models.bindingModels.UserLoginModel;
import com.softuni.store.models.bindingModels.UserRegisterModel;
import com.softuni.store.models.viewModels.GameView;
import com.softuni.store.models.viewModels.LoggedUserView;

public interface UserService {

    void register(UserRegisterModel userModel);

    LoggedUserView findByEmailAndPassword(UserLoginModel userLoginModel);

    void orderGames(LoggedUserView loggedUser);

    void saveCart(LoggedUserView loggedUser);

    void removeFromCart(GameView gameToRemove, Long id);
}
