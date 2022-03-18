package com.cg.repository;

import com.cg.model.Classroom;
import com.cg.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClassroomRepository extends PagingAndSortingRepository<Classroom, Long> {

}
