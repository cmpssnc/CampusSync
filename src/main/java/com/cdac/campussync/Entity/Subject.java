package com.cdac.campussync.Entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String subjectName;

    // Many subjects can be taught by the same teacher
    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    // Many subjects can be in the same course
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    // A subject can have multiple assignments
    @OneToMany(mappedBy="subject")
    private List<Assignment> assignments;


    // getter, setter, constructor
    public Subject() {
    }

    public Subject(Long id, String subjectName, Teacher teacher, Course course, List<Assignment> assignments) {
        this.id = id;
        this.subjectName = subjectName;
        this.teacher = teacher;
        this.course = course;
        this.assignments = assignments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }
}
