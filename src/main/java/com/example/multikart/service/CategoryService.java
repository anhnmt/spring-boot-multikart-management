package com.example.multikart.service;

import com.example.multikart.domain.dto.CategoryRequestDTO;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

public interface CategoryService {
    String findAllCategories(Model model);

    String createCategory(Model model);

    String storeCategory(CategoryRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect);

    String editCategory(Long id, Model model, RedirectAttributes redirect);

    String updateCategory(Long id, CategoryRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect);

    String deleteCategory(Long id, Model model, RedirectAttributes redirect);

    String frontendCategory(String slug, Optional<Integer> page, Optional<Integer> size, Model model, RedirectAttributes redirect);
}
