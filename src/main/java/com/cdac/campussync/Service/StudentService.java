package com.cdac.campussync.Service;

import com.cdac.campussync.Entity.Course;
import com.cdac.campussync.Entity.Student;
import com.cdac.campussync.Entity.User;
import com.cdac.campussync.Repository.CourseRepository;
import com.cdac.campussync.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    // Fetch all students
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    // Find all students who don't have a course assigned (course_id is null)
    public List<Student> findStudentsWithNoCourse() {
        return studentRepository.findByEnrolledCourseIsNull();
    }

    // Assign a course to a student
    public Student assignCourseToStudent(Long studentId, Long courseId) {
        Optional<Student> studentOpt = studentRepository.findById(studentId);
        Optional<Course> courseOpt = courseRepository.findById(courseId);

        if (studentOpt.isPresent() && courseOpt.isPresent()) {
            Student student = studentOpt.get();
            Course course = courseOpt.get();

            student.setEnrolledCourse(course);
            return studentRepository.save(student);
        } else {
            throw new RuntimeException("Student or Course not found");
        }
    }
}


