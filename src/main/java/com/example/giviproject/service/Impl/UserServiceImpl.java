package com.example.giviproject.service.Impl;

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
    public User getUserById(int id) {
        Optional<User> user = users
                .stream()
                .filter(u -> u.getId() == id)
                .findFirst();

        if(user.isEmpty())
        {
            //we will later write custom exception for this
            return null;
        }

        return user.get();
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
