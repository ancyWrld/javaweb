package com.wrld.javaweproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller  // This handles HTML pages
public class PageController {

    @GetMapping("/")
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String showDashboardPage() {
        return "dashboard";
    }
}