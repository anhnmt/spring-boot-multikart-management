package com.example.multikart.controller.backend;

import com.example.multikart.domain.dto.CreateCategoryRequestDTO;
import com.example.multikart.domain.dto.UpdateCategoryRequestDTO;
import com.example.multikart.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/dashboard/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String index(Model model) {
        return categoryService.findAllCategories(model);
    }

    @GetMapping("/create")
    public String create(Model model) {
        return categoryService.createCategory(model);
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("category") CreateCategoryRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect) {
        return categoryService.storeCategory(input, result, model, redirect);
    }

    @GetMapping("/{id}")
    public String edit(@PathVariable("id") Long id, Model model, RedirectAttributes redirect) {
        return categoryService.editCategory(id, model, redirect);
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute("category") UpdateCategoryRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect) {
        return categoryService.updateCategory(id, input, result, model, redirect);
    }

    @PostMapping("{id}/delete")
    public String delete(@PathVariable("id") Long id, Model model, RedirectAttributes redirect) {
        return categoryService.deleteCategory(id, model, redirect);
    }
}
