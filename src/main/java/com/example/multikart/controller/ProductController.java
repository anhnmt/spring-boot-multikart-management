package com.example.multikart.controller;

import com.example.multikart.domain.dto.CategoryRequestDTO;
import com.example.multikart.domain.dto.ProductRequestDTO;
import com.example.multikart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/dashboard/products")
    public String index(Model model) {
        return productService.findAllProducts(model);
    }

    @GetMapping("/dashboard/products/create")
    public String create(Model model) {
        return productService.createProduct(model);
    }

    @PostMapping("/dashboard/products/create")
    public String store(@Valid @ModelAttribute("product") ProductRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect) {
        return productService.storeProduct(input, result, model, redirect);
    }

    @GetMapping("/dashboard/products/{id}")
    public String edit(@PathVariable("id") Long id, Model model, RedirectAttributes redirect) {
        return productService.editProduct(id, model, redirect);
    }

    @PostMapping("/dashboard/products/{id}")
    public String update(@PathVariable("id") Long id,@Valid @ModelAttribute("product") ProductRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect) {
        return productService.updateProduct(id, input, result, model, redirect);
    }

    @PostMapping("/dashboard/products/{id}/delete")
    public String delete(@PathVariable("id") Long id, Model model, RedirectAttributes redirect) {
        return productService.deleteProduct(id, model, redirect);
    }

    /**
     * FRONTEND SPACE
     */

    @GetMapping("/san-pham/{slug}")
    public String view(@PathVariable("slug") String slug, Model model, RedirectAttributes redirect) {
        return productService.frontendProduct(slug, model, redirect);
    }
}
