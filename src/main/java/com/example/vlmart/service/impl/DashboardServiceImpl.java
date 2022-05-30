package com.example.vlmart.service.impl;

import com.example.vlmart.service.DashboardService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class DashboardServiceImpl implements DashboardService {
    @Override
    public String dashboard(Model model) {
        return "backend/index";
    }
}
