package com.sharma.libmanagmentsystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sharma.libmanagmentsystem.dao.UserRepo;
import com.sharma.libmanagmentsystem.model.User;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    public List<User> getUsers() {
        return userRepo.findAll();
    }

    public void addUser(User user) {
        userRepo.save(user);

    }

    public User getUserById(int userId) {
        return userRepo.findById(userId).orElse(null);
    }
}
