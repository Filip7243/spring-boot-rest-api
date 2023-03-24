package com.example.springbootrestapi.controller;

import com.example.springbootrestapi.dto.CourseDto;
import com.example.springbootrestapi.dto.StudentDto;
import com.example.springbootrestapi.service.CourseService;
import com.example.springbootrestapi.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class CourseControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CourseService courseService;

    private static final String API_PATH = "/api/course/";
    private CourseDto course;

    @BeforeEach
    void setUp() {
        course = new CourseDto();
        course.setName("test");
        course.setDescription("test");
    }
}
