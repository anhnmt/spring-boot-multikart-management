package com.example.vlmart.service;

import com.example.vlmart.domain.dto.UserLoginRequestDTO;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

public interface AuthService {

    String backendLogin(Model model);

    String backendPostLogin(@Valid UserLoginRequestDTO input, HttpSession session, BindingResult result, Model model);

    String logout(HttpSession session, Model model);
}
