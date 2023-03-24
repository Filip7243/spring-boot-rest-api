package com.example.springbootrestapi.repo;

import com.example.springbootrestapi.model.Course;
import com.example.springbootrestapi.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

    List<Student> findStudentsByCourse(Course course);

    List<Student> findStudentsWithoutAnyCourses();
}
