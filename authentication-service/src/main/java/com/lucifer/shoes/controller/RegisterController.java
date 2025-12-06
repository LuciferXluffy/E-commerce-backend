package com.lucifer.shoes.controller;

import com.lucifer.shoes.dto.UserRequest;
import com.lucifer.shoes.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/register")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class RegisterController {

    private final RegisterService registerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String AddUser(@RequestBody UserRequest userRequest) {
        registerService.addUser(userRequest);
        return "User Added Succesfully";
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String working() {
        return "Api is Working";
    }
}
