package com.example.springbootrestapi.service;

import com.example.springbootrestapi.dto.CourseDto;
import com.example.springbootrestapi.dto.StudentDto;
import com.example.springbootrestapi.exception.CourseNotFoundException;
import com.example.springbootrestapi.exception.StudentNotFoundException;
import com.example.springbootrestapi.model.Course;
import com.example.springbootrestapi.model.Student;
import com.example.springbootrestapi.repo.CourseRepository;
import com.example.springbootrestapi.repo.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.springbootrestapi.mapper.StudentMapper.mapStudentDtoToStudent;
import static com.example.springbootrestapi.mapper.StudentMapper.mapToStudentDtoList;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentService(StudentRepository studentRepository,
                          CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public Student createStudent(StudentDto studentDto) {
        Student student = mapStudentDtoToStudent(studentDto);

        return studentRepository.save(student);
    }

    public void updateStudentWithId(Long id, StudentDto updatedStudent) {
        Student foundStudent = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        foundStudent.setAlbumId(updatedStudent.getAlbumId());
        foundStudent.setFirstName(updatedStudent.getFirstName());
        foundStudent.setLastName(updatedStudent.getLastName());
        foundStudent.setEmail(updatedStudent.getEmail());

        studentRepository.save(foundStudent);
    }

    public void deleteStudentWithId(Long id) {
        Student foundStudent = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));

        studentRepository.delete(foundStudent);
    }

    public List<StudentDto> findAllStudents() {
        return mapToStudentDtoList(studentRepository.findAll());
    }

    public List<StudentDto> findStudentsByCourseId(Long id) {
        Course foundCourse = courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException(id));
        List<Student> studentsByCourse = studentRepository.findStudentsByCoursesContaining(foundCourse);

        return mapToStudentDtoList(studentsByCourse);
    }

    public List<StudentDto> findStudentsWithoutCourses() {
        List<Student> studentsWithoutAnyCourses = studentRepository.findStudentsWithNoCoursesEnrolled();

        return mapToStudentDtoList(studentsWithoutAnyCourses);
    }


}
