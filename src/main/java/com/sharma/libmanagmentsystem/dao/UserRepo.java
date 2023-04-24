package com.sharma.libmanagmentsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sharma.libmanagmentsystem.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {

    @Query("SELECT a FROM User a WHERE a.name = :name")
    User findUser(@Param("name") String name);
}
