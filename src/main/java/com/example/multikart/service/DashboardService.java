package com.example.multikart.service;

import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

public interface DashboardService {
    String dashboard(HttpSession session, Model model);
}
