package com.example.json.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserResponse {
    private String status;
    private List<User> users;
    private String error;
}
