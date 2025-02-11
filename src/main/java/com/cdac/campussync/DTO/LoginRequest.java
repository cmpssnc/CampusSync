package com.cdac.campussync.DTO;

public class LoginRequest {
    private String username;
    private String password;


    // getter, setter, constructor
    public LoginRequest() {
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
