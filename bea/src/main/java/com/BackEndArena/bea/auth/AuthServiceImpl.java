package com.BackEndArena.bea.auth;

import com.BackEndArena.bea.User.User;
import com.BackEndArena.bea.User.UserRepository;
import com.BackEndArena.bea.auth.dto.AuthResponse;
import com.BackEndArena.bea.exception.UnauthorisedException;
import com.BackEndArena.bea.security.jwt.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthResponse login(String email, String password) throws UnauthorisedException{

        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        }catch (BadCredentialsException ex){
            throw new UnauthorisedException("Incorrect email or password");
        }
        User user = userRepository.findByEmail(email).orElseThrow();

        user.setLastLoginAt(Instant.now());

        userRepository.save(user);

        Map<String,Object> claims = Map.of("roles",user.getRoles().stream().map(Enum::name).toList());

        String token = jwtUtil.generateToken(user.getEmail(), claims);

        Set<String> roles = user.getRoles().stream().map(Enum::name).collect(Collectors.toSet());

        return new AuthResponse(token, user.getEmail(), roles);

    }

    @Override
    public void register(String email, String password) {

        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalStateException("User with the email already exists");

        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }
}
