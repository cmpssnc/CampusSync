package com.cdac.campussync.Controller;

import com.cdac.campussync.Entity.Course;
import com.cdac.campussync.Entity.Student;
import com.cdac.campussync.Entity.Teacher;
import com.cdac.campussync.Entity.User;
import com.cdac.campussync.Enum.Role;
import com.cdac.campussync.Service.EmailService;
import com.cdac.campussync.Service.StudentService;
import com.cdac.campussync.Service.TeacherService;
import com.cdac.campussync.Service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/register")
public class RegistrationController {

    private final StudentService studentService;
    private final TeacherService teacherService;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final EmailService emailService;

    // constructor autowiring
    @Autowired
    public RegistrationController(StudentService studentService, TeacherService teacherService, PasswordEncoder passwordEncoder, UserService userService, EmailService emailService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.emailService = emailService;
    }

    // Register a user in their respective table depending on their role
    @Transactional(rollbackOn = Exception.class)
    @PostMapping("/teacher")
    public ResponseEntity<String> registerTeacher(@RequestBody Teacher teacher) {

        boolean success;

        Long maxId = userService.findMaxId();
        if(maxId == null) {
            maxId = 1L;
        } else {
            maxId++;
        }
        String strPassword = teacher.getPassword();

        // sets the username to name + id
        teacher.setUsername(teacher.getName() + maxId);

        // password same as username
        teacher.setPassword(passwordEncoder.encode(teacher.getUsername()));

        success = teacherService.saveTeacher(teacher);

        if (success) {
            emailService.sendCredentials(teacher.getEmail(), teacher.getUsername(), strPassword);
            return ResponseEntity.status(201).body("User Registration Successful!");
        } else {
            return ResponseEntity.status(400).body("Teacher Registration Failed!");
        }
    }

    @Transactional(rollbackOn = Exception.class)
    @PostMapping("/student")
    public ResponseEntity<String> registerStudent(@RequestBody Student student) {

        boolean success;

        Long maxId = userService.findMaxId();
        if(maxId == null) {
            maxId = 1L;
        } else {
            maxId++;
        }

        String strPassword = student.getPassword();
        // sets the username to name + id
        student.setUsername(student.getName() + maxId);

        // password same as username
        student.setPassword(passwordEncoder.encode(student.getUsername()));

        success = studentService.saveStudent(student);

        if (success) {
            emailService.sendCredentials(student.getEmail(), student.getUsername(), strPassword);
            return ResponseEntity.status(201).body("Student Registration Successful!");
        } else {
            return ResponseEntity.status(400).body("Teacher Registration Failed! Invalid Input Data.");
        }
    }

}
