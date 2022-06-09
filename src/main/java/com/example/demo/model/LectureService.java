package com.example.demo.model;

import com.example.demo.repo.LectureRepository;
import com.example.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public List<Lecture> getAllLectures() {
        return lectureRepository.findAll();
    }


    public List<Lecture> findUserLectures(String login){
        List<Lecture> lectureUser1 = null;
        var k = String.format("SELECT \"lecture\".* FROM \"lecture_user\"\n" +
                "JOIN \"lecture\" ON LECTURE_ID = \"lecture\".ID\n" +
                "JOIN \"user\" ON USER_ID = \"user\".ID\n" +
                "WHERE LOGIN  REGEXP '%s'", login);
        try {
            var result1 = jdbcTemplate.queryForList(k);
            lectureUser1 = new ArrayList<Lecture>();
            for(Map m: result1){
                var l = new Lecture((String) m.get("lecture_end"), (String) m.get("lecture_start"), (Integer) m.get("path"));
                l.setLectureDate((String) m.get("lecture_date"));
                lectureUser1.add(l);
            }
        }
        catch (Exception e){

        }
        return lectureUser1;
    }

}

