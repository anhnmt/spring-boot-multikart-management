package com.example.multikart.controller.backend;

import com.example.multikart.domain.dto.UserLoginRequestDTO;
import com.example.multikart.domain.dto.UserProfileRequestDTO;
import com.example.multikart.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/dashboard")
public class UserAuthController {
    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String login(Model model) {
        return authService.backendLogin(model);
    }

    @PostMapping("/login")
    public String postLogin(@Valid @ModelAttribute("user") UserLoginRequestDTO input, HttpSession session, BindingResult result, Model model) {
        return authService.backendPostLogin(input, session, result, model);
    }

    @PostMapping("/logout")
    public String logout(HttpSession session, Model model) {
        return authService.backendLogout(session, model);
    }

    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {
        return authService.backendProfile(session, model);
    }

    @PostMapping("/profile")
    public String postProfile(@Valid @ModelAttribute("user") UserProfileRequestDTO input, HttpSession session, BindingResult result, Model model, RedirectAttributes redirect) {
        return authService.backendPostProfile(input, session, result, model, redirect);
    }

}
