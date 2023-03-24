package com.example.springbootrestapi.service;

import com.example.springbootrestapi.dto.CourseDto;
import com.example.springbootrestapi.model.Course;
import com.example.springbootrestapi.model.Student;
import com.example.springbootrestapi.repo.CourseRepository;
import com.example.springbootrestapi.repo.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTests {

    @Mock
    private CourseRepository courseRepository;
    @Mock
    private StudentRepository studentRepository;

    private CourseService courseService;

    @BeforeEach
    void setUp() {
        this.courseService = new CourseService(courseRepository, studentRepository);
    }

    @Test
    public void canUpdateCourseWithId() {
        Course course = new Course();
        CourseDto updatedCourse = new CourseDto();
        updatedCourse.setName("test");
        updatedCourse.setDescription("test");
        Long anyId = anyLong();

        when(courseRepository.findById(anyId)).thenReturn(Optional.of(course));

        courseService.updateCourseWithId(anyId, updatedCourse);

        verify(courseRepository).save(course);
        assertEquals("test", course.getName());
    }

    @Test
    public void throwsExceptionWhenUpdateNonExistingCourse() {
        Long anyId = anyLong();

        when(courseRepository.findById(anyId)).thenReturn(Optional.empty());

        RuntimeException ex =
                assertThrows(RuntimeException.class, () -> courseService.updateCourseWithId(anyId, new CourseDto()));

        assertEquals("Course not found!", ex.getMessage());
    }

    @Test
    public void canDeleteCourseWithId() {
        Course course = new Course();
        Long anyId = anyLong();

        when(courseRepository.findById(anyId)).thenReturn(Optional.of(course));

        courseService.deleteCourseWithId(anyId);

        verify(courseRepository).delete(course);
    }

    @Test
    public void throwsExceptionWhenDeleteNonExistingCourse() {
        Long anyId = anyLong();

        when(courseRepository.findById(anyId)).thenReturn(Optional.empty());

        RuntimeException ex =
                assertThrows(RuntimeException.class, () -> courseService.deleteCourseWithId(anyId));

        assertEquals("Course not found!", ex.getMessage());
    }

    @Test
    public void canFindCoursesByStudentId() {
        Student student = new Student();
        Course course = new Course();
        List<Course> studentCourses = List.of(course);
        Long anyId = anyLong();

        when(studentRepository.findById(anyId)).thenReturn(Optional.of(student));
        when(courseRepository.findCoursesByStudent(student)).thenReturn(studentCourses);

        List<CourseDto> coursesByStudentId = courseService.findCoursesByStudentId(anyId);

        assertEquals(studentCourses.size(), coursesByStudentId.size());
    }

    @Test
    public void canFindCoursesWithoutStudents() {
        List<Course> any = List.of(new Course());

        when(courseRepository.findCoursesWithoutStudents()).thenReturn(any);

        List<CourseDto> coursesWithoutStudents = courseService.findCoursesWithoutStudents();

        assertEquals(any.size(), coursesWithoutStudents.size());
    }
}
