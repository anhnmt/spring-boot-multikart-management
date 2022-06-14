package com.example.multikart.service;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

public interface ProductImageService {

    String findAllProductImages(Long id, Model model, RedirectAttributes redirect);

    String upload(Long id, MultipartFile file, RedirectAttributes redirect) throws IOException;

    String delete(Long productId, Long productImageId, RedirectAttributes redirect);

    String up(Long productId, Long productImageId, RedirectAttributes redirect);

    String down(Long productId, Long productImageId, RedirectAttributes redirect);
}
