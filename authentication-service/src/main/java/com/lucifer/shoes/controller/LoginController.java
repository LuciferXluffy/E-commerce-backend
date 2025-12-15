package com.lucifer.shoes.controller;

import com.lucifer.shoes.dto.LoginRequest;
import com.lucifer.shoes.model.AuthResponse;
import com.lucifer.shoes.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/login")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;


    @PostMapping
    public ResponseEntity<AuthResponse> loginUser(@RequestBody LoginRequest loginRequest) {
        AuthResponse response = loginService.loginUser(loginRequest);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
