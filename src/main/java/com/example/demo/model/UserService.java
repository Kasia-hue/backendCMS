package com.example.demo.model;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    List<User> allUsers();

    //sprawdz czy istnieje taki login
    User findByLogin(String login);
    //sprawdz czy istnieje taki email
    User findByEmail(String email);
    //zmiana email
    User updateEmail(String email);
}
