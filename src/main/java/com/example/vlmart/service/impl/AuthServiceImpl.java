package com.example.vlmart.service.impl;

import com.example.vlmart.repo.UserRepository;
import com.example.vlmart.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public String backendLogin() {
        return "backend/auth/login";
    }

    @Override
    public String backendPostLogin() {
        return "backend/index";
    }
}
