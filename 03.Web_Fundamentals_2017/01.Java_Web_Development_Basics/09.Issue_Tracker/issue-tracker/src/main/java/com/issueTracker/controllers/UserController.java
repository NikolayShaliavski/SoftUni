package com.issueTracker.controllers;

import com.issueTracker.models.bindingModels.user.LoginUserModel;
import com.issueTracker.models.bindingModels.user.RegisterUserModel;
import com.issueTracker.models.viewModels.user.LoggedUserModel;
import com.issueTracker.services.UserService;
import com.mvcFramework.annotations.controller.Controller;
import com.mvcFramework.annotations.parameters.ModelAttribute;
import com.mvcFramework.annotations.request.GetMapping;
import com.mvcFramework.annotations.request.PostMapping;
import com.mvcFramework.models.Model;

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

    @GetMapping("/register")
    public String getRegisterPage() {
        return "/templates/create-user";
    }

    @PostMapping("/login")
    public String logInUser(@ModelAttribute LoginUserModel loginUserModel, Model model, HttpSession session) {
        LoggedUserModel loggedUser = this.userService.findLoggedUser(loginUserModel);
        if (loggedUser == null) {
            model.addAttribute("loginError", "Error");
            return "/templates/login";
        }
        session.setAttribute("currentUser", loggedUser);
        return "redirect:/";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute RegisterUserModel registerUserModel, Model model) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<RegisterUserModel>> constraintViolations = validator.validate(registerUserModel);
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<RegisterUserModel> constraintViolation : constraintViolations) {
            errors.add(constraintViolation.getMessage());
        }
        if (!registerUserModel.getPassword().equals(registerUserModel.getRepeatPassword())) {
            errors.add("Passwords not matching");
        }
        if (errors.size() > 0) {
            model.addAttribute("errors", errors);
            return "/templates/create-user";
        }
        this.userService.register(registerUserModel);

        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logOut(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
