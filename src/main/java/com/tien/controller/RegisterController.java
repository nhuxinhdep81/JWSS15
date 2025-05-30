package com.tien.controller;

import com.tien.dto.UserDTO;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @GetMapping("register_form")
    public String registerForm(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("userDTO", userDTO);
        System.out.println("UserDTO added to model: " + userDTO);
        return "register_form";
    }

    @PostMapping("register_save")
    public String registerSave(@Valid @ModelAttribute("userDTO") UserDTO userDTO
            , BindingResult bindingResult
            , Model model,
                               HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "register_form";
        }
        session.setAttribute("userDTO", userDTO);
        return "result";
    }
}
