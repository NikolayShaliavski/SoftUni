package com.blog.controllers;

import com.mvc.framework.annotations.controller.Controller;
import com.mvc.framework.annotations.request.GetMapping;
import com.mvc.framework.models.Model;

import javax.ejb.Stateless;

@Stateless
@Controller
public class HomeController {
    @GetMapping("/")
    public String details(Model model){
        model.addAttribute("title", "Softuni Blog");
        model.addAttribute("view", "home/index.jsp");
        return "base-layout";
    }
}
