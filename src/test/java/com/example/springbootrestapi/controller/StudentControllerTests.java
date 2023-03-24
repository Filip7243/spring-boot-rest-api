package com.example.springbootrestapi.controller;

import com.example.springbootrestapi.dto.StudentDto;
import com.example.springbootrestapi.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StudentService studentService;

    private static final String API_PATH = "/api/student/";
    private StudentDto student;

    @BeforeEach
    void setUp() {
        student = new StudentDto();
        student.setAlbumId("00000");
        student.setFirstName("test");
        student.setLastName("test");
        student.setEmail("test");
    }

    @Test
    public void shouldReturnJSONWithListStudentDtoAndOkStatus() throws Exception {
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

    @Test
    public void shouldReturnStatusCreated() throws Exception {
        when(studentService.createStudent(student)).thenReturn(student);

        mvc.perform(post(API_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(student)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    public void shouldReturnMessageThatUserWasUpdatedAndStatusOk() throws Exception {
        Long id = anyLong();

        studentService.updateStudentWithId(id, eq(student));

        mvc.perform(put(API_PATH + "/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(student)))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnMessageThatUserWasDeletedAndStatusOk() throws Exception {
        Long id = anyLong();

        studentService.deleteStudentWithId(id);

        mvc.perform(delete(API_PATH + "/{id}", 1L))
                .andExpect(status().isOk());
    }
}
