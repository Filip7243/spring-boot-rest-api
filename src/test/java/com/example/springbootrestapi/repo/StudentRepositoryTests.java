package com.example.springbootrestapi.repo;

import com.example.springbootrestapi.model.Course;
import com.example.springbootrestapi.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class StudentRepositoryTests {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;

    private Course c1;
    private Course c2;
    private Course c3;

    @BeforeEach
    void setUp() {
        Student s1 = new Student("00000", "John", "Doe", "john@mail.com");
        Student s2 = new Student("00001", "Mark", "Doe", "Mark@mail.com");
        Student s3 = new Student("00002", "Steve", "Doe", "Steve@mail.com");

        c1 = new Course("Course 1", "C1 description");
        c2 = new Course("Course 2", "C2 description");
        c3 = new Course("Course 3", "C3 description");

        studentRepository.save(s1);
        studentRepository.save(s2);
        studentRepository.save(s3);

        courseRepository.save(c1);
        courseRepository.save(c2);
        courseRepository.save(c3);

        s1.addCourseToStudent(c1);
        s1.addCourseToStudent(c2);

        s2.addCourseToStudent(c2);
        s2.addCourseToStudent(c3);
        s2.addCourseToStudent(c1);
    }

    @Test
    public void itShouldFindStudentsBySpecificCourse() {
        List<Student> foundStudents = studentRepository.findStudentsByCoursesContaining(c1);

        assertThat(foundStudents).isNotNull();
        assertThat(foundStudents.size()).isEqualTo(2);
    }
}
