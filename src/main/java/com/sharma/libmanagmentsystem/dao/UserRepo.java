package com.sharma.libmanagmentsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sharma.libmanagmentsystem.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
