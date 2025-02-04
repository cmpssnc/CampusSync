package com.cdac.campussync.Service;

import com.cdac.campussync.Entity.Assignment;
import com.cdac.campussync.Entity.Submission;
import com.cdac.campussync.Repository.AssignmentRepository;
import com.cdac.campussync.Repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubmissionService {

    private SubmissionRepository submissionRepository;

    private AssignmentRepository assignmentRepository;

    @Autowired
    public SubmissionService(SubmissionRepository submissionRepository, AssignmentRepository assignmentRepository) {
        this.submissionRepository = submissionRepository;
        this.assignmentRepository = assignmentRepository;
    }

//    // Create or update a submission
//    public Submission saveSubmission(Submission submission, Long assignmentId, Long studentId) {
//        Assignment assignment = assignmentRepository.findById(assignmentId).orElseThrow(() -> new RuntimeException("Assignment not found"));
//        submission.setAssignment(assignment);
//        submission.setStudent(studentId);  // Assuming Student entity is linked with studentId
//        return submissionRepository.save(submission);
//    }
//
//    // Get submission by ID
//    public Submission getSubmissionById(Long submissionId) {
//        return submissionRepository.findById(submissionId).orElseThrow(() -> new RuntimeException("Submission not found"));
//    }
//
//    // Grade a submission (assuming teacher provides grade)
//    public Submission gradeSubmission(Long submissionId, Integer grade) {
//        Submission submission = submissionRepository.findById(submissionId).orElseThrow(() -> new RuntimeException("Submission not found"));
//        submission.setGrade(grade);
//        return submissionRepository.save(submission);
//    }

    // Other submission-related business logic can be added here
}
