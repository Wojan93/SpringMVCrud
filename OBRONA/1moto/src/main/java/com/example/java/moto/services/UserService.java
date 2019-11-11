package com.example.java.moto.services;

import com.example.java.moto.model.User;

import java.util.Set;

public interface UserService {

    User findByLastName(String lastName);
    User findByEmail(String email);
    User findByPhone(String phone);
    User findByFirstName(String firstName);
    User findById(Long id);

    User save(User user);

    Set<User> findAll();
}