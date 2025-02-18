package com.cdac.campussync.DTO;

import com.cdac.campussync.Entity.Student;
import com.cdac.campussync.Enum.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class StudentViewObject {
    private long id;
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dob;
    private String contactNumber;
    private String enrolledCourseName;
    private byte[] photo;
    private Gender gender;
    private String address;


    public StudentViewObject(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        this.dob = student.getDateOfBirth();
        this.contactNumber = student.getContactNumber();
        this.enrolledCourseName = student.getEnrolledCourse().getCourseName();
        this.photo = student.getPhoto();
        this.gender = student.getGender();
        this.address = student.getAddress();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEnrolledCourseName() {
        return enrolledCourseName;
    }

    public void setEnrolledCourseName(String enrolledCourseName) {
        this.enrolledCourseName = enrolledCourseName;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
