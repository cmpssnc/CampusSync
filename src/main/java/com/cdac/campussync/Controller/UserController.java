package com.cdac.campussync.Controller;

import com.cdac.campussync.Entity.User;
import com.cdac.campussync.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@ModelAttribute User user) {
        boolean success = userService.saveUser(user);
        if (success) {
            return ResponseEntity.status(201).body("User Registration Successful!");
        } else {
            return ResponseEntity.status(500).body("User Registration Failed!");
        }
    }
}
