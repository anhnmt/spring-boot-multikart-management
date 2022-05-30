package com.example.vlmart.service.impl;

import com.example.vlmart.repo.RoleRepository;
import com.example.vlmart.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
}
