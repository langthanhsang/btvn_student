package com.cg.repository;

import com.cg.model.Classroom;
import com.cg.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentRepository extends PagingAndSortingRepository<Student,Long> {
    Page<Student> findAllByClassroom(Pageable pageable, Classroom classroom);

    Page<Student> findAllByNameContaining(Pageable pageable,String name);
}
