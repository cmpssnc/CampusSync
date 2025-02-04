package com.cdac.campussync.Service;

import com.cdac.campussync.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    // Create or update a user
//    public User saveUser(User user) {
//        return userRepository.save(user);
//    }
//
//    // Find user by username
//    public User findByUsername(String username) {
//        return userRepository.findByUsername(username);
//    }

}