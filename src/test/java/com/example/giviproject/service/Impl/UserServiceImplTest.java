package com.example.giviproject.service.Impl;

import com.example.giviproject.dto.UserDTO;
import com.example.giviproject.exception.UserNotFoundException;
import com.example.giviproject.model.User;
import com.example.giviproject.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    //looks like mockito cannot understand interfaces
    //so we are using concrete impl
    //find out other solutions to prevent this issue
    //dont like seeing concrete impl instead of interface
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testGetUserById_UserExists()
    {
        // Arrange
        Long userId = 1L;
        Optional<User> user = Optional.ofNullable(User
                .builder()
                .id(userId)
                .name("John Doe")
                .age(30)
                .build());

        // Mocking behavior of UserRepository
        when(userRepository.findById(userId)).thenReturn(user);

        // Act
        UserDTO userDTO = userService.getUserById(userId);

        // Assert
        assertEquals(user.get().getName(), userDTO.getName());
        assertEquals(user.get().getAge(), userDTO.getAge());

        // Verify that the UserRepository's findById method was called with the correct parameter
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void testGetUserById_UserDoesNotExists()
    {
        // Arrange
        long userId = 1L;

        // Mocking behavior of UserRepository
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(UserNotFoundException.class, () -> userService.getUserById(userId));

        // Verify that the UserRepository's findById method was called with the correct parameter
        verify(userRepository, times(1)).findById(userId);
    }
}