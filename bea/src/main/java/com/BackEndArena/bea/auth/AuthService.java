package com.BackEndArena.bea.auth;

import com.BackEndArena.bea.auth.dto.AuthResponse;
import com.BackEndArena.bea.exception.UnauthorisedException;

public interface AuthService {

    AuthResponse login(String username, String password) throws UnauthorisedException;

    void register(String email, String password);

}
