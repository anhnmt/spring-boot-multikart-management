package com.example.multikart.service;

import com.example.multikart.domain.dto.CustomerRequestDTO;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface CustomerService {
    String findAllCustomers(Model model);

    String createCustomer(Model model);

    String storeCustomer(CustomerRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect);

    String editCustomer(Long id, Model model, RedirectAttributes redirect);

    String updateCustomer(Long id, CustomerRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect);

    String deleteCustomer(Long id, Model model, RedirectAttributes redirect);
}
