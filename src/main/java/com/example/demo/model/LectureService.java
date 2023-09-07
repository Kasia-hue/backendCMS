package com.example.demo.model;

import com.example.demo.repo.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
                var l = new Lecture((String) m.get("lecture_end"), (String) m.get("lecture_start"), (Long) m.get("path"));
                l.setLectureDate((String) m.get("lecture_date"));
                lectureUser1.add(l);
            }
        }
        catch (DataAccessException e){
            System.out.println(e.getMessage());
        }
        return lectureUser1;
    }

    public class KeyValue{
        public Long key;
        public BigDecimal value;

        public KeyValue(Long key, BigDecimal value) {
            this.key = key;
            this.value = value;
        }

    }
    public List<KeyValue> percentUsers(){
        var v = String.format("SELECT LECTURE_ID, 1.0*count(USER_ID)/(SELECT COUNT (*) FROM" +
                " \"user\") as \"howMany\" FROM \"lecture_user\" group by LECTURE_ID");
        List<KeyValue> result = null;
        try {
            var result1 = jdbcTemplate.queryForList(v);
            result = new ArrayList<KeyValue>();
            for(Map m: result1){
                var l = new KeyValue((Long) m.get("LECTURE_ID"), (BigDecimal)m.get("howMany"));
                result.add(l);
            }
        }
        catch (DataAccessException e){
            System.out.println(e.getMessage());
        }
        return result;
    }


    public List<KeyValue> percentUsersPath(){
        var v1 = String.format("SELECT \"path\", 1.0*count(USER_ID)/(SELECT COUNT (*) FROM \"user\") as \"howMany\" \n" +
                "FROM \"lecture_user\"\n" +
                "JOIN \"lecture\" ON LECTURE_ID = ID \n" +
                "group by \"path\"\n");
        List<KeyValue> result2 = null;
        try {
            var result12 = jdbcTemplate.queryForList(v1);
            result2 = new ArrayList<KeyValue>();
            for(Map m: result12){
                var l = new KeyValue((Long) m.get("path"), (BigDecimal) m.get("howMany"));
                result2.add(l);
            }
        }
        catch (DataAccessException e){
            System.out.println(e.getMessage());
        }
        return result2;
    }

}

