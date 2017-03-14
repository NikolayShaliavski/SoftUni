package com.softuni.store.controllers;

import com.mvcFramework.annotations.controller.Controller;
import com.mvcFramework.annotations.parameters.ModelAttribute;
import com.mvcFramework.annotations.request.GetMapping;
import com.mvcFramework.annotations.request.PostMapping;
import com.mvcFramework.models.Model;
import com.softuni.store.models.bindingModels.UserLoginModel;
import com.softuni.store.models.bindingModels.UserRegisterModel;
import com.softuni.store.models.viewModels.LoggedUserView;
import com.softuni.store.services.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Stateless
@Controller
public class UserController {

    @Inject
    private UserService userService;

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
            loggedUser.setCart(new ArrayList<>());
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
        model.addAttribute("totalPrice", totalPrice);
        return "/templates/cart";
    }

    @GetMapping("/logout")
    public String logOut(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
