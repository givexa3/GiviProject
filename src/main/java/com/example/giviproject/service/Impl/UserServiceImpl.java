package com.example.giviproject.service.Impl;

import com.example.giviproject.dto.UserDTO;
import com.example.giviproject.exception.UserNotFoundException;
import com.example.giviproject.model.User;
import com.example.giviproject.repository.UserRepository;
import com.example.giviproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty())
        {
            //search for another string format i remember it is done with better like using %s
            //i dont like this concat logic!!!
            throw new UserNotFoundException("User With Given ID: " + id + " Does not Exist!");
        }

        //we need auto mapper here
        return UserDTO
                .builder()
                .age(user.get().getAge())
                .name(user.get().getName())
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
    }
}
