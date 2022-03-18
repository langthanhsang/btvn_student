package com.cg.service.imp;


import com.cg.model.Classroom;
import com.cg.model.Student;
import com.cg.repository.IStudentRepository;
import com.cg.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService implements IStudentService {

    @Autowired
    private IStudentRepository iStudentRepository;


    @Override
    public Page<Student> findAll(Pageable pageable) {
        return iStudentRepository.findAll(pageable);
    }

    @Override
    public Page<Student> findAllByName(Pageable pageable, String name) {
        return iStudentRepository.findAllByNameContaining(pageable, name);
    }


    @Override
    public Optional<Student> findById(Long id) {
        return iStudentRepository.findById(id);
    }

    @Override
    public void save(Student student) {
        iStudentRepository.save(student);
    }

    @Override
    public void delete(Long id) {
        iStudentRepository.deleteById(id);
    }

    @Override
    public Page<Student> findAllByClassroom(Pageable pageable, Classroom classroom) {
        return iStudentRepository.findAllByClassroom(pageable,classroom);
    }
}
