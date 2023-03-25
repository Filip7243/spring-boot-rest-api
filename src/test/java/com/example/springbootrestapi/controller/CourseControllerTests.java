package com.example.springbootrestapi.controller;

import com.example.springbootrestapi.dto.CourseDto;
import com.example.springbootrestapi.service.CourseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

    @Test
    public void shouldReturnJSONWithListCourseDtoAndOkStatus() throws Exception {
        when(courseService.findAllCourses()).thenReturn(List.of(course));

        mvc.perform(get(API_PATH))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("test")))
                .andExpect(jsonPath("$[0].description", is("test")));
    }

    @Test
    public void shouldReturnStatusCreated() throws Exception {
        when(courseService.createCourse(course)).thenReturn(course);

        mvc.perform(post(API_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(course)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    public void shouldReturnMessageThatCourseWasUpdatedAndStatusOk() throws Exception {
        Long id = anyLong();

        courseService.updateCourseWithId(id, eq(course));

        mvc.perform(put(API_PATH + "/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(course)))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnMessageThatCourseWasDeletedAndStatusOk() throws Exception {
        Long id = anyLong();

        courseService.deleteCourseWithId(id);

        mvc.perform(delete(API_PATH + "/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnJSONWithStudentWithCoursesEnrolledAndStatusOk() throws Exception {
        when(courseService.findCoursesByStudentId(1L)).thenReturn(List.of(course));

        mvc.perform(get("/api/course?studentId=1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void shouldReturnJSONWithStudentWithoutCoursesAndStatusOk() throws Exception {
        when(courseService.findCoursesWithoutStudents()).thenReturn(List.of(course));

        mvc.perform(get("/api/course/without-students"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)));
    }
}
