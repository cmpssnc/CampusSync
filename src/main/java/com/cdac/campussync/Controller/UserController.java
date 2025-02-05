package com.cdac.campussync.Controller;

import com.cdac.campussync.Entity.Student;
import com.cdac.campussync.Entity.Teacher;
import com.cdac.campussync.Entity.User;
import com.cdac.campussync.Enum.Role;
import com.cdac.campussync.Service.StudentService;
import com.cdac.campussync.Service.TeacherService;
import com.cdac.campussync.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final StudentService studentService;
    private final TeacherService teacherService;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    // constructor autowiring
    @Autowired
    public UserController(StudentService studentService, TeacherService teacherService, PasswordEncoder passwordEncoder, UserService userService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    // Register a user in their respective table depending on their role
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {

        boolean success = false;

        Long maxId = userService.findMaxId();
        if(maxId == null) {
            maxId = 1L;
        } else {
            maxId++;
        }
        // sets the username to name + id
        user.setUsername(user.getName() + maxId);

        // password same as username
        user.setPassword(passwordEncoder.encode(user.getUsername()));


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
