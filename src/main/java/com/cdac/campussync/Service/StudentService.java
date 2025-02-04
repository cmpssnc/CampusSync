package com.cdac.campussync.Service;

import com.cdac.campussync.Entity.Course;
import com.cdac.campussync.Entity.Student;
import com.cdac.campussync.Entity.User;
import com.cdac.campussync.Repository.CourseRepository;
import com.cdac.campussync.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private final CourseRepository courseRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public boolean saveStudent(Student student) {
        try {
            studentRepository.save(student);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

//    // Enroll a student in a course
//    public Student enrollInCourse(Long studentId, Long courseId) {
//        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
//        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
//
//        student.setCourse(course);
//        return studentRepository.save(student);
//    }
//
//    // Get student by associated user
//    public Student getStudentByUser(User user) {
//        return studentRepository.findByUser(user);
//    }

    // Other student-related business logic can be added here
}