package com.example.resttest.service;

import com.example.resttest.models.Users;
import com.example.resttest.repository.ResultRepository;
import com.example.resttest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SaveService {
    @Autowired
    static UserRepository repository;



    private static PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
    public static void addUsers(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
    }
}
