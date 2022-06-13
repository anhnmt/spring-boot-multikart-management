package com.example.multikart.controller;

import com.example.multikart.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/dashboard")
public class ProductImageController {
    @Autowired
    private ProductImageService productImageService;

    @GetMapping("/products/{id}/images")
    public String index(@PathVariable("id") Long id, Model model, RedirectAttributes redirect) {
        return productImageService.findAllProductImages(id, model, redirect);
    }

    @PostMapping("/products/{id}/images")
    public String upload(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file, RedirectAttributes redirect) throws IOException {
        return productImageService.upload(id, file, redirect);
    }
}
