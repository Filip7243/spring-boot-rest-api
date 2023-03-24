package com.example.springbootrestapi.controller;

import com.example.springbootrestapi.dto.StudentDto;
import com.example.springbootrestapi.service.CourseService;
import com.example.springbootrestapi.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private StudentService studentService;
    private CourseService courseService;

    public StudentController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @GetMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<StudentDto>> findAll() {
        return ResponseEntity.ok(studentService.findAllStudents());
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> createUser(@RequestBody StudentDto studentDto) {
        StudentDto student = studentService.createStudent(studentDto);

        try {
            return ResponseEntity
                    .created(new URI("/api/students/"))
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("CREATED");
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
