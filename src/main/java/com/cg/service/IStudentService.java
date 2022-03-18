package com.cg.service;


import com.cg.model.Classroom;
import com.cg.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface IStudentService {

    Page<Student> findAll(Pageable pageable);

    Page<Student> findAllByName(Pageable pageable, String name);

    Optional<Student> findById(Long id);

    void save(Student student);

    void delete(Long id);

    Page<Student>findAllByClassroom(Pageable pageable, Classroom classroom);
}
