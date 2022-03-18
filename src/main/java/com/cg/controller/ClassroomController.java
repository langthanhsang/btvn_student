package com.cg.controller;

import com.cg.model.Classroom;
import com.cg.model.Student;
import com.cg.service.IClassroomService;
import com.cg.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


@Controller
@RequestMapping(value = "/classroom")
public class ClassroomController {

    @Autowired
    private IClassroomService iClassroomService;

    @Autowired
    private IStudentService iStudentService;


    @ModelAttribute(name = "classes")
    private Iterable<Classroom> findAdd() {
        return iClassroomService.findAll();
    }

    @GetMapping
    public ModelAndView showListClass() {
        return new ModelAndView("classroom/list-class");
    }

    @GetMapping("/student-in")
    public ModelAndView showAll(@PageableDefault(value = 3) Pageable pageable,
                                Optional<Long> class_id) {
        ModelAndView modelAndView = new ModelAndView("classroom/student-in-class");
        Optional<Classroom> classroom = iClassroomService.findById(class_id.orElse(0L));
        Page<Student> students = iStudentService.findAllByClassroom(
                pageable, classroom.orElse(new Classroom()));
        modelAndView.addObject("class_id", class_id.orElse(0L));
        modelAndView.addObject("students", students);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PageableDefault(value = 3) Pageable pageable, @PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("student/list");
        iStudentService.delete(id);
        Page<Student> students = iStudentService.findAll(pageable);
        modelAndView.addObject("students", students);
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("student/edit");
        Optional<Student> student = iStudentService.findById(id);
        student.ifPresent(value -> modelAndView.addObject("student", value));
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public String editStudent(@ModelAttribute("student") Student student,
                              BindingResult bindingResult, Model model,
                              @PathVariable("id") Long id) {
        if (bindingResult.hasFieldErrors()) {
            model.addAttribute("student", student);
            return "student/edit";
        }
        student.setId(id);
        iStudentService.save(student);
        return "redirect:/student";
    }

}
