package com.henrique.blog.service;

import com.henrique.blog.entities.User;

import java.util.Optional;

public interface UserService {

    void createUser(User user);
    Optional<User> getUserById(Long id);
    Optional<User> getUserByUsername(String username);

}
