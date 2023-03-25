package com.example.springbootrestapi.model;

import jakarta.persistence.*;
import org.springframework.data.jpa.repository.Modifying;

import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(unique = true)
    private String albumId;
    private String firstName;
    private String lastName;
    private String email;
    @ManyToMany(fetch = LAZY)
    @JoinTable(
            name = "students_courses",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")}
    )
    private Set<Course> courses = new HashSet<>();

    public Student() {
    }

    public Student(String albumId, String firstName, String lastName, String email) {
        this.id = null;
        this.albumId = albumId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public void addCourseToStudent(Course course) {
        if (this.courses.size() <= 5 && course.getStudents().size() <= 50) {
            this.courses.add(course);
            course.getStudents().add(this);
        } else {
            throw new RuntimeException("Limits are reached!");
        }
    }

    public void removeCourseFromStudent(Course course) {
        this.courses.remove(course);
        course.getStudents().remove(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
