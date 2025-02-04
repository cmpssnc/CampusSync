package com.cdac.campussync.Service;


import com.cdac.campussync.Entity.Assignment;
import com.cdac.campussync.Entity.Subject;
import com.cdac.campussync.Repository.AssignmentRepository;
import com.cdac.campussync.Repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;

    private final SubjectRepository subjectRepository;

    @Autowired
    public AssignmentService(AssignmentRepository assignmentRepository, SubjectRepository subjectRepository) {
        this.assignmentRepository = assignmentRepository;
        this.subjectRepository = subjectRepository;
    }

//    // Create or update an assignment
//    public Assignment saveAssignment(Assignment assignment, Long subjectId) {
//        Subject subject = subjectRepository.findById(subjectId).orElseThrow(() -> new RuntimeException("Subject not found"));
//        assignment.setSubject(subject);
//        return assignmentRepository.save(assignment);
//    }
//
//    // Get assignment by ID
//    public Assignment getAssignmentById(Long assignmentId) {
//        return assignmentRepository.findById(assignmentId).orElseThrow(() -> new RuntimeException("Assignment not found"));
//    }

    // Other assignment-related business logic can be added here
}