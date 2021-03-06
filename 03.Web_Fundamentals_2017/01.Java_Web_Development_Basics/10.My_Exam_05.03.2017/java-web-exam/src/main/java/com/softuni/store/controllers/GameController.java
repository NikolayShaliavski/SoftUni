package com.softuni.store.controllers;

import com.mvcFramework.annotations.controller.Controller;
import com.mvcFramework.annotations.parameters.ModelAttribute;
import com.mvcFramework.annotations.parameters.PathVariable;
import com.mvcFramework.annotations.request.GetMapping;
import com.mvcFramework.annotations.request.PostMapping;
import com.mvcFramework.models.Model;
import com.softuni.store.models.bindingModels.GameAddModel;
import com.softuni.store.models.viewModels.GameAdminView;
import com.softuni.store.models.viewModels.GameDetailsView;
import com.softuni.store.models.viewModels.GameView;
import com.softuni.store.models.viewModels.LoggedUserView;
import com.softuni.store.services.GameService;
import com.softuni.store.services.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.text.ParseException;
import java.util.*;

@Stateless
@Controller
public class GameController {

    @Inject
    private GameService gameService;

    @Inject
    private UserService userService;

    @GetMapping("/game-details/{id}")
    public String getGameInfo(@PathVariable("id") Long id, Model model) {
        GameDetailsView gameView = this.gameService.getGameDetailsViewById(id);
        model.addAttribute("gameView", gameView);
        return "/templates/game-details";
    }

    @GetMapping("/games")
    public String getAdminGamesPage(Model model) {
        List<GameAdminView> allGameHomeViews = this.gameService.getAllGamesAdminView();
        model.addAttribute("gameViews", allGameHomeViews);
        return "/templates/admin-games";
    }

    @GetMapping("/add-game")
    public String getAdminAddGamePage(Model model) {
        return "/templates/add-game";
    }

    @PostMapping("/add-game")
    public String addGame(@ModelAttribute GameAddModel gameAddModel, Model model) throws ParseException {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<GameAddModel>> constraintViolations = validator.validate(gameAddModel);
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<GameAddModel> constraintViolation : constraintViolations) {
            errors.add(constraintViolation.getMessage());
        }
        String price = gameAddModel.getPrice();
        String size = gameAddModel.getSize();
        if (price.startsWith("-")) {
            errors.add("Price must be positive.");
        }
        if (size.startsWith("-")) {
            errors.add("Size must be positive.");
        }
        if (gameAddModel.getThumbnail() != null) {
            if (!gameAddModel.getThumbnail().contains("http://") &&
                    !gameAddModel.getThumbnail().contains("https://")) {

                errors.add("Invalid thumbnail format.");
            }
        }
        if (errors.size() > 0) {
            model.addAttribute("errors", errors);
            return "/templates/add-game";
        }


        this.gameService.create(gameAddModel);
        return "redirect:/games";
    }

    @GetMapping("/buy/{id}")
    public String buyGame(@PathVariable("id") Long id, HttpSession session) {
        LoggedUserView loggedUser = (LoggedUserView) session.getAttribute("loggedUser");
        GameView game = this.gameService.getGameCartViewById(id);
        loggedUser.getCart().add(game);

        return "redirect:/";
    }

    @GetMapping("/cart/{id}")
    public String removeGame(@PathVariable("id") Long id, HttpSession session, Model model) {
        LoggedUserView loggedUser = (LoggedUserView) session.getAttribute("loggedUser");
        GameView gameToRemove = loggedUser.getCart().
                stream().filter(game -> game.getId() == id).findFirst().orElse(null);
        if (gameToRemove != null) {
            loggedUser.getCart().remove(gameToRemove);
            this.userService.removeFromCart(gameToRemove, loggedUser.getId());
        }
        session.setAttribute("loggedUser", loggedUser);
        model.addAttribute("games", loggedUser.getCart());
        return "redirect:/cart";
    }

    @PostMapping("/cart")
    public String orderGame(HttpSession session) {
        LoggedUserView loggedUser = (LoggedUserView) session.getAttribute("loggedUser");
        if (loggedUser.getCart() != null && loggedUser.getCart().size() > 0) {
            this.userService.orderGames(loggedUser);
        }
        loggedUser.getGames().addAll(loggedUser.getCart());
        loggedUser.setCart(new LinkedHashSet<>());
        return "redirect:/cart";
    }
}
