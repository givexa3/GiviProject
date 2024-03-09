package com.example.giviproject.controller;

import com.example.giviproject.dto.UserDTO;
import com.example.giviproject.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//url for example /user should be here not on @GetMapping
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //localhost:8080/user?userId=1
    @GetMapping("/user")
    public ResponseEntity<UserDTO> getUser(@RequestParam Long userId)
    {
        //late we need to reimplement logic to return UserDTO instead of user
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    //localhost:8080/user
    @PostMapping("/user")
    public ResponseEntity<HttpStatus> createUser(@RequestBody UserDTO userDTO)
    {
        //we need to reimplement this looks awful :D
        userService.createUser(userDTO);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}
