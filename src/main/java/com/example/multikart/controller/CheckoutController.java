package com.example.multikart.controller;

import com.example.multikart.domain.model.Customer;
import com.example.multikart.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {
    @Autowired
    private CheckoutService checkoutService;

    @GetMapping
    public String view(HttpSession session, Model model) {
        var customer = (Customer) session.getAttribute("customer");
        model.addAttribute("customer", customer);

        return checkoutService.view(session, model);
    }

}
