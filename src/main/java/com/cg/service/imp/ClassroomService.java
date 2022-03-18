package com.cg.service.imp;


import com.cg.model.Classroom;
import com.cg.repository.IClassroomRepository;
import com.cg.service.IClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClassroomService implements IClassroomService {
    @Autowired
    IClassroomRepository iClassroomRepository;


    @Override
    public Iterable<Classroom> findAll() {
        return iClassroomRepository.findAll();
    }

    @Override
    public Optional<Classroom> findById(Long id) {
        return iClassroomRepository.findById(id);
    }
}
