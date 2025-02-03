package com.cdac.campussync.Entity;

import jakarta.persistence.*;
import lombok.*;

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

    private String fileName;
    private String fileType;

    @Lob // Stores large binary data
    private byte[] fileData;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}