package com.cdac.campussync.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate submissionDate;

    private String fileName;
    private String fileType;

    @Lob
    private byte[] fileData; // Stores the submitted file

    @Column(nullable = false)
    private String status; // Submitted, Pending, Graded

    private Double grade;

    // Many submissions can come from the same student
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    // One submission per assignment
    @OneToOne
    @JoinColumn(name = "assignment_id", nullable = false)
    private Assignment assignment;
}
