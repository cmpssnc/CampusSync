package com.cdac.campussync.Entity;

import com.cdac.campussync.Enum.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student extends User {

    // Many students can be mapped to the same course
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course enrolledCourse;

    @Lob
    private byte[] photo;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private String address;

    // custom copy constructor
    public Student (User user) {
        this.setName(user.getName());
        this.setUsername(user.getUsername());
        this.setEmail(user.getEmail());
        this.setPassword(user.getPassword());
        this.setRole(user.getRole());
        this.setContactNumber(user.getContactNumber());
    }
}