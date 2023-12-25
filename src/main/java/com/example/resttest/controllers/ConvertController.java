package com.example.resttest.controllers;

import com.example.resttest.NumberToText;
import com.example.resttest.TextToNumber;
import com.example.resttest.models.Result;
import com.example.resttest.models.Users;
import com.example.resttest.repository.ResultRepository;
import com.example.resttest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class ConvertController {

    @Autowired
    UserRepository repository;

    @Autowired
    ResultRepository resultRepository;
    private PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();

    @GetMapping("/w")
    public String welcome(@RequestParam("name") String name, @RequestParam("pass") String pass){
        addUsers(new Users(name, pass, "ROLE_USER"));
        return "w";
    }
    public void addUsers(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
    }

    @GetMapping("/convert")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String convert(@RequestParam("type") String type, @RequestParam("value") String value) {


        String oldValue=String.valueOf(value);
        boolean correct=true;
        System.out.println("Type: " + type);
        System.out.println("Value: " + value);

        switch (type){
            case "StringToNumber"->{
                try {
                    long num = Long.parseLong(value);

                } catch (NumberFormatException e) {
                    TextToNumber.correct=false;
                    correct=false;
                    break;
                }
                TextToNumber.correct=true;
                value=TextToNumber.Convert(value).toString();
                correct=TextToNumber.correct;
            }
            case "NumberToString"->{
                value=value.replaceAll(" ", "");
                value=NumberToText.words(Long.parseLong(value));
            }
            default -> {
                return "Error type";
            }
        }

        value=(String) value;
        if(correct){
            LocalDateTime t=LocalDateTime.now();
            Result result=new Result(oldValue,value);
            resultRepository.save(result);
        }
        else {
            LocalDateTime t=LocalDateTime.now();
            Result result=new Result(oldValue,"Error");
            resultRepository.save(result);
        }

        return "Type: " + type + "\nResult: " + value;
    }


}
