package com.example.multikart.service;

import com.example.multikart.domain.dto.PaymentRequestDTO;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface PaymentService {
    String findAllPayments(Model model);

    String createPayment(Model model);

    String storePayment(PaymentRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect);

    String editPayment(Long id, Model model, RedirectAttributes redirect);

    String updatePayment(Long id, PaymentRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect);

    String deletePayment(Long id, Model model, RedirectAttributes redirect);
}
