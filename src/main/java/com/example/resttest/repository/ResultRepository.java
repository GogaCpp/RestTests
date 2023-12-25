package com.example.resttest.repository;

import com.example.resttest.models.Result;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result, Long> {
}
