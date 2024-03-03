package com.example.giviproject.controller;

import com.example.giviproject.dto.UserDTO;
import com.example.giviproject.model.User;
import com.example.giviproject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//url for example /user should be here not on @GetMapping
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<UserDTO> getUser(@RequestParam Integer userId)
    {
        //late we need to reimplement logic to return UserDTO instead of user
        return ResponseEntity.ok(userService.getUserById(userId));
    }
}
