package com.cdac.campussync.Controller;

import com.cdac.campussync.Entity.Student;
import com.cdac.campussync.Entity.Teacher;
import com.cdac.campussync.Entity.User;
import com.cdac.campussync.Enum.Role;
import com.cdac.campussync.Service.StudentService;
import com.cdac.campussync.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final StudentService studentService;
    private final TeacherService teacherService;
    private final PasswordEncoder passwordEncoder;

    // constructor autowiring
    @Autowired
    public UserController(StudentService studentService, TeacherService teacherService, PasswordEncoder passwordEncoder) {
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.passwordEncoder = passwordEncoder;
    }

    // Register a user in their respective table depending on their role
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {

        boolean success = false;

        // encrypts the password in the user object
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if(user.getRole() == Role.TEACHER) {

            // makes a teacher object by copying all values of the existing user object into a new teacher object
            // constructor defined in Teacher entity
            Teacher teacher = new Teacher(user);

            // returns true if the teacher is saved successfully, otherwise returns false
            success = teacherService.saveTeacher(teacher);
        }
        else if (user.getRole() == Role.STUDENT) {

            // makes a student object by copying all values of the existing user object into a new student object
            // constructor defined in Student entity
            Student student = new Student(user);

            // returns true of the student is saved successfully, otherwise returns false
            success = studentService.saveStudent(student);
        }

        if (success) {
            return ResponseEntity.status(201).body("User Registration Successful!");
        } else {
            return ResponseEntity.status(400).body("User Registration Failed! Invalid Input Data.");
        }
    }
}
