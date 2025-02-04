package com.cdac.campussync.Service;

import com.cdac.campussync.Entity.Teacher;
import com.cdac.campussync.Repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    private TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

//    // Create or update a teacher
//    public Teacher saveTeacher(Teacher teacher) {
//        return teacherRepository.save(teacher);
//    }
//
//    // Get teacher by ID
//    public Teacher getTeacherById(Long teacherId) {
//        return teacherRepository.findById(teacherId).orElseThrow(() -> new RuntimeException("Teacher not found"));
//    }

    // Other teacher-related business logic can be added here
}