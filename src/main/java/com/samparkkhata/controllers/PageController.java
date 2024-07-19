package com.samparkkhata.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.samparkkhata.entities.User;
import com.samparkkhata.forms.UserRegisterForm;
import com.samparkkhata.services.UserService;

@Controller
public class PageController {

    private final UserService userService;

    public PageController(UserService userService) {
        this.userService = userService;
    }

    // Home route
    @GetMapping("/home")
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

    // Contact us route
    @GetMapping("/contact")
    public String contactUs(Model model) {
        return "pages/contact";
    }

    // Login form route
    @GetMapping("/login")
    public String login(Model model) {
        return "pages/login";
    }

    // Signup form route
    @GetMapping("/signup")
    public String signup(Model model) {
        UserRegisterForm userRegisterForm = new UserRegisterForm();
        model.addAttribute(userRegisterForm);
        return "pages/signup";
    }

    // Register user
    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserRegisterForm userRegisterForm) {
        // Received the form data via @ModelAttribute annotation

        // From the incoming user signup form data we are creating user, and storing to db
        User user = User.builder()
                .userFirstName(userRegisterForm.getUserFirstName())
                .userLastName(userRegisterForm.getUserLastName())
                .userEmail(userRegisterForm.getUserEmail())
                .userPhoneNumber(userRegisterForm.getUserPhoneNumber())
                .userPassword(userRegisterForm.getUserPassword())
                .userAbout(userRegisterForm.getUserAbout())
                .build();
        
        User savedUser = userService.saveUser(user);

        System.out.println("New user: " + savedUser);

        return "redirect:/signup";
    }

}
