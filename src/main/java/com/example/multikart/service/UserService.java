package com.example.multikart.service;

import com.example.multikart.domain.dto.UserRequestDTO;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface UserService {
    String findAllUsers(Model model);

    String createUser(Model model);

    String storeUser(UserRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect);

    String editUser(Long id, Model model, RedirectAttributes redirect);

    String updateUser(Long id, UserRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect);

    String deleteUser(Long id, Model model, RedirectAttributes redirect);
}
