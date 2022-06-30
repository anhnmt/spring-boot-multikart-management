package com.example.multikart.service;

import org.springframework.ui.Model;

import java.util.Optional;

public interface HomeService {
    String home(Model model);

    String search(Optional<String> search, Optional<Integer> page, Optional<Integer> size, Model model);
}
