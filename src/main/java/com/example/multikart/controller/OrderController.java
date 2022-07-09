package com.example.multikart.controller;

import com.example.multikart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/dashboard/orders")
    public String index(Model model) {
        return orderService.findAllOrders(model);
    }

    @GetMapping("/dashboard/orders/{id}")
    public String view(@PathVariable("id") Long id, Model model, RedirectAttributes redirect) {
        return orderService.viewOrder(id, model, redirect);
    }

    @PostMapping("/dashboard/orders/{id}")
    public String update(@PathVariable("id") Long id, @Valid @ModelAttribute("status") Integer status, BindingResult result, Model model, RedirectAttributes redirect) {
        return orderService.update(id, status, result, model, redirect);
    }

    @GetMapping("/orders")
    public String frontendListOrder(HttpSession session, Model model, RedirectAttributes redirect) {
        return orderService.frontendListOrder(session, model, redirect);
    }

    @GetMapping("/orders/{id}")
    public String frontendViewOrder(@PathVariable("id") Long id, HttpSession session, Model model, RedirectAttributes redirect) {
        return orderService.frontendViewOrder(id, session, model, redirect);
    }
}
