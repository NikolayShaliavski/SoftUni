package com.softuni.store.models.viewModels;

import com.softuni.store.entities.user.Role;

import java.util.List;

public class LoggedUserView {

    private Long id;

    private String email;

    private Role role;

    private List<GameCartView> cart;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<GameCartView> getCart() {
        return this.cart;
    }

    public void setCart(List<GameCartView> cart) {
        this.cart = cart;
    }
}
