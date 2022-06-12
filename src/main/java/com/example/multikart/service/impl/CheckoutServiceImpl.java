package com.example.multikart.service.impl;

import com.example.multikart.common.Utils;
import com.example.multikart.domain.dto.ItemProductDTO;
import com.example.multikart.domain.model.Customer;
import com.example.multikart.service.CheckoutService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class CheckoutServiceImpl implements CheckoutService {
    @Override
    public String view(HttpSession session, Model model) {
        var customer = (Customer) session.getAttribute("customer");
        model.addAttribute("customer", customer);

        var carts = Utils.getCartSession(session);
        model.addAttribute("carts", carts);

        var total = Utils.getTotalPriceCart(carts);
        model.addAttribute("total", total);

        return "frontend/checkout";
    }
}
