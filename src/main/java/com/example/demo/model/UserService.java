package com.example.demo.model;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    List<User> allUsers();

// User updateEmail(User user, String email);

    User findByLogin(String login);
    User findByEmail(String email);
    User updateEmail(User user, String login);
}
