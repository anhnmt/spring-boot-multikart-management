package com.example.vlmart.service;

import com.example.vlmart.domain.dto.CreateUserRequestDTO;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface UserService {
    String findAllUsers(Model model);

    String createUser(Model model);

    String storeUser(CreateUserRequestDTO input, BindingResult result, Model model);

    String deleteUser(Long id, Model model);
}
