package com.example.springbootrestapi.mapper;

import com.example.springbootrestapi.dto.StudentDto;
import com.example.springbootrestapi.model.Student;

import java.util.List;

public final class StudentMapper {

    public static Student mapStudentDtoToStudent(StudentDto studentDto) {
        return new Student(studentDto.getAlbumId(), studentDto.getFirstName(), studentDto.getLastName(), studentDto.getEmail());
    }

    public static List<StudentDto> mapToStudentDtoList(List<Student> students) {
        return students.stream().map(student -> mapStudentToStudentDto(student)).toList();
    }

    public static StudentDto mapStudentToStudentDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setAlbumId(student.getAlbumId());
        studentDto.setFirstName(student.getFirstName());
        studentDto.setLastName(student.getLastName());
        studentDto.setEmail(student.getEmail());

        return studentDto;
    }
}
