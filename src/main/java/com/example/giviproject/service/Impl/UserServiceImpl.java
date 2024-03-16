package com.example.giviproject.service.Impl;

import com.example.giviproject.dto.UserDTO;
import com.example.giviproject.exception.UserNotFoundException;
import com.example.giviproject.model.User;
import com.example.giviproject.repository.UserRepository;
import com.example.giviproject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO getUserById(Long id) {
        User user = checkAndGetUserById(id);
        //we need auto mapper here
        return UserDTO
                .builder()
                .age(user.getAge())
                .name(user.getName())
                .build();
    }

    @Override
    public void createUser(UserDTO userDTO) {
        User user = User
                .builder()
                .age(userDTO.getAge())
                .name(userDTO.getName())
                .build();

        userRepository.save(user);
        log.info("User With Given Name: {} Created Successfully", userDTO.getName());
    }

    @Override
    public void updateUser(UserDTO userDTO, long userId) {
        User user = checkAndGetUserById(userId);

        user.setName(userDTO.getName());
        user.setAge(userDTO.getAge());

        userRepository.save(user);
        log.info("User With Given ID: {} Updated Successfully", userId);
    }

    @Override
    public void deleteUser(long userId) {
        User user = checkAndGetUserById(userId);
        userRepository.delete(user);
        log.info("User With Given ID: {} Deleted Successfully", userId);
    }


    private User checkAndGetUserById(long userId)
    {
        Optional<User> user = userRepository.findById(userId);

        if(user.isEmpty())
        {
            log.error("User With Given ID: {} Does not Exist!", userId);
            throw new UserNotFoundException(String.format("User With Given ID: %s Does not Exist!", userId));
        }

        return user.get();
    }
}
