package com.softuni.store.controllers;

import com.mvcFramework.annotations.controller.Controller;
import com.mvcFramework.annotations.parameters.RequestParam;
import com.mvcFramework.annotations.request.GetMapping;
import com.mvcFramework.models.Model;
import com.softuni.store.entities.user.Role;
import com.softuni.store.models.viewModels.GameHomeView;
import com.softuni.store.models.viewModels.LoggedUserView;
import com.softuni.store.services.GameService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Controller
public class HomeController {

    @Inject
    private GameService gameService;

    @GetMapping("/")
    public String getHomePage(@RequestParam("filter") String filter, Model model, HttpSession session) {
        LoggedUserView loggedUser = (LoggedUserView) session.getAttribute("loggedUser");
        List<GameHomeView> allGameHomeViews = new ArrayList<>();
        if (loggedUser == null ||
                filter == null ||
                filter.isEmpty() ||
                filter.equalsIgnoreCase("All") ||
                loggedUser.getRole() == Role.ADMIN) {
            allGameHomeViews = this.gameService.getAllGames();
        } else if (loggedUser != null && filter.equalsIgnoreCase("Owned")) {
            allGameHomeViews = this.gameService.getUserOwnedGames(loggedUser.getId());
        }
        model.addAttribute("games", allGameHomeViews);
        if (loggedUser == null) {
            return "/templates/home/guest-home";
        } else if (loggedUser.getRole().name().equals("ADMIN")) {
            return "/templates/home/admin-home";
        } else  {
            return "/templates/home/user-home";
        }
    }

//    @GetMapping("/")
//    public String getGuestHomePage(Model model, HttpSession session) {
//        LoggedUserView loggedUser = (LoggedUserView) session.getAttribute("loggedUser");
//        if (loggedUser == null) {
//            List<GameHomeView> allGameHomeViews = this.gameService.getAllGames();
//            model.addAttribute("games", allGameHomeViews);
//            return "/templates/home/guest-home";
//        }
//        if (loggedUser.getRole().name().equals("ADMIN")) {
//            return "redirect:/admin";
//        }
//        return "redirect:/user";
//    }
//
//    @GetMapping("/admin")
//    public String getAdminHomePage(Model model) {
//        List<GameHomeView> allGameHomeViews = this.gameService.getAllGames();
//        model.addAttribute("games", allGameHomeViews);
//        return "/templates/home/admin-home";
//    }
//
//    @GetMapping("/user")
//    public String getUserHomePage(Model model) {
//        List<GameHomeView> allGameHomeViews = this.gameService.getAllGames();
//        model.addAttribute("games", allGameHomeViews);
//        return "/templates/home/user-home";
//    }
}
