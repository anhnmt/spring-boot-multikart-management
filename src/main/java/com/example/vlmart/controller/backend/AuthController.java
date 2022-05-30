package com.example.vlmart.controller.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class AuthController {

    @GetMapping("/login")
    public String login() {
        return "backend/index";
    }

    @PostMapping("/login")
    public String postLogin() {
        return "backend/index";
    }

}
