package com.example.multikart.service;

import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

public interface CartService {

    String view(HttpSession session, Model model);
}