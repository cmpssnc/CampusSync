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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

        // sets the username to name + id
        teacher.setUsername(teacher.getName() + maxId);

        // password same as username
        teacher.setPassword(passwordEncoder.encode(teacher.getUsername()));

        success = teacherService.saveTeacher(teacher);

        if (success) {
            emailService.sendCredentials(teacher.getEmail(), teacher.getUsername(), teacher.getUsername());
            return ResponseEntity.status(201).body("User Registration Successful!");
        } else {
            return ResponseEntity.status(400).body("Teacher Registration Failed!");
        }
    }

//    @Transactional(rollbackOn = Exception.class)
//    @PostMapping("/student")
//    public ResponseEntity<String> registerStudent(@RequestBody Student student, @RequestParam("photo") MultipartFile file) {
//
//        // ensuring filesize less than 1MB, otherwise error
//        if (file.getSize() > 1024 * 1024) {
//            return ResponseEntity.badRequest().body("File size exceeds 1MB limit");
//        }
//
//        // saving photo
//        try {
//            student.setPhoto(file.getBytes());
//        } catch (IOException e) {
//            System.err.println(e.getMessage());
//        }
//
//        boolean success;
//
//        Long maxId = userService.findMaxId();
//        if(maxId == null) {
//            maxId = 1L;
//        } else {
//            maxId++;
//        }
//
//        // sets the username to name + id
//        student.setUsername(student.getName().replaceAll("\\s", "").toLowerCase() + maxId);
//
//        // password same as username
//        student.setPassword(passwordEncoder.encode(student.getUsername()));
//
//        success = studentService.saveStudent(student);
//
//        if (success) {
//            emailService.sendCredentials(student.getEmail(), student.getUsername(), student.getUsername());
//            return ResponseEntity.status(201).body("Student Registration Successful!");
//        } else {
//            return ResponseEntity.status(400).body("Teacher Registration Failed! Invalid Input Data.");
//        }
//    }
@Transactional(rollbackOn = Exception.class)
@PostMapping("/student")
public ResponseEntity<String> registerStudent(
        @RequestPart("student") Student student,  // Receives JSON data as a separate part
        @RequestPart(value = "photo", required = false) MultipartFile file // Receives file separately
) {
        System.out.println(student.toString());
    try {
        // File size validation (if photo is uploaded)
        if (file != null && file.getSize() > 1024 * 1024) {
            return ResponseEntity.badRequest().body("File size exceeds 1MB limit");
        }

        // Store the file as a byte array
        if (file != null) {
            student.setPhoto(file.getBytes());
        }

        // Generate unique username and password
        Long maxId = userService.findMaxId();
        maxId = (maxId == null) ? 1L : maxId + 1;
        student.setUsername(student.getName().replaceAll("\\s", "").toLowerCase() + maxId);
        student.setPassword(passwordEncoder.encode(student.getUsername()));

        // Save the student
        boolean success = studentService.saveStudent(student);

        if (success) {
            emailService.sendCredentials(student.getEmail(), student.getUsername(), student.getUsername());
            return ResponseEntity.status(201).body("Student Registration Successful!");
        } else {
            return ResponseEntity.status(400).body("Student Registration Failed! Invalid Input Data.");
        }
    } catch (IOException e) {
        return ResponseEntity.status(500).body("Error processing file upload.");
    }
}
}
