package com.example.springbootrestapi.repo;

import com.example.springbootrestapi.model.Course;
import com.example.springbootrestapi.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

    List<Student> findStudentsByCoursesContaining(Course course);

    @Query(value = "SELECT * FROM Student s WHERE s.id NOT IN(SELECT student_id FROM students_courses)",
            nativeQuery = true)
    List<Student> findStudentsWithNoCoursesEnrolled();
}
