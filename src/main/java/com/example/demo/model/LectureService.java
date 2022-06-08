package com.example.demo.model;

import com.example.demo.repo.LectureRepository;
import com.example.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LectureService {

    private JdbcTemplate jdbcTemplate;
    private LectureRepository lectureRepository;

    @Autowired
    public LectureService(LectureRepository lectureRepository, JdbcTemplate jdbcTemplate) {
        this.lectureRepository = lectureRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public LectureService(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

   // public Lecture saveLecture(Lecture lecture) {
    //    return (Lecture) this.lectureRepository.save(lecture);
   // }

    public List<Lecture> getAllLectures() {
        return lectureRepository.findAll();
    }

//    public Lecture saveLecture(Lecture lecture) {
//        return (Lecture)this.jdbcTemplate.queryForObject("INSERT INTO \"Lecture\" WHERE login = ?", new BeanPropertyRowMapper(User.class), new Object[]{login});
//    }

//    List<Lecture> allLectures(){
//        return;
//    }



}

