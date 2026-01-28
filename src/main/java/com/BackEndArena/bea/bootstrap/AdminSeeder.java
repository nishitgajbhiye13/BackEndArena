package com.BackEndArena.bea.bootstrap;

import com.BackEndArena.bea.User.User;
import com.BackEndArena.bea.User.UserRepository;
import com.BackEndArena.bea.enums.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;


@Component
public class AdminSeeder implements CommandLineRunner {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    @Value("${app.admin.email}")
    private String adminEmail;

    @Value("${app.admin.password}")
    private String adminPassword;

    public AdminSeeder(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void run(String... args) throws Exception {

        System.out.println("I am watching ");

        boolean adminExists = userRepository.existsByEmail(adminEmail);

        if (adminExists) {
            return;             //Idempotent - do nothing
        }
        User admin = new User();
        admin.setEmail(adminEmail);
        admin.setPassword(passwordEncoder.encode(adminPassword));
        admin.setRoles(Set.of(Role.ADMIN));
        admin.setEnabled(true);

        userRepository.save(admin);



    }
}
