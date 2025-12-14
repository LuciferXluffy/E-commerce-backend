package com.lucifer.shoes.service;

import com.lucifer.shoes.dto.UserRequest;
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
public class RegisterService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtHelper jwtHelper;

    public AuthResponse registerUser(UserRequest userRequest) {
        User user = new User();
        user.setFirstname(userRequest.getFirstname());
        user.setLastname(userRequest.getLastname());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());

        if(user != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }

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
    }
}
