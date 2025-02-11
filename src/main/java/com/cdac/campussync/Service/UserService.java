package com.cdac.campussync.Service;

import com.cdac.campussync.Entity.User;
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

    // Create or update a user
    public boolean saveUser(User user) {
        try {
            userRepository.save(user);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public Long findMaxId() {
        return userRepository.findMaxId();
    }
//
//    // Find user by username
//    public User findByUsername(String username) {
//        return userRepository.findByUsername(username);
//    }

}