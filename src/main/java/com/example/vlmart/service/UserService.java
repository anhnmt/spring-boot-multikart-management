package com.example.vlmart.service;

import com.example.vlmart.domain.dto.CreateUserRequestDTO;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;

public interface UserService {
    String findAllUsers(Model model);

    String createUser(Model model);

    String storeUser(@Valid CreateUserRequestDTO input, BindingResult result, Model model);
}
