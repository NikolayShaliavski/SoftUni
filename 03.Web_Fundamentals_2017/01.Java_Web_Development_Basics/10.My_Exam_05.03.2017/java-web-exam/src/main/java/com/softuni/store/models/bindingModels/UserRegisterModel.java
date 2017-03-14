package com.softuni.store.models.bindingModels;

import javax.validation.constraints.Pattern;

public class UserRegisterModel {

    @Pattern(regexp = "^([A-Za-z0-9]+@[a-z]+\\.[a-z]+)$", message = "Invalid email.")
    private String email;

    @Pattern(regexp = "^[a-zA-Z -.]+$", message = "Invalid name.")
    private String fullName;

    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}",
    message = "Invalid Password. It should be at least 6 symbols long, containing 1 uppercase letter, 1 lowercase letter and 1 digit.")
    private String password;

    private String confirmPassword;

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
