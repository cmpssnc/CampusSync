package com.cdac.campussync.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String courseName;

    // One course will have many students enrolled in it
    @OneToMany(mappedBy = "enrolledCourse", cascade = CascadeType.ALL)
    private List<Student> students;

    @OneToMany(mappedBy = "course")
    private List<Subject> subjects;
}
