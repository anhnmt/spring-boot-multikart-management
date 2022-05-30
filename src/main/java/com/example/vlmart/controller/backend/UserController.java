package com.example.vlmart.controller.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard/users")
public class UserController {

    @GetMapping()
    public String listUsers() {
        return "backend/user/index";
    }

}
