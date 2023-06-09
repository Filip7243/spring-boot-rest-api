package com.example.springbootrestapi.controller;

import com.example.springbootrestapi.dto.StudentDto;
import com.example.springbootrestapi.model.Course;
import com.example.springbootrestapi.model.Student;
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

    public StudentController(StudentService studentService,
                             CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
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

    //TODO: register to course
    @PostMapping("/enroll/{courseId}")
    public ResponseEntity<?> enrollCourse(@PathVariable("courseId") Long courseId, @RequestBody StudentDto student) {
        Course foundCourse = courseService.findCourseById(courseId);
        Student foundStudent = studentService.findStudentByAlbumId(student.getAlbumId());
        int sizeBeforeAdding = foundStudent.getCourses().size();

        studentService.addStudentToCourse(foundCourse, foundStudent);

        try {
            if (sizeBeforeAdding == foundStudent.getCourses().size()) {
                return ResponseEntity.status(409).body("User already in course");
            }

            return ResponseEntity.created(new URI("/api/student/"))
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(student);
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping()
    public ResponseEntity<?> findStudentsWithCourse(@RequestParam("courseId") Long courseId) {
        List<StudentDto> studentsByCourseId = studentService.findStudentsByCourseId(courseId);

        return ResponseEntity.ok(studentsByCourseId);
    }

    @GetMapping("/without-courses")
    public ResponseEntity<?> findStudentsWithoutCourses() {
        return ResponseEntity.ok(studentService.findStudentsWithoutCourses());
    }

}
