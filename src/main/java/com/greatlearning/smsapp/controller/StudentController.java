package com.greatlearning.smsapp.controller;

import com.greatlearning.smsapp.entity.Student;
import com.greatlearning.smsapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping("/list")
    public String listStudents(Model theModel) {

        List<Student> students = studentService.findAll();
        theModel.addAttribute("students",students);
        return "list-students"; // /WEB-INF/views/list-students.jsp

    }
    @RequestMapping("/showFormForAdd")
    public String showFormforAdd(Model theModel) {
        Student theStudent = new Student();

        theModel.addAttribute("Student",theStudent);

        return "Student-form";

    }
    @RequestMapping("/showFormForUpdate")
    public String showFormforUpdate(@RequestParam("studentId") int id,Model theModel) {
        Student theStudent = studentService.findById(id);

        theModel.addAttribute("Student",theStudent);

        return "Student-form";

    }
    @PostMapping("/save")
    public String saveStudents(@RequestParam("id") int id,
                           @RequestParam("fname") String fname,@RequestParam("lname") String lname,@RequestParam("course") String course,@RequestParam("country") String country) {

        System.out.println(id);

        Student theStudent;

        if(id!=0) {
            theStudent  = studentService.findById(id);
            theStudent.setFname(fname);
            theStudent.setLname(lname);
            theStudent.setCountry(country);
            theStudent.setCourse(course);
        }
        else
            theStudent = new Student(fname,lname,course,country);
        studentService.save(theStudent);

        return "redirect:/students/list";

    }


    @RequestMapping("/delete")
    public String delete(@RequestParam("studentId") int theId) {

        // delete the Student
        studentService.deleteById(theId);

        // redirect to /Students/list
        return "redirect:/students/list";

    }
}
