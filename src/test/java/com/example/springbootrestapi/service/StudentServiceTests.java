package com.example.springbootrestapi.service;

import com.example.springbootrestapi.repo.CourseRepository;
import com.example.springbootrestapi.repo.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    public void canCreateStudent() {
    }
}
