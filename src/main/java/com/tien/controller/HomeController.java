package com.tien.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("home_page_b4")
    public String homePageB4(Model model) {
        return "home";
    }

    @GetMapping("about")
    public String home(Model model) {
        return "about";
    }

    @GetMapping("contact")
    public String contact(Model model) {
        return "contact";
    }
}
