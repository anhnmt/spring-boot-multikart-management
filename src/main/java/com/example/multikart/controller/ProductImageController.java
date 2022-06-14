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

    @PostMapping("/products/{productId}/images/{productImageId}/delete")
    public String delete(@PathVariable("productId") Long productId, @PathVariable("productImageId") Long productImageId, RedirectAttributes redirect) throws IOException {
        return productImageService.delete(productId, productImageId, redirect);
    }

    @PostMapping("/products/{productId}/images/{productImageId}/up")
    public String up(@PathVariable("productId") Long productId, @PathVariable("productImageId") Long productImageId, RedirectAttributes redirect) throws IOException {
        return productImageService.up(productId, productImageId, redirect);
    }

    @PostMapping("/products/{productId}/images/{productImageId}/down")
    public String down(@PathVariable("productId") Long productId, @PathVariable("productImageId") Long productImageId, RedirectAttributes redirect) throws IOException {
        return productImageService.down(productId, productImageId, redirect);
    }
}
