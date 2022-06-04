package com.example.multikart.controller.backend;

import com.example.multikart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String index(Model model) {
        return productService.findAllProducts(model);
    }
}
