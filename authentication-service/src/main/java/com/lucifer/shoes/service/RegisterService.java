package com.lucifer.shoes.service;

import com.lucifer.shoes.dto.UserRequest;
import com.lucifer.shoes.model.User;
import com.lucifer.shoes.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public void addUser(UserRequest userRequest) {
        User user = new User();
        user.setFirst_name(userRequest.getFirstName());
        user.setLast_name(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        userRepository.save(user);
    }

}
