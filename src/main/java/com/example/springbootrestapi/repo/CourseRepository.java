package com.example.springbootrestapi.repo;

import com.example.springbootrestapi.model.Course;
import com.example.springbootrestapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query(value = "SELECT * FROM Course c WHERE c.id IN (SELECT course_id FROM students_courses sc WHERE sc.student_id = :#{#student.id})",
            nativeQuery = true)
    List<Course> findCoursesByStudent(@Param("student") Student student);

    @Query(value = "SELECT * FROM Course c WHERE c.id NOT IN (SELECT course_id FROM students_courses)", nativeQuery = true)
    List<Course> findCoursesWithoutStudents();
}
