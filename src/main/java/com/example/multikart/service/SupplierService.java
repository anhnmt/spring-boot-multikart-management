package com.example.multikart.service;

import com.example.multikart.domain.dto.SupplierRequestDTO;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface SupplierService {
    String findAllSuppliers(Model model);

    String createSupplier(Model model);

    String storeSupplier(SupplierRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect);

    String editSupplier(Long id, Model model, RedirectAttributes redirect);

    String updateSupplier(Long id, SupplierRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect);

    String deleteSupplier(Long id, Model model, RedirectAttributes redirect);
}
