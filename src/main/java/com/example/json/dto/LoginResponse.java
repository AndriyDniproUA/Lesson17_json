package com.example.json.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String status;
    private String error;
    private String token;


}
