package com.softuni.store.services.implementations;

import com.softuni.store.entities.game.Game;
import com.softuni.store.entities.user.Role;
import com.softuni.store.entities.user.User;
import com.softuni.store.models.bindingModels.UserLoginModel;
import com.softuni.store.models.bindingModels.UserRegisterModel;
import com.softuni.store.models.viewModels.GameView;
import com.softuni.store.models.viewModels.LoggedUserView;
import com.softuni.store.repositories.GameRepository;
import com.softuni.store.repositories.UserRepository;
import com.softuni.store.services.UserService;
import com.softuni.store.utils.parser.interfaces.ModelParser;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.LinkedHashSet;
import java.util.Set;

@Stateless
public class UserServiceImpl implements UserService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private GameRepository gameRepository;

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
    public LoggedUserView findByEmailAndPassword(UserLoginModel userLoginModel) {
        String email = userLoginModel.getEmail();
        String password = userLoginModel.getPassword();
        User user = this.userRepository.findByEmailAndPassword(email, password);
        LoggedUserView loggedUser = null;
        if (user != null) {
            loggedUser = this.modelParser.convert(user, LoggedUserView.class);
            if (loggedUser.getGames() != null) {
                for (GameView gameView : loggedUser.getGames()) {
                    String description = gameView.getDescription();
                    if (description != null && description.length() > 300) {
                        gameView.setDescription(description.substring(0, 300) + " ...");
                    }
                }
            }
            if (loggedUser.getCart() != null) {
                for (GameView gameView : loggedUser.getCart()) {
                    String description = gameView.getDescription();
                    if (description != null && description.length() > 300) {
                        gameView.setDescription(description.substring(0, 300) + " ...");
                    }
                }
            }
        }
        return loggedUser;
    }

    @Override
    public void orderGames(LoggedUserView loggedUser) {
        Set<GameView> cart = loggedUser.getCart();
        User user = this.userRepository.findById(loggedUser.getId());
        if (user != null) {
            Set<Game> userGames = user.getGames();
            if (userGames == null) {
                userGames = new LinkedHashSet<>();
            }
            for (GameView gameCartView : cart) {
                Game game = this.gameRepository.findById(gameCartView.getId());
                userGames.add(game);
            }
            user.setCart(new LinkedHashSet<>());
        }
    }

    @Override
    public void saveCart(LoggedUserView loggedUser) {
        Set<GameView> cart = loggedUser.getCart();
        User user = this.userRepository.findById(loggedUser.getId());
        if (user != null) {
            Set<Game> userCart = user.getCart();
            if (userCart == null) {
                userCart = new LinkedHashSet<>();
            }
            for (GameView gameView : cart) {
                Game game = this.gameRepository.findById(gameView.getId());
                userCart.add(game);
            }
        }
    }

    @Override
    public void removeFromCart(GameView gameToRemove, Long id) {
        User user = this.userRepository.findById(id);
        if (user != null) {
            Set<Game> userCart = user.getCart();
            if (userCart != null) {
                Game game = this.gameRepository.findById(gameToRemove.getId());
                userCart.remove(game);
            }
        }
    }
}
