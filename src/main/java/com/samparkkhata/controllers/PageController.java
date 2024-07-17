package com.samparkkhata.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    // Home route
    @RequestMapping("/home")
    public String home(Model model) {
        System.out.println("This is home page");
        model.addAttribute("name", "Achyut Pal");
        model.addAttribute("course", "Spring Boot Project");
        return "pages/home";
    }


    // About route
    @GetMapping("/about")
    public String about(Model model) {
        return "pages/about";
    }


    // Service route
    @GetMapping("/services")
    public String services(Model model) {
        return "pages/services";
    }
}
