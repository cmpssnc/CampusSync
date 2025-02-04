package com.cdac.campussync.Service;

import com.cdac.campussync.Entity.Teacher;
import com.cdac.campussync.Repository.SubjectRepository;
import com.cdac.campussync.Repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {

    private SubjectRepository subjectRepository;

    private TeacherRepository teacherRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository, TeacherRepository teacherRepository) {
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
    }

//    // Create or update a subject
//    public Subject saveSubject(Subject subject, Long teacherId) {
//        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() -> new RuntimeException("Teacher not found"));
//        subject.setTeacher(teacher);
//        return subjectRepository.save(subject);
//    }
//
//    // Get a subject by ID
//    public Subject getSubjectById(Long subjectId) {
//        return subjectRepository.findById(subjectId).orElseThrow(() -> new RuntimeException("Subject not found"));
//    }

    // Other subject-related business logic can be added here
}
