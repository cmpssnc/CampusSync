package com.cdac.campussync.Entity;

import com.cdac.campussync.Enum.SubmissionStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

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

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDate submissionDate;

    private String fileName;
    private String fileType;

    @Lob
    private byte[] fileData; // Stores the submitted file

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SubmissionStatus status; // Submitted, Graded

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
