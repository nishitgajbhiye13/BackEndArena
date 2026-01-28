package com.BackEndArena.bea.auth.dto;

import lombok.Getter;

public class RegisterRequest {

    @Getter
    private String email;

    @Getter
    private String password;
}
