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

    private LocalDate submissionDate;

    private String fileName;
    private String fileType;

    @Lob
    private byte[] fileData; // Stores the submitted file

    private String status; // Submitted, Pending, Graded

    private Double grade;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @OneToOne
    @JoinColumn(name = "assignment_id")
    private Assignment assignment;
}
