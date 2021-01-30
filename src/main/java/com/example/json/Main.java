package com.example.json;

import com.example.json.dto.LoginResponse;
import com.example.json.dto.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.http.HttpClient;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        HttpClient httpClient = HttpClient.newBuilder().build();
        ObjectMapper objectMapper = new ObjectMapper();
        UsersService usersService = new UsersService(httpClient, objectMapper);

        List<User> users = usersService.getALl();
        System.out.println(users);

        LoginResponse loginResponse = usersService.login("user0","1111");
        System.out.println(loginResponse);






    }
}
