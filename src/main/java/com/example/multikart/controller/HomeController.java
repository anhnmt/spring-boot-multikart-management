package com.example.multikart.controller;

import com.example.multikart.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private HomeService homeService;

    @GetMapping
    public String homePage(Model model) {
        return homeService.home(model);
    }

    @GetMapping("/search")
    public String search(@RequestParam("search") Optional<String> search, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size, Model model) {
        return homeService.search(search, page, size, model);
    }

}
