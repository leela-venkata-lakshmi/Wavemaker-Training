package com.wavemaker.example.service;

import com.wavemaker.example.model.User;
import com.wavemaker.example.repository.UserRepository;

import java.sql.SQLException;

public class UserService {
    UserRepository userRepository=new UserRepository();

    public UserService() throws SQLException {
    }

    public User findByUserName(String username)
    {
        return userRepository.findByUserName(username);
    }
}
