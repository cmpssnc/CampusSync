package com.cdac.campussync.Entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String courseName;

    private String courseDescription;

    // One course will have many students enrolled in it
    @OneToMany(mappedBy = "enrolledCourse", cascade = CascadeType.ALL)
    private List<Student> students;

    @OneToMany(mappedBy = "course")
    private List<Subject> subjects;


    // getter, setter, constructor
    public Course() {
    }

    public Course(Long id, String courseName, String courseDescription, List<Student> students, List<Subject> subjects) {
        this.id = id;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.students = students;
        this.subjects = subjects;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}
