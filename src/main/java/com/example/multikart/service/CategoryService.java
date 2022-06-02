package com.example.multikart.service;

import com.example.multikart.domain.dto.CreateCategoryRequestDTO;
import com.example.multikart.domain.dto.UpdateCategoryRequestDTO;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface CategoryService {
    String findAllCategories(Model model);

    String createCategory(Model model);

    String storeCategory(CreateCategoryRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect);

    String editCategory(Long id, Model model, RedirectAttributes redirect);

    String updateCategory(Long id, UpdateCategoryRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect);

    String deleteCategory(Long id, Model model, RedirectAttributes redirect);
}
