package com.cdac.campussync.Entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student extends User {

    // Many students can be mapped to the same course
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course enrolledCourse;

    @Column(nullable = false)
    private String contactNumber;

    // custom copy constructor
    public Student (User user) {
        this.setName(user.getName());
        this.setUsername(user.getUsername());
        this.setEmail(user.getEmail());
        this.setPassword(user.getPassword());
        this.setRole(user.getRole());
    }
}