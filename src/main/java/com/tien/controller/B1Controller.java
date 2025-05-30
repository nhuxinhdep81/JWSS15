package com.tien.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class B1Controller {

    @GetMapping("/show_home")
    public String homeb1(Model model) {
        model.addAttribute("message", "Hello, Thymeleaf!");
        return "b1";
    }
}
