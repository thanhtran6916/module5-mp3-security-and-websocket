package com.example.mp3.model;

import lombok.Data;

import java.util.Set;

@Data
public class JwtResponse {

    private Long id;

    private String username;

    private String type = "Bearer ";

    private String token;

    private Set<Role> roles;

    public JwtResponse(Long id, String username, String token, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.token = type + token;
        this.roles = roles;
    }
}
