package com.samparkkhata.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/user")
public class UserController {

    // user dashboard page
    @GetMapping("/dashboard")
    public String userDashboard() {
        return "user/dashboard";
    }

    // user profile page
    @GetMapping("/profile")
    public String userProfile() {
        return "user/profile";
    }
    

    // user add contacts page

    // user view contacts

    // user edit contacts

    // user delete contact

    // user search contact
}
