package com.example.springbootrestapi.service;

import com.example.springbootrestapi.dto.CourseDto;
import com.example.springbootrestapi.model.Course;
import com.example.springbootrestapi.model.Student;
import com.example.springbootrestapi.repo.CourseRepository;
import com.example.springbootrestapi.repo.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.springbootrestapi.mapper.CourseMapper.mapToCourse;
import static com.example.springbootrestapi.mapper.CourseMapper.mapToCourseDtoList;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public CourseService(CourseRepository courseRepository,
                         StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    public Course createCourse(CourseDto courseDto) {
        Course course = mapToCourse(courseDto);

        return courseRepository.save(course);
    }

    public void updateCourseWithId(Long id, CourseDto updatedCourse) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found!"));
        course.setName(updatedCourse.getName());
        course.setDescription(updatedCourse.getDescription());

        courseRepository.save(course);
    }

    public void deleteCourseWithId(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found!"));

        courseRepository.delete(course);
    }

    public List<CourseDto> findAllCourses() {
        return mapToCourseDtoList(courseRepository.findAll());
    }


    public List<CourseDto> findCoursesByStudentId(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found!"));

        List<Course> coursesByStudent = courseRepository.findCoursesByStudent(student);

        return mapToCourseDtoList(coursesByStudent);
    }

    public List<CourseDto> findCoursesWithoutStudents() {
        return mapToCourseDtoList(courseRepository.findCoursesWithoutStudents());
    }


}
