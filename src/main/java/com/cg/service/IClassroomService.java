package com.cg.service;

import com.cg.model.Classroom;

import java.util.Optional;

public interface IClassroomService {


    Iterable<Classroom> findAll();

    Optional<Classroom> findById(Long id);
}
