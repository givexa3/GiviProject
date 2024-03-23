package com.example.giviproject.unitTests;

import com.example.giviproject.dto.UserDTO;
import com.example.giviproject.exception.UserNotFoundException;
import com.example.giviproject.model.User;
import com.example.giviproject.repository.UserRepository;
import com.example.giviproject.service.Impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeAll;
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

    private static Long userId;
    private static Optional<User> user;
    private static UserDTO userDTO;

    @Mock
    private UserRepository userRepository;

    //looks like mockito cannot understand interfaces
    //so we are using concrete impl
    //find out other solutions to prevent this issue
    //dont like seeing concrete impl instead of interface
    @InjectMocks
    private UserServiceImpl userService;

    //i think this should be @BeforeAll and not @BeforeEach but check it anyway...
    @BeforeAll
    static void beforeAll() {
        userId = 1L;
        user = Optional.ofNullable(User
                .builder()
                .id(userId)
                .name("John Doe")
                .age(30)
                .build());
        userDTO = UserDTO
                .builder()
                .name("John")
                .age(30)
                .build();
    }

    @Test
    void testGetUserById_UserExists()
    {
        // Arrange
        //before we had userId and user initialized here but now we refactored
        //so you should have Arrange, Act, Assert

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
        // Mocking behavior of UserRepository
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(UserNotFoundException.class, () -> userService.getUserById(userId));

        // Verify that the UserRepository's findById method was called with the correct parameter
        verify(userRepository, times(1)).findById(userId);
    }


    @Test
    void testCreateUser()
    {
        // Act
        userService.createUser(userDTO);

        // Assert
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testUpdateUserWhenUserExists()
    {
        // Mock the behavior of userRepository
        when(userRepository.findById(userId)).thenReturn(user);

        // Act
        userService.updateUser(userDTO, userId);

        // Assert
        //maybe we can also do if Present, if not throw exception and test failed ...
        assertEquals(userDTO.getName(), user.get().getName());
        assertEquals(userDTO.getAge(), user.get().getAge());

        // Verify that userRepository.save() is called once with the updated user
        verify(userRepository, times(1)).save(user.get());
        // check Power Mock for testing logs!!!
        // Verify log.info() is called once
        // This requires a mocking framework that can mock static methods or a logging abstraction for mocking
        // Assuming log is a Logger instance, you might need a specific approach based on your logging framework
        // For example, with Mockito's PowerMockito, you can mock static methods like this:
        // PowerMockito.mockStatic(Log.class);
        // PowerMockito.verifyStatic(Log.class, times(1));
        // Log.info("User With Given ID: {} Updated Successfully", userId);
    }

    @Test
    void testUpdateUserWhenUserDoesNotExist() {
        // Mock the behavior of userRepository
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> userService.updateUser(userDTO, userId));

        // Verify that userRepository.save() is never called
        verify(userRepository, never()).save(any());
    }

    @Test
    void testDeleteUserWhenUserExists()
    {
        // Mock the behavior of userRepository
        when(userRepository.findById(userId)).thenReturn(user);

        // Act
        userService.deleteUser(userId);

        // Verify that userRepository.save() is called once with the delete user
        verify(userRepository, times(1)).delete(user.get());
    }


    //Hmm is writing the same code neccessary? maybe we can just write one test check if user does not exist
    //and do the same for all tests? just write one test check user does not exist functionality?
    @Test
    void testDeleteUserWhenUserDoesNotExist() {
        // Mock the behavior of userRepository
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> userService.deleteUser(userId));

        // Verify that userRepository.save() is never called
        verify(userRepository, never()).save(any());
    }

    //lets write tests for check logging later
}