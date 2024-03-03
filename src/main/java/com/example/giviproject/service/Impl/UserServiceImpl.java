package com.example.giviproject.service.Impl;

import com.example.giviproject.dto.UserDTO;
import com.example.giviproject.exception.UserNotFoundException;
import com.example.giviproject.model.User;
import com.example.giviproject.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private List<User> users = new ArrayList<>();

    public UserServiceImpl()
    {
        generateUsers();
    }

    //this method should be reimplemented with DB logic
    //for temporary testing we will use data structures
    @Override
    public UserDTO getUserById(int id) {
        Optional<User> user = users
                .stream()
                .filter(u -> u.getId() == id)
                .findFirst();

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


    private void generateUsers()
    {
        User u1 = User
                .builder()
                .id(1)
                .name("Givi")
                .age(24)
                .build();

        User u2 = User
                .builder()
                .id(2)
                .name("Nika")
                .age(22)
                .build();

        User u3 = User
                .builder()
                .id(3)
                .name("Jango")
                .age(21)
                .build();

        this.users.addAll(Arrays.asList(u1, u2, u3));
    }
}
