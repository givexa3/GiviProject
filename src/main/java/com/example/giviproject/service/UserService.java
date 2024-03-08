package com.example.giviproject.service;

import com.example.giviproject.dto.UserDTO;
import com.example.giviproject.model.User;

public interface UserService {
    UserDTO getUserById(Long id);
    void createUser(UserDTO userDTO);
}
