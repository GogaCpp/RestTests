package com.example.resttest.controllers;

import com.example.resttest.service.NumberToText;
import com.example.resttest.service.TextToNumber;
import com.example.resttest.models.Result;
import com.example.resttest.models.Users;
import com.example.resttest.repository.ResultRepository;
import com.example.resttest.service.SaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
public class ConvertController {


    @Autowired
    ResultRepository resultRepository;

    @GetMapping("/w")
    public String welcome(@RequestParam("name") String name, @RequestParam("pass") String pass){
        SaveService.addUsers(new Users(name, pass, "ROLE_USER"));
        return "Add user";
    }



    @GetMapping("/convert")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String convert(@RequestParam("type") String type, @RequestParam("value") String value) {


        String oldValue=String.valueOf(value);
        boolean correct=true;
        log.info("Type: " + type);
        log.info("Value: " + value);

        switch (type){
            case "StringToNumber"->{
                value=TextToNumber.Convert(value).toString();
                correct=TextToNumber.correct;
            }
            case "NumberToString"->{
                TextToNumber.correct=true;
                value=NumberToText.words(Long.parseLong(value));
            }
            default -> {
                return "Error type";
            }
        }


        if(correct){
            LocalDateTime t=LocalDateTime.now();
            Result result=new Result(type,oldValue,value);
            resultRepository.save(result);
        }
        else {
            LocalDateTime t=LocalDateTime.now();
            Result result=new Result(type,oldValue,"Error");
            resultRepository.save(result);
            value="Error";
        }

        return "Type: " + type + "\nResult: " + value;
    }


}
