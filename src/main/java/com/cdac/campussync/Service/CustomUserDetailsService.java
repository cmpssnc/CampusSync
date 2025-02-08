package com.cdac.campussync.Service;

import com.cdac.campussync.Entity.User;
import com.cdac.campussync.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("admin".equals(username)) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(username)
                    .password("admin") // Password should be hashed in real apps!
                    .roles("ADMIN") // Add user roles/authorities
                    .build();
        }

        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found!"));

        return new org.springframework.security.core.userdetails
                .User(
                        user.getUsername(),
                        user.getPassword(),
                        AuthorityUtils.createAuthorityList(String.valueOf(user.getRole()))
                );
    }
}
