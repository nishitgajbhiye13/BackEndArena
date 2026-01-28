package com.BackEndArena.bea.auth.dto;

import lombok.Getter;

import java.util.Set;

public class AuthResponse {


    @Getter
    private String token;

    @Getter
    private String email;

    @Getter
    private Set<String> roles;

    public AuthResponse(String token, String email, Set<String> roles) {

        this.token = token;
        this.email = email;
        this.roles = roles;
    }
}
