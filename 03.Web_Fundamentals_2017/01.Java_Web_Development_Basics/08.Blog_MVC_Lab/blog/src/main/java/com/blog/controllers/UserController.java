package com.blog.controllers;

import com.blog.entities.User;
import com.blog.repository.UserRepository;
import com.mvc.framework.annotations.controller.Controller;
import com.mvc.framework.annotations.parameters.RequestParam;
import com.mvc.framework.annotations.request.GetMapping;
import com.mvc.framework.annotations.request.PostMapping;
import com.mvc.framework.models.Model;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Stateless
@Controller
public class UserController {

    public static final String FULL_NAME_PATTERN = "^([A-Za-z]{5,50})$";
    public static final String EMAIL_PATTERN = "^(.+\\@.+\\..+)$";

    private List<String> errors;

    @Inject
    private UserRepository userRepository;

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("title", "Register");
        model.addAttribute("view", "user/register.jsp");
        return "base-layout";
    }

    @PostMapping("/register")
    public String register(@RequestParam("email") String email,
                           @RequestParam("fullname") String fullName,
                           @RequestParam("password") String password,
                           @RequestParam("confirmPassword") String confirmPassword,
                           Model model) {
        this.errors = new ArrayList<>();
        this.validateParameters(email, fullName, password, confirmPassword);

        if (this.errors.size() == 0) {
            User user = new User();
            user.setEmail(email);
            user.setFullName(fullName);
            user.setPassword(password);
            this.userRepository.createUser(user);
            return "/login";
        } else {
            model.addAttribute("errors", this.errors);
            model.addAttribute("title", "Register");
            model.addAttribute("view", "user/register.jsp");
            return "base-layout";
        }
    }

    private void validateParameters(String email,
                                       String fullName,
                                       String password,
                                       String confirmPassword) {
        Pattern pattern;
        Matcher matcher;

        if (email == null) {
            this.errors.add("Invalid email.");
        } else {
            pattern = Pattern.compile(EMAIL_PATTERN);
            matcher = pattern.matcher(email);
            if (!matcher.find()) {
                this.errors.add("Invalid email.");
            }
        }
        if (fullName == null ||
                fullName.trim() == "" ||
                fullName.length() < 5 ||
                fullName.length() > 50) {
            this.errors.add("Full name must be between 5 and 50 characters");
        } else {
            pattern = Pattern.compile(FULL_NAME_PATTERN);
            matcher = pattern.matcher(fullName);
            if (!matcher.find()) {
                this.errors.add("Full name contains invalid characters.");
            }
        }
        if (password == null || password.length() < 3 || password.length() > 30) {
            this.errors.add("Password must be between 3 and 30 characters.");
        } else if (!password.equals(confirmPassword)) {
            this.errors.add("Passwords do not match.");
        }
    }
}
