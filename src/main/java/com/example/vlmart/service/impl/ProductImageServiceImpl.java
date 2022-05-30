package com.example.vlmart.service.impl;

import com.example.vlmart.repo.ProductImageRepository;
import com.example.vlmart.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductImageServiceImpl implements ProductImageService {
    @Autowired
    private ProductImageRepository productImageRepository;
}
