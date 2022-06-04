package com.example.multikart.service;

import com.example.multikart.domain.dto.ProductRequestDTO;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface ProductService {
    String findAllProducts(Model model);

    String createProduct(Model model);

    String storeProduct(ProductRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect);

    String editProduct(Long id, Model model, RedirectAttributes redirect);

    String updateProduct(Long id, ProductRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect);

    String deleteProduct(Long id, Model model, RedirectAttributes redirect);
}
