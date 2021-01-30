package com.example.json;

import com.example.json.dto.LoginRequest;
import com.example.json.dto.LoginResponse;
import com.example.json.dto.User;
import com.example.json.dto.UserResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@RequiredArgsConstructor
public class UsersService {
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public List<User> getALl() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://mag-contacts-api.herokuapp.com/users"))
                .GET()
                .header("Accept", "application/json")
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            UserResponse userResponse = objectMapper.readValue(
                    response.body(),
                    UserResponse.class);
            return userResponse.getUsers();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public LoginResponse login(String login, String pass) {
        try {
            String req = objectMapper.writeValueAsString(
                    new LoginRequest(login, pass)
            );

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://mag-contacts-api.herokuapp.com/login"))
                    .POST(HttpRequest.BodyPublishers.ofString(req))
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            return objectMapper.readValue
                    (response.body(),
                            LoginResponse.class
                    );
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
