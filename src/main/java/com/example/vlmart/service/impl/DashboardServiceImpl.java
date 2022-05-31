package com.example.vlmart.service.impl;

import com.example.vlmart.service.DashboardService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

@Service
public class DashboardServiceImpl implements DashboardService {
    @Override
    public String dashboard(HttpSession session, Model model) {
        return "backend/index";
    }
}
