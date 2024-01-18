package com.example.resttest.models;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user", schema = "alldata")//название
@Data
@Builder
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String role;

    public Users(String username, String password, String roleUser) {
        this.username=username;
        this.password=password;
        this.role=roleUser;
    }
}