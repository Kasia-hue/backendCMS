package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="`User`")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name="login", nullable = false)
    private String login;
    @Column(name="email", nullable = false)
    private String email;
    @Column(name="signUpLecture1")
    private Long signUpLecture1;
    @Column(name="signUpLecture2")
    private Long signUpLecture2;
    @Column(name="signUpLecture3")
    private Long signUpLecture3;


    public User(){}

    public User(String login, String email) {
        this.login = login;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getSignUpLecture1() {
        return signUpLecture1;
    }

    public void setSignUpLecture1(Long signUpLecture1) {
        this.signUpLecture1 = signUpLecture1;
    }

    public Long getSignUpLecture2() {
        return signUpLecture2;
    }

    public void setSignUpLecture2(Long signUpLecture2) {
        this.signUpLecture2 = signUpLecture2;
    }

    public Long getSignUpLecture3() {
        return signUpLecture3;
    }

    public void setSignUpLecture3(Long signUpLecture3) {
        this.signUpLecture3 = signUpLecture3;
    }
}
