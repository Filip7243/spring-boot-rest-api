package com.example.springbootrestapi.service;

import com.example.springbootrestapi.repo.CourseRepository;
import com.example.springbootrestapi.repo.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

    }
}
