package com.example.resttest.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="result", schema = "alldata")//название
@Data
@Builder
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String first;
    private String second;
    private LocalDateTime time;

    public Result(String first,String second){
        this.first=first;
        this.second=second;
        this.time=LocalDateTime.now();
    }
}
