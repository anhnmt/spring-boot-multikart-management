package com.example.vlmart.service;

import com.example.vlmart.domain.model.User;
import org.springframework.ui.Model;

public interface UserService {
    String findAllUsers(Model model);
    String storeUser(User user);
}
