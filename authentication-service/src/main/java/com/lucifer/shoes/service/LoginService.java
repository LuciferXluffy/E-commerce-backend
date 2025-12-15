package com.lucifer.shoes.service;

import com.lucifer.shoes.dto.LoginRequest;
import com.lucifer.shoes.exception.InvalidCredentialsException;
import com.lucifer.shoes.model.AuthResponse;
import com.lucifer.shoes.model.User;
import com.lucifer.shoes.repository.UserRepository;
import com.lucifer.shoes.security.JwtHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtHelper jwtHelper;

    public AuthResponse loginUser(LoginRequest loginRequest) {
        try {
            User user = userRepository.findByEmail(loginRequest.getEmail());

            if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                String token = jwtHelper.generateToken(
                        new org.springframework.security.core.userdetails.User(
                                user.getEmail(),
                                user.getPassword(),
                                Collections.emptyList()
                        )
                );

                return AuthResponse.builder()
                        .email(user.getEmail())
                        .token(token)
                        .firstname(user.getFirstname())
                        .build();
            } else {
                // Invalid login
                throw new InvalidCredentialsException("Invalid email or password");
            }
        } catch (InvalidCredentialsException e) {
            // Let global exception handler handle this
            throw e;
        } catch (Exception e) {
            // Unexpected service error
            throw new RuntimeException("Login service failed", e);
        }
    }
}