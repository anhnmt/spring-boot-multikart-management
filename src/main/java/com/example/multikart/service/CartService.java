package com.example.multikart.service;

import com.example.multikart.domain.dto.AddToCartRequestDTO;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

public interface CartService {

    String view(HttpSession session, Model model);

    String addToCart(AddToCartRequestDTO input, HttpSession session, Model model, RedirectAttributes redirect);

    String removeFromCart(Long id, HttpSession session, Model model, RedirectAttributes redirect);
}
