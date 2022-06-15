package com.example.multikart.service.impl;

import com.example.multikart.common.Const;
import com.example.multikart.common.Const.DefaultStatus;
import com.example.multikart.common.Utils;
import com.example.multikart.domain.dto.CheckoutRequestDTO;
import com.example.multikart.domain.model.Customer;
import com.example.multikart.repo.PaymentRepository;
import com.example.multikart.repo.TransportRepository;
import com.example.multikart.repo.UnitRepository;
import com.example.multikart.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Service
public class CheckoutServiceImpl implements CheckoutService {
    @Autowired
    private TransportRepository transportRepository;
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public String view(HttpSession session, Model model) {
        var customer = (Customer) session.getAttribute("customer");
        model.addAttribute("customer", customer);

        var carts = Utils.getCartSession(session);
        model.addAttribute("carts", carts);

        var total = Utils.getTotalPriceCart(carts);
        model.addAttribute("total", total);

        var transports = transportRepository.findAllByStatus(DefaultStatus.ACTIVE);
        model.addAttribute("transports", transports);

        var payments = paymentRepository.findAllByStatus(DefaultStatus.ACTIVE);
        model.addAttribute("payments", payments);

        return "frontend/checkout";
    }

    @Override
    public String checkout(CheckoutRequestDTO input, BindingResult result, HttpSession session, Model model, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            model.addAttribute("checkout", input);

            return "redirect:/checkout";
        }

        return null;
    }
}
