package com.lucifer.shoes.service;

import com.lucifer.shoes.dto.LoginRequest;
import com.lucifer.shoes.model.AuthResponse;
import com.lucifer.shoes.model.User;
import com.lucifer.shoes.repository.UserRepository;
import com.lucifer.shoes.security.JwtHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
                    .firstname(user.getFirstname())  // Assuming your User entity uses first_name
                    .build();
        } else {
            // You can either throw an exception or return null
            // Throwing exception example:
            throw new RuntimeException("Invalid email or password");
        }
    }
}
