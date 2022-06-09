package com.example.demo.model;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    List<User> allUsers();

    //sprawdz czy istnieje taki login
    User findByLogin(String login);
    //zmiana email
    User updateEmail(User user, Long id);
    //Zapis na prelekcje
    boolean signUp(User user, Long lectureId);

    LectureUser findUser(User user, Long id);
    LectureUser findUser(User user, String[] id);
    void cancel(User user, Long lectureId);
    int countUser(Long lectureId);
}
