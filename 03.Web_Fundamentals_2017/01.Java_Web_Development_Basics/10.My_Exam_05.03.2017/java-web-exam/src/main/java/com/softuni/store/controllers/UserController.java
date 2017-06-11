package com.softuni.store.controllers;

import com.mvcFramework.annotations.controller.Controller;
import com.mvcFramework.annotations.parameters.ModelAttribute;
import com.mvcFramework.annotations.parameters.PathVariable;
import com.mvcFramework.annotations.request.GetMapping;
import com.mvcFramework.annotations.request.PostMapping;
import com.mvcFramework.models.Model;
import com.softuni.store.models.bindingModels.GameEditModel;
import com.softuni.store.models.bindingModels.UserLoginModel;
import com.softuni.store.models.bindingModels.UserRegisterModel;
import com.softuni.store.models.viewModels.GameEditView;
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
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Stateless
@Controller
public class UserController {

    @Inject
    private UserService userService;

    @Inject
    private GameService gameService;

    @GetMapping("/login")
    public String getLoginPage() {
        return "/templates/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserLoginModel userLoginModel, Model model, HttpSession session) {

        LoggedUserView loggedUser = this.userService.findByEmailAndPassword(userLoginModel);
        if (loggedUser == null) {
            model.addAttribute("loginError", "Error");
            return "/templates/login";
        }
        if (loggedUser.getCart() == null) {
            loggedUser.setCart(new LinkedHashSet<>());
        }
        session.setAttribute("loggedUser", loggedUser);
        return "redirect:/";
    }

    @GetMapping("/register")
    public String getRegisterPage() {
        return "/templates/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserRegisterModel userRegisterModel, Model model) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<UserRegisterModel>> constraintViolations = validator.validate(userRegisterModel);
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<UserRegisterModel> constraintViolation : constraintViolations) {
            errors.add(constraintViolation.getMessage());
        }
        if (!userRegisterModel.getPassword().equals(userRegisterModel.getConfirmPassword())) {
            errors.add("Passwords not matching");
        }
        if (errors.size() > 0) {
            model.addAttribute("errors", errors);
            return "/templates/register";
        }
        this.userService.register(userRegisterModel);

        return "redirect:/login";
    }

    @GetMapping("/cart")
    public String getCartPage(Model model, HttpSession session) {
        LoggedUserView loggedUser = (LoggedUserView) session.getAttribute("loggedUser");
        model.addAttribute("games", loggedUser.getCart());
        Double totalPrice = loggedUser.getCart().
                stream().mapToDouble(game -> Double.parseDouble(game.getPrice())).sum();
        model.addAttribute("totalPrice", String.format("%.2f", totalPrice));
        return "templates/cart";
    }

    @GetMapping("/edit-game/{id}")
    public String getEditPage(@PathVariable("id") Long id, Model model) {
        GameEditView game = this.gameService.getGameEditView(id);
        model.addAttribute("game", game);
        return "/templates/edit-game";
    }

    @PostMapping("/edit-game/{id}")
    public String editGame(@PathVariable("id") Long id, @ModelAttribute GameEditModel gameEditModel, Model model, HttpSession session) throws ParseException {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<GameEditModel>> constraintViolations = validator.validate(gameEditModel);
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<GameEditModel> constraintViolation : constraintViolations) {
            errors.add(constraintViolation.getMessage());
        }
        String price = gameEditModel.getPrice();
        String size = gameEditModel.getSize();
        if (price.startsWith("-")) {
            errors.add("Price must be positive.");
        }
        if (size.startsWith("-")) {
            errors.add("Size must be positive.");
        }
        if (gameEditModel.getThumbnail() != null) {
            if (!gameEditModel.getThumbnail().contains("http://") &&
                    !gameEditModel.getThumbnail().contains("https://")) {

                errors.add("Invalid thumbnail format.");
            }
        }
        if (errors.size() > 0) {
            model.addAttribute("errors", errors);
            model.addAttribute("game", gameEditModel);
            return "/templates/edit-game";
        }
        gameEditModel.setId(id);
        this.gameService.edit(gameEditModel);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logOut(HttpSession session) {
        LoggedUserView loggedUser = (LoggedUserView) session.getAttribute("loggedUser");
        this.userService.saveCart(loggedUser);
        session.invalidate();
        return "redirect:/";
    }
}
