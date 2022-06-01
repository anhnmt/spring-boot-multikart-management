package com.example.multikart.service.impl;

import com.example.multikart.repo.RoleRepository;
import com.example.multikart.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
}
