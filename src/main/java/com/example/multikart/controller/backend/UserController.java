package com.example.multikart.controller.backend;

import com.example.multikart.domain.dto.CreateUserRequestDTO;
import com.example.multikart.domain.dto.UpdateUserRequestDTO;
import com.example.multikart.service.UserService;
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

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("user") CreateUserRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect) {
        return userService.storeUser(input, result, model, redirect);
    }

    @GetMapping("/{id}")
    public String edit(@PathVariable("id") Long id, Model model, RedirectAttributes redirect) {
        return userService.editUser(id, model, redirect);
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute("user") UpdateUserRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect) {
        return userService.updateUser(id, input, result, model, redirect);
    }

    @PostMapping("{id}/delete")
    public String delete(@PathVariable("id") Long id, Model model, RedirectAttributes redirect) {
        return userService.deleteUser(id, model, redirect);
    }
}
