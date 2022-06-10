package com.example.multikart.service.impl;

import com.example.multikart.domain.dto.ItemProductDTO;
import com.example.multikart.service.CheckoutService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class CheckoutServiceImpl implements CheckoutService {
    @Override
    public String view(HttpSession session, Model model) {
        var carts = (List<ItemProductDTO>) session.getAttribute("carts");
        model.addAttribute("carts", carts);

        return "frontend/checkout";
    }
}
