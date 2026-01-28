package com.BackEndArena.bea.auth;

import com.BackEndArena.bea.auth.dto.AuthResponse;
import com.BackEndArena.bea.auth.dto.LoginRequest;
import com.BackEndArena.bea.auth.dto.RegisterRequest;
import com.BackEndArena.bea.exception.UnauthorisedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) throws UnauthorisedException {

        AuthResponse response = authService.login(loginRequest.getEmail(), loginRequest.getPassword());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody RegisterRequest request
    ) {
        authService.register(request.getEmail(), request.getPassword());
        return ResponseEntity.ok("User registered successfully");
    }

}
