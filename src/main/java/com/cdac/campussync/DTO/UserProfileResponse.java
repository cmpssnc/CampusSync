package com.cdac.campussync.DTO;

import com.cdac.campussync.Enum.Role;

public class UserProfileResponse {
    private Long id;
    private String username;
    private Role role;


    // getter, setter, constructor
    public UserProfileResponse() {
    }

    public UserProfileResponse(Long id, String username, Role role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
