package com.example.springbootrestapi.controller;

import com.example.springbootrestapi.dto.StudentDto;
import com.example.springbootrestapi.service.StudentService;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StudentService studentService;

    private static final String API_PATH = "/api/student/";

    @Test
    public void shouldReturnJSONWithListStudentDtoAndOkStatus() throws Exception {
        StudentDto student = new StudentDto();
        student.setAlbumId("00000");
        student.setFirstName("test");
        student.setLastName("test");
        student.setEmail("test");

        when(studentService.findAllStudents()).thenReturn(List.of(student));

        mvc.perform(get(API_PATH))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].albumId", is("00000")))
                .andExpect(jsonPath("$[0].firstName", is("test")));
    }

    @Test
    public void shouldReturnEmptyJSONAndOkStatus() throws Exception {
        when(studentService.findAllStudents()).thenReturn(List.of());

        mvc.perform(get(API_PATH))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));
    }
}