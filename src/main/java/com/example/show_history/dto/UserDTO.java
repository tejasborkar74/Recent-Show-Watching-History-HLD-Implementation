package com.example.show_history.dto;

public class UserDTO {
    private Long userId;
    private String username;
    private String email;

    public UserDTO(){}

    public UserDTO(Long userId, String username) {
        this.username = username;
        this.userId = userId;
    }

    public UserDTO(Long userId, String username, String email) {
        this.username = username;
        this.userId = userId;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Long getUserId() {
        return userId;
    }



}