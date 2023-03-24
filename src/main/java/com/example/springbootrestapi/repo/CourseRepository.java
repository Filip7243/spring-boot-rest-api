package com.example.springbootrestapi.repo;

import com.example.springbootrestapi.model.Course;
import com.example.springbootrestapi.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {

    List<Course> findCoursesByStudent(Student student);
    List<Course> findCoursesWithoutStudents();
}
