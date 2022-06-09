package com.example.multikart.controller.frontend;

import com.example.multikart.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private HomeService homeService;

    @GetMapping
    public String homePage(Model model) {
        return homeService.home(model);
    }

}
