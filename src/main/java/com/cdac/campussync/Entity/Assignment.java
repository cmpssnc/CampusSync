package com.cdac.campussync.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

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


    // getter, setter, constructor
    public Assignment() {
    }

    public Assignment(Long id, String title, LocalDate deadline, String fileName, String fileType, byte[] fileData, Subject subject) {
        this.id = id;
        this.title = title;
        this.deadline = deadline;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileData = fileData;
        this.subject = subject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}