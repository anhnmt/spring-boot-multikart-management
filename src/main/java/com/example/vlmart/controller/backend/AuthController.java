package com.example.vlmart.controller.backend;

import com.example.vlmart.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class AuthController {
    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String login() {
        return authService.backendLogin();
    }

    @PostMapping("/login")
    public String postLogin() {
        return authService.backendPostLogin();
    }

}
