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
public class CourseRepositoryTests {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;

    private Student s1;
    private Student s2;
    private Student s3;
    private Course c1;
    private Course c2;
    private Course c3;
    private Course c4;

    @BeforeEach
    void setUp() {
        s1 = new Student("00000", "John", "Doe", "john@mail.com");
        s2 = new Student("00001", "Mark", "Doe", "Mark@mail.com");
        s3 = new Student("00002", "Steve", "Doe", "Steve@mail.com");

        c1 = new Course("Course 1", "C1 description");
        c2 = new Course("Course 2", "C2 description");
        c3 = new Course("Course 3", "C3 description");
        c4 = new Course("Course 4", "C4 description");

        studentRepository.save(s1);
        studentRepository.save(s2);
        studentRepository.save(s3);

        courseRepository.save(c1);
        courseRepository.save(c2);
        courseRepository.save(c3);
        courseRepository.save(c4);

        s1.addCourseToStudent(c1);
        s1.addCourseToStudent(c2);

        s2.addCourseToStudent(c2);
        s2.addCourseToStudent(c3);
        s2.addCourseToStudent(c1);
    }

    @Test
    public void itShouldFindCoursesThatSpecificStudentEnrolled() {
        List<Course> foundCourses = courseRepository.findCoursesByStudent(s1);

        makeSureThatListHasSizeOf(foundCourses, 2);
        assertThat(foundCourses.get(0).getName()).isEqualTo(c1.getName());
    }

    @Test
    public void itShouldNotFindCoursesThatSpecificStudentEnrolled() {
        s1.removeCourseFromStudent(c1);
        s1.removeCourseFromStudent(c2);

        List<Course> foundCourses = courseRepository.findCoursesByStudent(s1);

        makeSureThatListHasSizeOf(foundCourses, 0);
    }

    @Test
    public void itShouldFindCourseWithoutStudentsEnrolled() {
        List<Course> foundCourses = courseRepository.findCoursesWithoutStudents();

        makeSureThatListHasSizeOf(foundCourses, 1);
    }

    @Test
    public void itShouldNotFindCoursesWithoutStudentsEnrolled() {
        s1.addCourseToStudent(c4);

        List<Course> foundCourses = courseRepository.findCoursesWithoutStudents();

        makeSureThatListHasSizeOf(foundCourses, 0);
    }

    private static void makeSureThatListHasSizeOf(List<Course> foundCourses, int expected) {
        assertThat(foundCourses).isNotNull();
        assertThat(foundCourses.size()).isEqualTo(expected);
    }
}
