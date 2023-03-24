package com.example.springbootrestapi.controller;

import com.example.springbootrestapi.dto.CourseDto;
import com.example.springbootrestapi.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping(value = "/")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(courseService.findAllCourses());
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> createCourse(@RequestBody CourseDto courseDto) {
        CourseDto course = courseService.createCourse(courseDto);

        try {
            return ResponseEntity
                    .created(new URI("/api/course/"))
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(course);
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateCourseWithId(@PathVariable("id") Long id, @RequestBody CourseDto courseDto) {
        courseService.updateCourseWithId(id, courseDto);

        return ResponseEntity
                .ok("Course was successfully updated!");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteCourseWithId(@PathVariable("id") Long id) {
        courseService.deleteCourseWithId(id);

        return ResponseEntity
                .ok("Course was successfully deleted!");
    }
}
