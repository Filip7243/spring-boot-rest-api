package com.example.springbootrestapi.service;

import com.example.springbootrestapi.dto.StudentDto;
import com.example.springbootrestapi.mapper.StudentMapper;
import com.example.springbootrestapi.model.Course;
import com.example.springbootrestapi.model.Student;
import com.example.springbootrestapi.repo.CourseRepository;
import com.example.springbootrestapi.repo.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTests {

    @Mock
    private StudentRepository studentRepository;
    @Mock
    private CourseRepository courseRepository;

    private StudentService studentService;

    @BeforeEach
    void setUp() {
        this.studentService = new StudentService(studentRepository, courseRepository);
    }

    @Test
    public void canUpdateStudentWithId() {
        Student student = new Student();
        StudentDto updatedStudent = new StudentDto();
        updatedStudent.setFirstName("test");
        Long anyId = anyLong();

        when(studentRepository.findById(anyId)).thenReturn(Optional.of(student));

        studentService.updateStudentWithId(anyId, updatedStudent);

        verify(studentRepository).save(student);
        assertEquals("test", student.getFirstName());
    }

    @Test
    public void throwsExceptionWhenUpdateNonExistingStudent() {
        StudentDto student = new StudentDto();
        Long anyId = anyLong();

        when(studentRepository.findById(anyId)).thenReturn(Optional.empty());

        RuntimeException ex =
                assertThrows(RuntimeException.class, () -> studentService.updateStudentWithId(anyId, student));

        assertEquals("Student with id: " + anyId + "not found!", ex.getMessage());
    }

    @Test
    public void canDeleteStudent() {
        Student student = new Student();
        Long anyId = anyLong();

        when(studentRepository.findById(anyId)).thenReturn(Optional.of(student));

        studentService.deleteStudentWithId(anyId);

        verify(studentRepository).delete(student);
    }

    @Test
    public void throwsExceptionWhenDeleteNonExistingStudent() {
        Long anyId = anyLong();

        when(studentRepository.findById(anyId)).thenReturn(Optional.empty());

        RuntimeException ex =
                assertThrows(RuntimeException.class, () -> studentService.deleteStudentWithId(anyId));

        assertEquals("Student with id: " + anyId + "not found!", ex.getMessage());
    }

    @Test
    public void canFindStudentsByCourseId() {
        Course course = new Course();
        Long anyId = anyLong();
        Student student = new Student();

        when(courseRepository.findById(anyId)).thenReturn(Optional.of(course));
        when(studentRepository.findStudentsByCoursesContaining(course)).thenReturn(List.of(student));

        List<StudentDto> foundStudents = studentService.findStudentsByCourseId(anyId);

        assertEquals(1, foundStudents.size());
    }

    @Test
    public void throwsExceptionWhenFindStudentByNonExistingCourseId() {
        Long anyId = anyLong();

        when(courseRepository.findById(anyId)).thenReturn(Optional.empty());

        RuntimeException ex =
                assertThrows(RuntimeException.class, () -> studentService.findStudentsByCourseId(anyId));

        assertEquals("Course with id: " + anyId + " not found!", ex.getMessage());
    }

    @Test
    public void canFindStudentsWithoutCourses() {
        Student student = new Student();

        when(studentRepository.findStudentsWithNoCoursesEnrolled()).thenReturn(List.of(student));

        List<StudentDto> foundStudents = studentService.findStudentsWithoutCourses();

        assertEquals(1, foundStudents.size());
    }

}
