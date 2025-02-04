package com.cdac.campussync.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
}
