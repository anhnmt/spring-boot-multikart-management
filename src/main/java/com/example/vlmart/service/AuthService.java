package com.example.vlmart.service;

import com.example.vlmart.domain.dto.UserLoginRequestDTO;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

public interface AuthService {

    String backendLogin(Model model);

    String backendPostLogin(@Valid UserLoginRequestDTO input, HttpServletRequest request, BindingResult result, Model model);
}
