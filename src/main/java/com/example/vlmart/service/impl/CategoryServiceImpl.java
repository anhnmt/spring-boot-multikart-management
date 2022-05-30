package com.example.vlmart.service.impl;

import com.example.vlmart.repo.CategoryRepository;
import com.example.vlmart.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
}
