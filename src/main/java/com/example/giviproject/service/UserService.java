package com.example.giviproject.service;

import com.example.giviproject.dto.UserDTO;

public interface UserService {
    UserDTO getUserById(Long id);
    void createUser(UserDTO userDTO);
    void updateUser(UserDTO userDTO, long userId);
    void deleteUser(long userId);
}
