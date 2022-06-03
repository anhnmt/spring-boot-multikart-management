package com.example.multikart.controller.backend;

import com.example.multikart.domain.dto.RoleRequestDTO;
import com.example.multikart.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/dashboard/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping
    public String index(Model model) {
        return roleService.findAllRoles(model);
    }

    @GetMapping("/create")
    public String create(Model model) {
        return roleService.createRole(model);
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("role") RoleRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect) {
        return roleService.storeRole(input, result, model, redirect);
    }

    @GetMapping("/{id}")
    public String edit(@PathVariable("id") Long id, Model model, RedirectAttributes redirect) {
        return roleService.editRole(id, model, redirect);
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute("role") RoleRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect) {
        return roleService.updateRole(id, input, result, model, redirect);
    }

    @PostMapping("{id}/delete")
    public String delete(@PathVariable("id") Long id, Model model, RedirectAttributes redirect) {
        return roleService.deleteRole(id, model, redirect);
    }
}
