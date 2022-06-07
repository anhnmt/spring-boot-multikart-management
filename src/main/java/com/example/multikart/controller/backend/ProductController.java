package com.example.multikart.controller.backend;

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
@RequestMapping("/dashboard/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String index(Model model) {
        return productService.findAllProducts(model);
    }

    @GetMapping("/create")
    public String create(Model model) {
        return productService.createProduct(model);
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("product") ProductRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect) {
        return productService.storeProduct(input, result, model, redirect);
    }

    @GetMapping("/{id}")
    public String edit(@PathVariable("id") Long id, Model model, RedirectAttributes redirect) {
        return productService.editProduct(id, model, redirect);
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") Long id,@Valid @ModelAttribute("product") ProductRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect) {
        return productService.updateProduct(id, input, result, model, redirect);
    }

    @PostMapping("{id}/delete")
    public String delete(@PathVariable("id") Long id, Model model, RedirectAttributes redirect) {
        return productService.deleteProduct(id, model, redirect);
    }
}
