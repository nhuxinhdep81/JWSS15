package com.tien.controller;

import com.tien.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentController {

    List<Student> studentList = new ArrayList<>();

    public StudentController() {
        Student student1 = new Student("SV001", "Tien", 20, "12A13", "ldt@gmail.com", "TN","0394882659");
        Student student2 = new Student("SV002", "Manh", 25, "10A1", "manh@gmail.com", "TN","0912664172");
        Student student3 = new Student("SV003", "Hieu", 20, "11A7", "hieunopro@gmail.com", "TN","099887766");
    studentList.add(student1);
    studentList.add(student2);
    studentList.add(student3);
    }

    @GetMapping("/show_list")
    public String showList(Model model) {
        model.addAttribute("studentList", studentList);

        return "studentList";
    }


}
