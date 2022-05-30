package com.example.vlmart.controller.backend;

import com.example.vlmart.domain.dto.UserLoginRequestDTO;
import com.example.vlmart.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/dashboard")
public class AuthController {
    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String login(Model model) {
        return authService.backendLogin(model);
    }

    @PostMapping("/login")
    public String postLogin(@Valid UserLoginRequestDTO input, HttpServletRequest request, BindingResult result, Model model) {
        return authService.backendPostLogin(input, request, result, model);
    }

}
