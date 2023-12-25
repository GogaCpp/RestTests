package com.example.resttest.service;

import com.example.resttest.models.Users;
import com.example.resttest.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@AllArgsConstructor

@NoArgsConstructor
public class adminService {


    private UserRepository repository;
    private PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();


    public void addUsers(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
    }
}
