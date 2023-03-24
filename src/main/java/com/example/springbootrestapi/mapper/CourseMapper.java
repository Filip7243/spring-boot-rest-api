package com.example.springbootrestapi.mapper;

import com.example.springbootrestapi.dto.CourseDto;
import com.example.springbootrestapi.model.Course;

import java.util.List;

public final class CourseMapper {

    public static Course mapToCourse(CourseDto courseDto) {
        return new Course(courseDto.getName(), courseDto.getDescription());
    }

    public static List<CourseDto> mapToCourseDtoList(List<Course> courses) {
        return courses.stream()
                .map(course -> mapToCourseDto(course)).toList();
    }

    public static CourseDto mapToCourseDto(Course course) {
        CourseDto courseDto = new CourseDto();
        courseDto.setName(course.getName());
        courseDto.setDescription(course.getDescription());

        return courseDto;
    }
}
