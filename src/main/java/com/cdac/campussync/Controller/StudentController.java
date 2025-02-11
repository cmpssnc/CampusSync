package com.cdac.campussync.Controller;

import com.cdac.campussync.Entity.Student;
import com.cdac.campussync.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Fetch all students
    @GetMapping("/")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.findAllStudents();
        if (students.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content if no students found
        }
        return ResponseEntity.ok(students); // 200 OK with student list
    }

    // Fetch all students who have no assigned course
    @GetMapping("/unassigned")
    public ResponseEntity<List<Student>> getUnassignedStudents() {
        List<Student> students = studentService.findStudentsWithNoCourse();
        if (students.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content if no unassigned students found
        }
        return ResponseEntity.ok(students); // 200 OK with student list
    }

    // Assign a course to a student
    @PutMapping("/{studentId}/assign-course/{courseId}")
    public ResponseEntity<Student> assignCourseToStudent(@PathVariable Long studentId, @PathVariable Long courseId) {
        Optional<Student> updatedStudent = Optional.ofNullable(studentService.assignCourseToStudent(studentId, courseId));
        return updatedStudent
                .map(student -> ResponseEntity.ok().body(student)) // 200 OK if student is updated
                .orElseGet(() -> ResponseEntity.notFound().build()); // 404 Not Found if student or course not found
    }

    // Update student details (including course)
    @PutMapping("/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long studentId, @RequestBody Student studentDetails) {
        Optional<Student> updatedStudent = Optional.ofNullable(studentService.updateStudent(studentId, studentDetails));
        return updatedStudent
                .map(student -> ResponseEntity.ok().body(student)) // 200 OK if student is updated
                .orElseGet(() -> ResponseEntity.notFound().build()); // 404 Not Found if student not found
    }

    // Delete a student by id
    @DeleteMapping("/{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long studentId) {
        try {
            boolean deleted = studentService.deleteStudent(studentId);
            if (deleted) {
                return ResponseEntity.noContent().build(); // 204 No Content (Success)
            } else {
                return ResponseEntity.status(404).body("Student not found with ID: " + studentId); // 404 Not Found
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting student: " + e.getMessage()); // 500 Internal Server Error
        }
    }

}
