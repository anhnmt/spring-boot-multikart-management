package com.example.multikart.service;

import com.example.multikart.domain.dto.UserLoginRequestDTO;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

public interface AuthService {

    String backendLogin(Model model);

    String backendPostLogin(@Valid UserLoginRequestDTO input, HttpSession session, BindingResult result, Model model);

    String backendLogout(HttpSession session, Model model);

    String frontendLogin(Model model);

    String frontendPostLogin(@Valid UserLoginRequestDTO input, HttpSession session, BindingResult result, Model model);

    String frontendLogout(HttpSession session, Model model);
}
