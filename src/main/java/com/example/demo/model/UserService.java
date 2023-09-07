package com.example.demo.model;

import java.io.IOException;
import java.util.List;

public interface UserService {
    User saveUser(User user);
    List<User> allUsers();
    User findByLogin(String login);
    User updateEmail(User user, Long id);
    boolean signUp(User user, Long lectureId);

    LectureUser findUser(User user, Long id);
    LectureUser findUser(User user, String[] id);
    void cancel(User user, Long lectureId);
    int countUser(Long lectureId);
    boolean emailMsg(LectureUser lectureUser) throws IOException;
}
