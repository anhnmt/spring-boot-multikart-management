package com.example.multikart.service.impl;

import com.example.multikart.repo.CategoryRepository;
import com.example.multikart.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
}
