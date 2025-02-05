package com.cdac.campussync.Controller;

import com.cdac.campussync.Entity.Student;
import com.cdac.campussync.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Fetch all students
    @GetMapping("/")
    public List<Student> getAllStudents() {
        return studentService.findAllStudents();
    }


    //Fetch all students who have no assigned course
    @GetMapping("/unassigned")
    public List<Student> getUnassignedStudents() {
        return studentService.findStudentsWithNoCourse();
    }

    //Assign a course to a student
    @PutMapping("/{studentId}/assign-course/{courseId}")
    public Student assignCourseToStudent(@PathVariable Long studentId, @PathVariable Long courseId) {
        return studentService.assignCourseToStudent(studentId, courseId);
    }
}
