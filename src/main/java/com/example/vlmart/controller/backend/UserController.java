package com.example.vlmart.controller.backend;

import com.example.vlmart.domain.dto.CreateUserRequestDTO;
import com.example.vlmart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String store(@Valid CreateUserRequestDTO input, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        return userService.storeUser(input, result, model, redirectAttributes);
    }

    @PostMapping("{id}/delete")
    public String delete(@PathVariable("id") Long id, Model model) {
        return userService.deleteUser(id, model);
    }
}
