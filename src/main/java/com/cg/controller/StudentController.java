package com.cg.controller;

import com.cg.model.Classroom;
import com.cg.model.Student;
import com.cg.service.IClassroomService;
import com.cg.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private IClassroomService iClassroomService;

    @Autowired
   private IStudentService iStudentService;

    @ModelAttribute(name = "classes")
    public Iterable<Classroom> findAdd(){
        return iClassroomService.findAll();
    }

    @GetMapping("/list")
    public ModelAndView showAll(@PageableDefault(value = 3) Pageable pageable,
                                @RequestParam Optional<String> search)
                                 {
        ModelAndView modelAndView = new ModelAndView("student/list");
        Page<Student> students;
        if (search.isPresent()) {
            students = iStudentService.findAllByName(pageable,search.get());
            modelAndView.addObject("search", search.get());
        } else {
           students = iStudentService.findAll(pageable);
        }
        modelAndView.addObject("students", students);
        return modelAndView;
    }


//    @GetMapping
//    public ModelAndView display(@SortDefault(sort = {"point_avg"},
//            direction = Sort.Direction.ASC)@PageableDefault(3) Pageable pageable
//            , @RequestParam("search")Optional<String> key) {
//        ModelAndView modelAndView = new ModelAndView("student/list");
//
//        Page<Student> students;
//
//        if(key.isPresent()){
//            students =iStudentService.findAllByName(pageable, key.get());
//            modelAndView.addObject("students", students);
//            modelAndView.addObject("check", false);
//        } else {
//            students = iStudentService.findAll(pageable);
//            modelAndView.addObject("students", students);
//            modelAndView.addObject("check", true);
//        }
//
//        return modelAndView;
//    }


    @GetMapping("/create")
    public ModelAndView create(){
        ModelAndView modelAndView = new ModelAndView("student/create");
        modelAndView.addObject("student" , new Student());
        return modelAndView;
    }

    @PostMapping("/create")
    public String createStudent( @ModelAttribute("student") Student student,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasFieldErrors()) {
            model.addAttribute("student", student);
            return "student/create";
        }
        iStudentService.save(student);
        return "redirect:/student/list";
    }
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("student/edit");
        Optional<Student> student = iStudentService.findById(id);
        student.ifPresent(value -> modelAndView.addObject("student", value));
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public String editStudent(@Valid @ModelAttribute("student") Student student,
                              BindingResult bindingResult, Model model,
                              @PathVariable("id") Long id) {
        if (bindingResult.hasFieldErrors()) {
            model.addAttribute("student", student);
            return "student/edit";
        }
        student.setId(id);
        iStudentService.save(student);
        return "redirect:/student/list";
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PageableDefault(value = 3) Pageable pageable, @PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("student/list");
        iStudentService.delete(id);
        Page<Student> students = iStudentService.findAll(pageable);
        modelAndView.addObject("students", students);
        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public ModelAndView showDetail(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("student/detail");
        Optional<Student> student = iStudentService.findById(id);
        student.ifPresent(value -> modelAndView.addObject("student", value));
        return modelAndView;
    }


}
