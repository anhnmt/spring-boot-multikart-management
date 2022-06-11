package com.example.multikart.service;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

public interface CartService {

    String view(HttpSession session, Model model);

    String addToCart(Long id, HttpSession session, Model model, RedirectAttributes redirect);
}
