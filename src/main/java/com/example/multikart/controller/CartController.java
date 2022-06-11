package com.example.multikart.controller;

import com.example.multikart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping
    public String view(HttpSession session, Model model) {
        return cartService.view(session, model);
    }

    @PostMapping("/{id}")
    public String addToCart(@PathVariable("id") Long id, HttpSession session, Model model, RedirectAttributes redirect) {
        return cartService.addToCart(id, session, model, redirect);
    }

}
