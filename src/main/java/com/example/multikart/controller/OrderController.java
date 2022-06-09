package com.example.multikart.controller;

import com.example.multikart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/dashboard/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public String index(Model model) {
        return orderService.findAllOrders(model);
    }

    @GetMapping("/{id}")
    public String view(@PathVariable("id") Long id, Model model, RedirectAttributes redirect) {
        return orderService.viewOrder(id, model, redirect);
    }
}
