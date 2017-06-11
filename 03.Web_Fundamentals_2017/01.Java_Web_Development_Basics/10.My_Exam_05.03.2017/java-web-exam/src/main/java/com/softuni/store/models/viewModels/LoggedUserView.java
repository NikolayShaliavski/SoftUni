package com.softuni.store.models.viewModels;

import com.softuni.store.entities.user.Role;

import java.util.Set;

public class LoggedUserView {

    private Long id;

    private String email;

    private Role role;

    private Set<GameView> games;

    private Set<GameView> cart;

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

    public Set<GameView> getGames() {
        return games;
    }

    public void setGames(Set<GameView> games) {
        this.games = games;
    }

    public Set<GameView> getCart() {
        return this.cart;
    }

    public void setCart(Set<GameView> cart) {
        this.cart = cart;
    }

    public boolean containsGame(Long id) {
        return this.games.stream().anyMatch(game -> game.getId().equals(id));
    }

    public boolean containsGameInCart(Long id) {
        return this.cart != null &&
                this.cart.stream().anyMatch(game -> game.getId().equals(id));
    }
}
