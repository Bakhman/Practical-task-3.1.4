package com.bakh.springrestdemo.controller;

import com.bakh.springrestdemo.model.User;
import com.bakh.springrestdemo.service.UserServiceImpl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @author Bakhmai Begaev
 */
@Controller
@RequestMapping()
public class AdminController {

    private final UserServiceImpl userService;

    public AdminController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String toHomePage() {
        return "homePage";
    }

    @GetMapping("/user")
    public String getUserPage(Principal principal, Model model) {
        User user = userService.getUserByUserName(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }


    @GetMapping("/admin")
    public String getAdminPage(Principal principal, Model model){
        User user = userService.getUserByUserName(principal.getName());
        model.addAttribute("user", user);
        return "users";
    }

}
