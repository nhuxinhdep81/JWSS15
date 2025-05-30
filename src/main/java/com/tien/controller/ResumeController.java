package com.tien.controller;

import com.tien.dto.ResumeDTO;
import com.tien.service.ResumeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @GetMapping("/show_list_resume")
    public String showList(Model model) {
        List<ResumeDTO> resumeDTOList = resumeService.getAll();
        model.addAttribute("resumeDTOList", resumeDTOList);
        return "list_resume";
    }

    @GetMapping("form_add_resume")
    public String showFormAddResume(Model model) {
        ResumeDTO resumeDTO = new ResumeDTO();
        model.addAttribute("resumeDTO", resumeDTO);
        return "add_resume";
    }

    @PostMapping("resume_save")
    public String saveResume(@Valid @ModelAttribute("resumeDTO") ResumeDTO resumeDTO,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add_resume";
        }
        resumeService.save(resumeDTO);
        return "redirect:/show_list_resume";
    }

    @GetMapping("form_update_resume/{id}")
    public String showFormUpdateResume(@PathVariable("id") int id, Model model) {
        ResumeDTO resumeDTO = resumeService.getById(id);
        if (resumeDTO == null) {
            return "redirect:/show_list_resume";
        }
        model.addAttribute("resumeDTO", resumeDTO);
        return "update_resume";
    }

    @PostMapping("form_update_resume/resume_update")
    public String updateForm(@Valid @ModelAttribute("resumeDTO") ResumeDTO resumeDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "update_resume";
        }
        resumeService.update(resumeDTO);
        return "redirect:/show_list_resume";
    }

    @GetMapping("delete_resume/{id}")
    public String deleteResume(@PathVariable("id") int id) {
        resumeService.delete(id);
        return "redirect:/show_list_resume";
    }
}
