package com.cdac.campussync.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(nullable = false)
    private LocalDate deadline;

    private String fileName;
    private String fileType;

    @Lob // Stores large binary data
    private byte[] fileData;

    // Many assignments can be mapped to the same Subject
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;
}