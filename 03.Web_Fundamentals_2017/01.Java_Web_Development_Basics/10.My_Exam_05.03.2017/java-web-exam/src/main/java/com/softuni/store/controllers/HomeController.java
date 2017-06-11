package com.softuni.store.controllers;

import com.mvcFramework.annotations.controller.Controller;
import com.mvcFramework.annotations.parameters.RequestParam;
import com.mvcFramework.annotations.request.GetMapping;
import com.mvcFramework.models.Model;
import com.softuni.store.entities.user.Role;
import com.softuni.store.models.viewModels.GameView;
import com.softuni.store.models.viewModels.LoggedUserView;
import com.softuni.store.services.GameService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

@Stateless
@Controller
public class HomeController {

    @Inject
    private GameService gameService;

    @GetMapping("/")
    public String getHomePage(@RequestParam("filter") String filter, Model model, HttpSession session) {
        LoggedUserView loggedUser = (LoggedUserView) session.getAttribute("loggedUser");
        Set<GameView> allGameViews = new HashSet<>();
        if (loggedUser == null ||
                filter == null ||
                filter.isEmpty() ||
                filter.equalsIgnoreCase("All") ||
                loggedUser.getRole() == Role.ADMIN) {
            allGameViews = this.gameService.getAllGames();
        } else if (loggedUser != null && filter.equalsIgnoreCase("Owned")) {
            allGameViews = loggedUser.getGames();
        }
        model.addAttribute("games", allGameViews);
        if (loggedUser == null) {
            return "/templates/home/guest-home";
        } else if (loggedUser.getRole().name().equals("ADMIN")) {
            return "/templates/home/admin-home";
        } else  {
            return "/templates/home/user-home";
        }
    }
}
