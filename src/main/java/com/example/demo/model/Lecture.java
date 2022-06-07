package com.example.demo.model;


import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter

public class Lecture {
    private int id;
    private String lectureStart;
    private String lectureEnd;
    private String lectureDate = "01.06.2022";
    private int path;
    private User[] participants = new User[5];

    public Lecture(){}

    public Lecture(int id, String lectureStart, String lectureEnd, int path) {
        this.id = id;
        this.lectureStart = lectureStart;
        this.lectureEnd = lectureEnd;
        this.path = path;
    }

    public Lecture(int id, String lectureStart, String lectureEnd, int path, User[] participants) {
        this.id = id;
        this.lectureStart = lectureStart;
        this.lectureEnd = lectureEnd;
        this.path = path;
        this.participants = participants;
    }

    public int getId() {
        return id;
    }

    public String getLectureStart() {
        return lectureStart;
    }

    public String getLectureEnd() {
        return lectureEnd;
    }

    public String getLectureDate() {
        return lectureDate;
    }

    public User[] getParticipants() {
        return participants;
    }


    public void setParticipants(User[] participants) {
        this.participants = participants;
    }

    public int getPath() {
        return path;
    }


 }
