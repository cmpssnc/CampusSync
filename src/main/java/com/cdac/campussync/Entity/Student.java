package com.cdac.campussync.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student extends User {

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course enrolledCourse;
}