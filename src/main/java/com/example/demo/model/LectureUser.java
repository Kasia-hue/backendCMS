package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "`LectureUser`")
public class LectureUser {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long luid;
    @Column(name="userId")
    private Long userId;
    @Column(name="lectureId")
    private Long lectureId;

    public LectureUser(){}

    public LectureUser(Long userId, Long lectureId) {
        this.userId = userId;
        this.lectureId = lectureId;
    }

    public Long getluid() {
        return luid;
    }

    public void setluid(Long lUiD) {
        this.luid = lUiD;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getLectureId() {
        return lectureId;
    }

    public void setLectureId(Long lectureId) {
        this.lectureId = lectureId;
    }
}
