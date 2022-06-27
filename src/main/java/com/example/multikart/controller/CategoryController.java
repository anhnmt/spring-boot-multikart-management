package com.example.multikart.controller;

import com.example.multikart.domain.dto.CategoryRequestDTO;
import com.example.multikart.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/dashboard/categories")
    public String index(Model model) {
        return categoryService.findAllCategories(model);
    }

    @GetMapping("/dashboard/categories/create")
    public String create(Model model) {
        return categoryService.createCategory(model);
    }

    @PostMapping("/dashboard/categories/create")
    public String store(@Valid @ModelAttribute("category") CategoryRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect) {
        return categoryService.storeCategory(input, result, model, redirect);
    }

    @GetMapping("/dashboard/categories/{id}")
    public String edit(@PathVariable("id") Long id, Model model, RedirectAttributes redirect) {
        return categoryService.editCategory(id, model, redirect);
    }

    @PostMapping("/dashboard/categories/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute("category") CategoryRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect) {
        return categoryService.updateCategory(id, input, result, model, redirect);
    }

    @PostMapping("/dashboard/categories/{id}/delete")
    public String delete(@PathVariable("id") Long id, Model model, RedirectAttributes redirect) {
        return categoryService.deleteCategory(id, model, redirect);
    }

    /**
     * FRONTEND SPACE
     */

    @GetMapping("/danh-muc/{slug}")
    public String view(@PathVariable("slug") String slug, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size, Model model, RedirectAttributes redirect) {
        return categoryService.frontendCategory(slug, page, size, model, redirect);
    }
}
