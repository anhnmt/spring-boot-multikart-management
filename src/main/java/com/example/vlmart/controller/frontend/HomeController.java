package com.example.vlmart.controller.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String homePage() {
        return "frontend/index";
    }

    @GetMapping("/contact")
    public String contactPage() {
        return "frontend/contact";
    }

    @GetMapping("/about")
    public String aboutPage() {
        return "frontend/about";
    }

}
