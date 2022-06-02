package com.example.multikart.service.impl;

import com.example.multikart.repo.ProductImageRepository;
import com.example.multikart.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductImageServiceImpl implements ProductImageService {
    @Autowired
    private ProductImageRepository productImageRepository;
}
