package com.issueTracker.models.bindingModels.user;

public class LoginUserModel {

    private String username;

    private String password;

    public LoginUserModel() {
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
