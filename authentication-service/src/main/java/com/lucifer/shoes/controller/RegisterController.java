package com.lucifer.shoes.controller;

import com.lucifer.shoes.dto.UserRequest;
import com.lucifer.shoes.model.AuthResponse;
import com.lucifer.shoes.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/register")
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService;

    @PostMapping
    public ResponseEntity<AuthResponse> registerUser(@RequestBody UserRequest userRequest){
        AuthResponse token = registerService.registerUser(userRequest);

        return new ResponseEntity<>(token , HttpStatus.CREATED);
    }


}
