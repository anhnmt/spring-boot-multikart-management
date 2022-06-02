package com.example.multikart.controller.frontend;

import com.example.multikart.domain.dto.UserLoginRequestDTO;
import com.example.multikart.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class CustomerAuthController {
    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String login(Model model) {
        return authService.frontendLogin(model);
    }

    @PostMapping("/login")
    public String postLogin(@Valid @ModelAttribute("customer") UserLoginRequestDTO input, HttpSession session, BindingResult result, Model model) {
        return authService.frontendPostLogin(input, session, result, model);
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, Model model) {
        return authService.frontendLogout(session, model);
    }

}
