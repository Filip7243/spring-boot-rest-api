package com.example.springbootrestapi.service;

import com.example.springbootrestapi.dto.StudentDto;
import com.example.springbootrestapi.model.Student;
import com.example.springbootrestapi.repo.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(StudentDto studentDto) {
        Student student = mapStudentDtoToStudent(studentDto);

        return studentRepository.save(student);
    }

    public void updateStudentWithId(Long id, StudentDto updatedStudent) {
        Student foundStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        foundStudent.setAlbumId(updatedStudent.getAlbumId());
        foundStudent.setFirstName(updatedStudent.getFirstName());
        foundStudent.setLastName(updatedStudent.getLastName());
        foundStudent.setEmail(updatedStudent.getEmail());
    }

    public void deleteStudentWithId(Long id) {
        Student foundStudent = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found!"));

        studentRepository.deleteById(id);
    }

    public List<StudentDto> findAllStudents() {
        return mapToStudentDtoList((List<Student>) studentRepository.findAll());
    }

    public List<StudentDto> findStudentsByCourses() {
        return null;
    }

    private static Student mapStudentDtoToStudent(StudentDto studentDto) {
        return new Student(studentDto.getAlbumId(), studentDto.getFirstName(), studentDto.getLastName(), studentDto.getEmail());
    }

    private static List<StudentDto> mapToStudentDtoList(List<Student> students) {
        return students.stream().map(student -> mapStudentToStudentDto(student)).toList();
    }

    private static StudentDto mapStudentToStudentDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setAlbumId(student.getAlbumId());
        studentDto.setFirstName(student.getFirstName());
        studentDto.setLastName(student.getLastName());
        studentDto.setEmail(student.getEmail());

        return studentDto;
    }
}
