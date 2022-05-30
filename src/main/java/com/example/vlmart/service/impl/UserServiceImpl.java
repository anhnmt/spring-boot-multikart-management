package com.example.vlmart.service.impl;

import com.example.vlmart.common.utils.DataUtils;
import com.example.vlmart.domain.dto.CreateUserRequestDTO;
import com.example.vlmart.domain.model.User;
import com.example.vlmart.repo.UserRepository;
import com.example.vlmart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public String findAllUsers(Model model) {
        var users = userRepository.findAll();
        model.addAttribute("users", users);

        return "backend/user/index";
    }

    @Override
    public String createUser(Model model) {
        model.addAttribute("user", User.builder().status(1).build());

        return "backend/user/create";
    }

    @Override
    public String storeUser(@Valid CreateUserRequestDTO input, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "backend/user/create";
        }

        var user = userRepository.findByEmail(input.getEmail());
        if (DataUtils.notNullOrEmpty(user)) {
            result.rejectValue("email", null, "Email is already exist");
        }

        var newUser = new User(input);
        userRepository.save(newUser);

        return "redirect:/dashboard/users";
    }
}
