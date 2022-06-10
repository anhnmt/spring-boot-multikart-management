package com.example.multikart.controller;

import com.example.multikart.domain.dto.CustomerRequestDTO;
import com.example.multikart.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/dashboard/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String index(Model model) {
        return customerService.findAllCustomers(model);
    }

    @GetMapping("/create")
    public String create(Model model) {
        return customerService.createCustomer(model);
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("customer") CustomerRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect) {
        return customerService.storeCustomer(input, result, model, redirect);
    }

    @GetMapping("/{id}")
    public String edit(@PathVariable("id") Long id, Model model, RedirectAttributes redirect) {
        return customerService.editCustomer(id, model, redirect);
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute("customer") CustomerRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect) {
        return customerService.updateCustomer(id, input, result, model, redirect);
    }

    @PostMapping("{id}/delete")
    public String delete(@PathVariable("id") Long id, Model model, RedirectAttributes redirect) {
        return customerService.deleteCustomer(id, model, redirect);
    }
}
