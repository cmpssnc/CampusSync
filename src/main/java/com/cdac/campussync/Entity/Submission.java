package com.cdac.campussync.Entity;

import com.cdac.campussync.Enum.SubmissionStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

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


    // getter, setter, constructor
    public Submission() {
    }

    public Submission(Long id, LocalDate submissionDate, String fileName, String fileType, byte[] fileData, SubmissionStatus status, Double grade, Student student, Assignment assignment) {
        this.id = id;
        this.submissionDate = submissionDate;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileData = fileData;
        this.status = status;
        this.grade = grade;
        this.student = student;
        this.assignment = assignment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDate submissionDate) {
        this.submissionDate = submissionDate;
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

    public SubmissionStatus getStatus() {
        return status;
    }

    public void setStatus(SubmissionStatus status) {
        this.status = status;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }
}
