package com.example.springbootrestapi.service;

import com.example.springbootrestapi.dto.CourseDto;
import com.example.springbootrestapi.dto.StudentDto;
import com.example.springbootrestapi.exception.CourseNotFoundException;
import com.example.springbootrestapi.exception.StudentNotFoundException;
import com.example.springbootrestapi.exception.UserAlreadyExistsException;
import com.example.springbootrestapi.model.Course;
import com.example.springbootrestapi.model.Student;
import com.example.springbootrestapi.repo.CourseRepository;
import com.example.springbootrestapi.repo.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

import static com.example.springbootrestapi.mapper.StudentMapper.*;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentService(StudentRepository studentRepository,
                          CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public Student findStudentByAlbumId(String albumId) {
        return studentRepository.findStudentByAlbumId(albumId)
                .orElseThrow(() -> new StudentNotFoundException(null));
    }
    public StudentDto createStudent(StudentDto studentDto) {
        Optional<Student> foundStudent = studentRepository.findStudentByAlbumId(studentDto.getAlbumId());
        if (foundStudent.isEmpty()) {
            Student student = mapStudentDtoToStudent(studentDto);

            Student created = studentRepository.save(student);
            return mapStudentToStudentDto(created);
        } else {
            throw new UserAlreadyExistsException(studentDto.getAlbumId());
        }
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

    public void addStudentToCourse(Course course, Student student) {
        student.addCourseToStudent(course);

        studentRepository.save(student);
        courseRepository.save(course);
    }
}
