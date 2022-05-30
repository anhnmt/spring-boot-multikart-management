package com.example.vlmart.controller.backend;

import com.example.vlmart.domain.dto.CreateUserRequestDTO;
import com.example.vlmart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/dashboard/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String index(Model model) {
        return userService.findAllUsers(model);
    }

    @GetMapping("/create")
    public String create(Model model) {
        return userService.createUser(model);
    }

    @PostMapping
    public String store(@Valid CreateUserRequestDTO input, BindingResult result, Model model) {
        return userService.storeUser(input, result, model);
    }
}
