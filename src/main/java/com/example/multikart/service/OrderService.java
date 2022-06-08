package com.example.multikart.service;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface OrderService {
    String findAllOrders(Model model);

    String viewOrder(Long id, Model model, RedirectAttributes redirect);
}
