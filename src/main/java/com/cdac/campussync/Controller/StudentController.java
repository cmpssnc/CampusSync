package com.cdac.campussync.Controller;

import com.cdac.campussync.DTO.StudentFilterObject;
import com.cdac.campussync.Entity.Course;
import com.cdac.campussync.Entity.Student;
import com.cdac.campussync.Service.CourseService;
import com.cdac.campussync.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/students")
public class StudentController {

    private final StudentService studentService;
    private final CourseService courseService;

    @Autowired
    StudentController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

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


    // filtering students based on name or course or both
    @GetMapping("/filter")
    public ResponseEntity<List<StudentFilterObject>> filterStudents(@RequestParam("name") String nameFilter, @RequestParam("course") String courseFilter) {
        List<Student> finalList = null;
        Course course = courseService.getCourseByName(courseFilter);
        if(nameFilter.isEmpty() && !courseFilter.isEmpty()) {
            finalList = studentService.findStudentsByEnrolledCourse(course);
        }
        else if(!nameFilter.isEmpty() && courseFilter.isEmpty()) {
            finalList = studentService.findStudentsWithNameContaining(nameFilter);
        }
        else if(!nameFilter.isEmpty()) {
            finalList = studentService.findStudentsByEnrolledCourseAndNameContaining(course ,nameFilter);
        }

        List<StudentFilterObject> listToSend = new ArrayList<>();

        assert finalList != null;
        finalList.forEach(student -> listToSend.add(new StudentFilterObject(student)));
        return ResponseEntity.status(200).body(listToSend);
    }
}
