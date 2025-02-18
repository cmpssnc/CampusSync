package com.cdac.campussync.DTO;

import com.cdac.campussync.Entity.Student;

public class StudentFilterObject {
    private Long id;
    private String name;
    private String courseName;

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    private String contactNumber;

    public StudentFilterObject(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        this.courseName = student.getEnrolledCourse().getCourseName();
        this.contactNumber = student.getContactNumber();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
