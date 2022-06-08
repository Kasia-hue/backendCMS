package com.example.demo.model;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    List<User> allUsers();

    //sprawdz czy istnieje taki login
    User findByLogin(String login);
    //zmiana email
    User updateEmail(User user, Long id);
}
