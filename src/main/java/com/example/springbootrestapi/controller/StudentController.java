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

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<StudentDto>> findAll() {
        return ResponseEntity.ok(studentService.findAllStudents());
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> createStudent(@RequestBody StudentDto studentDto) {
        StudentDto student = studentService.createStudent(studentDto);

        try {
            return ResponseEntity
                    .created(new URI("/api/student/"))
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(student);
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateStudentWithId(@PathVariable("id") Long id, @RequestBody StudentDto studentDto) {
        studentService.updateStudentWithId(id, studentDto);

        return ResponseEntity
                .ok("Student was successfully updated!");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteStudentWithId(@PathVariable("id") Long id) {
        studentService.deleteStudentWithId(id);

        return ResponseEntity
                .ok("Student was successfully deleted!");
    }
}
