package com.alienlines.userservice.controller;

import com.alienlines.userservice.dao.RegisterUser;
import com.alienlines.userservice.model.User;
import com.alienlines.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String registerUser(@RequestBody RegisterUser registerUser){
        userService.registerUser(registerUser);
        return "User created Successfully!";
    }
}
