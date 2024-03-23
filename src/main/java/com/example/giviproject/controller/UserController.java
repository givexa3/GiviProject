package com.example.giviproject.controller;

import com.example.giviproject.constants.ControllerConstants;
import com.example.giviproject.dto.UserDTO;
import com.example.giviproject.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ControllerConstants.USER_CONTROLLER_PATH)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //localhost:8080/user?userId=1
    @GetMapping()
    public ResponseEntity<UserDTO> getUser(@RequestParam Long userId)
    {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    //localhost:8080/user
    @PostMapping()
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO)
    {
        userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping()
    public ResponseEntity<String> updateUser(@RequestBody UserDTO userDTO, @RequestParam long userId)
    {
        userService.updateUser(userDTO, userId);
        return ResponseEntity.ok("User Updated Successfully");
    }

    //do examples with @RequestParam and @PathVariable differences
    @DeleteMapping()
    public ResponseEntity<String> deleteUser(@RequestParam long userId)
    {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User Deleted Successfully");
    }
}
