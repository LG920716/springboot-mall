package com.ray.springmail.controller;

import com.ray.springmail.dto.UserLoginDto;
import com.ray.springmail.dto.UserRequestDto;
import com.ray.springmail.entity.User;
import com.ray.springmail.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUserByUserId(@PathVariable Integer userId) {
        User user = userService.getByUserId(userId);
        if (user == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping("/users/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserRequestDto userRequestDto) {
        User user = userService.register(userRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping("/users/login")
    public ResponseEntity<User> login(@RequestBody @Valid UserLoginDto userLoginDto) {
        User user = userService.login(userLoginDto);

        return ResponseEntity.ok(user);
    }
}
