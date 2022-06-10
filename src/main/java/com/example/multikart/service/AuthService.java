package com.example.multikart.service;

import com.example.multikart.domain.dto.UserLoginRequestDTO;
import com.example.multikart.domain.dto.UserProfileRequestDTO;
import com.example.multikart.domain.dto.UserRegisterRequestDTO;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

public interface AuthService {

    String backendLogin(Model model);

    String backendPostLogin(UserLoginRequestDTO input, HttpSession session, BindingResult result, Model model);

    String backendLogout(HttpSession session, Model model);

    String backendProfile(HttpSession session, Model model);

    String backendPostProfile(UserProfileRequestDTO input, HttpSession session, BindingResult result, Model model, RedirectAttributes redirect);

    String frontendLogin(Model model);

    String frontendPostLogin(UserLoginRequestDTO input, HttpSession session, BindingResult result, Model model);

    String frontendLogout(HttpSession session, Model model);

    String frontendRegister(Model model);

    String frontendPostRegister(UserRegisterRequestDTO input, HttpSession session, BindingResult result, Model model);
}
