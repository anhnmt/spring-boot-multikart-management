package com.example.vlmart.service.impl;

import com.example.vlmart.repo.ProductRepository;
import com.example.vlmart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
}
