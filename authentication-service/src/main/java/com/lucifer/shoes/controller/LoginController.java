package com.lucifer.shoes.controller;

import com.lucifer.shoes.dto.LoginRequest;
import com.lucifer.shoes.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/login")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class LoginController {
    private final LoginService loginService;
    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)

    public boolean validateUser(@RequestBody LoginRequest loginRequest) {
        return loginService.isValid(loginRequest);
    }
}
