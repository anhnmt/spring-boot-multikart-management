package com.example.multikart.controller;

import com.example.multikart.domain.dto.UserLoginRequestDTO;
import com.example.multikart.domain.dto.UserProfileRequestDTO;
import com.example.multikart.domain.dto.UserRegisterRequestDTO;
import com.example.multikart.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/")
public class AuthController {
    @Autowired
    private AuthService authService;

    @GetMapping("/dashboard/login")
    public String login(Model model) {
        return authService.backendLogin(model);
    }

    @PostMapping("/dashboard/login")
    public String postLogin(@RequestParam(value = "redirect", required = false) String referer, @Valid @ModelAttribute("user") UserLoginRequestDTO input, HttpSession session, BindingResult result, Model model) {
        return authService.backendPostLogin(referer, input, session, result, model);
    }

    @PostMapping("/dashboard/logout")
    public String logout(HttpSession session, Model model) {
        return authService.backendLogout(session, model);
    }

    @GetMapping("/dashboard/profile")
    public String backendProfile(HttpSession session, Model model) {
        return authService.backendProfile(session, model);
    }

    @PostMapping("/dashboard/profile")
    public String postProfile(@Valid @ModelAttribute("user") UserProfileRequestDTO input, HttpSession session, BindingResult result, Model model, RedirectAttributes redirect) {
        return authService.backendPostProfile(input, session, result, model, redirect);
    }

    @PostMapping("/dashboard/profile/avatar")
    public String postProfile(@RequestParam("file") MultipartFile file, HttpSession session, RedirectAttributes redirect) throws IOException {
        return authService.backendPostAvatar(file, session, redirect);
    }

    /**
     * FRONTEND SPACE
     */

    @GetMapping("/signin")
    public String frontendLogin(Model model) {
        return authService.frontendLogin(model);
    }

    @PostMapping("/signin")
    public String frontendPostLogin(@RequestParam(value = "redirect", required = false) String referer, @Valid @ModelAttribute("customer") UserLoginRequestDTO input, HttpSession session, BindingResult result, Model model) {
        return authService.frontendPostLogin(referer, input, session, result, model);
    }

    @PostMapping("/signout")
    public String frontendLogout(HttpSession session, Model model) {
        return authService.frontendLogout(session, model);
    }

    @GetMapping("/register")
    public String frontendRegister(Model model) {
        return authService.frontendRegister(model);
    }

    @PostMapping("/register")
    public String frontendPostLogin(@Valid @ModelAttribute("customer") UserRegisterRequestDTO input, HttpSession session, BindingResult result, Model model) {
        return authService.frontendPostRegister(input, session, result, model);
    }

    @GetMapping("/profile")
    public String frontendProfile(HttpSession session, Model model, RedirectAttributes redirect) {
        return authService.frontendProfile(session, model, redirect);
    }

    @PostMapping("/profile")
    public String frontendPostProfile(@Valid @ModelAttribute("customer") UserProfileRequestDTO input, HttpSession session, BindingResult result, Model model, RedirectAttributes redirect) {
        return authService.frontendPostProfile(input, session, result, model, redirect);
    }

    @PostMapping("/profile/avatar")
    public String frontendPostAvatar(@RequestParam("file") MultipartFile file, HttpSession session, RedirectAttributes redirect) throws IOException {
        return authService.frontendPostAvatar(file, session, redirect);
    }

}
