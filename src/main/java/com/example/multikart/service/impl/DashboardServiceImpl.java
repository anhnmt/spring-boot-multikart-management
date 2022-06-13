package com.example.multikart.service.impl;

import com.example.multikart.service.DashboardService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class DashboardServiceImpl implements DashboardService {
    @Override
    public String dashboard(Model model) {
        return "backend/index";
    }
}
