package com.example.multikart.service;

import com.example.multikart.domain.dto.CheckoutRequestDTO;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

public interface CheckoutService {

    String view(HttpSession session, Model model);

    String checkout(CheckoutRequestDTO input, BindingResult result, HttpSession session, Model model, RedirectAttributes redirect);
}
