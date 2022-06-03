package com.example.multikart.controller.backend;

import com.example.multikart.domain.dto.PaymentRequestDTO;
import com.example.multikart.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/dashboard/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public String index(Model model) {
        return paymentService.findAllPayments(model);
    }

    @GetMapping("/create")
    public String create(Model model) {
        return paymentService.createPayment(model);
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("payment") PaymentRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect) {
        return paymentService.storePayment(input, result, model, redirect);
    }

    @GetMapping("/{id}")
    public String edit(@PathVariable("id") Long id, Model model, RedirectAttributes redirect) {
        return paymentService.editPayment(id, model, redirect);
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute("payment") PaymentRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect) {
        return paymentService.updatePayment(id, input, result, model, redirect);
    }

    @PostMapping("{id}/delete")
    public String delete(@PathVariable("id") Long id, Model model, RedirectAttributes redirect) {
        return paymentService.deletePayment(id, model, redirect);
    }
}
