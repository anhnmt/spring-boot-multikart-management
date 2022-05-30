package com.example.vlmart.controller.backend;

import com.example.vlmart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String findAllUsers(Model model) {
        return userService.findAllUsers(model);
    }

}
