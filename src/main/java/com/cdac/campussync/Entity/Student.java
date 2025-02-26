package com.cdac.campussync.Entity;

import com.cdac.campussync.Enum.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Arrays;

@Entity
public class Student extends User {

    // Many students can be mapped to the same course
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course enrolledCourse;

    @Lob
    @JsonIgnore
    private byte[] photo;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    private String address;


    // getter, setter, constructor
    public Student() {
    }

    public Student(Course enrolledCourse, byte[] photo, LocalDate dateOfBirth, Gender gender, String address) {
        this.enrolledCourse = enrolledCourse;
        this.photo = photo;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
    }

    public Course getEnrolledCourse() {
        return enrolledCourse;
    }

    public void setEnrolledCourse(Course enrolledCourse) {
        this.enrolledCourse = enrolledCourse;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    @Override
    public String toString() {
        return "Student{" +
                "enrolledCourse=" + enrolledCourse +
                ", photo=" + Arrays.toString(photo) +
                ", dateOfBirth=" + dateOfBirth +
                ", gender=" + gender +
                ", address='" + address + '\'' +
                '}';
    }
}