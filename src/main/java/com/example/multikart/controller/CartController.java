package com.example.multikart.controller;

import com.example.multikart.domain.dto.AddToCartRequestDTO;
import com.example.multikart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping
    public String view(HttpSession session, Model model) {
        return cartService.view(session, model);
    }

    @PostMapping
    public String addToCart(@Valid @ModelAttribute("cart") AddToCartRequestDTO input, HttpSession session, Model model, RedirectAttributes redirect) {
        return cartService.addToCart(input, session, model, redirect);
    }

}
