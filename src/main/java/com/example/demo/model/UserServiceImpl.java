package com.example.demo.model;

import com.example.demo.repo.LectureUserRepository;
import com.example.demo.repo.UserRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;

    private LectureUserRepository lectureUserRepository;
    private final JdbcTemplate jdbcTemplate;
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public UserServiceImpl(UserRepository userRepository,  JdbcTemplate jtm, LectureUserRepository lectureUserRepository) {
        this.userRepository = userRepository;
        this.jdbcTemplate = jtm;
        this.lectureUserRepository = lectureUserRepository;
    }

    @Override
    public User saveUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateEmail(User user, Long id) {
        User existingUser = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundEx("User", "Id", id));
        existingUser.setEmail(user.getEmail());
        return userRepository.save(existingUser);
    }

    @Override
    public User findByLogin(String login) throws ResourceNotFoundEx{
        return (User)this.jdbcTemplate.queryForObject("SELECT * FROM \"user\" WHERE login = ?",
                new BeanPropertyRowMapper(User.class), new Object[]{login});
    }

    public User findByEmail(String email) throws ResourceNotFoundEx{
        return (User)this.jdbcTemplate.queryForObject("SELECT * FROM \"user\" WHERE email = ?",
                new BeanPropertyRowMapper(User.class), new Object[]{email});
    }

    public LectureUser findUser(User user, Long id){
        LectureUser lectureUser1 = null;
        try {
            lectureUser1 = (LectureUser) jdbcTemplate.queryForObject("SELECT * FROM \"lecture_user\" WHERE USER_ID = ? AND LECTURE_ID  = ?",
                    new BeanPropertyRowMapper(LectureUser.class), new Object[]{user.getId()}, new Object[]{id});
        }
        catch (DataAccessException e){
            System.out.println(e.getMessage());
        }
        return lectureUser1;
    }

    public LectureUser findUser(User user, String[] id){
        LectureUser lectureUser1 = null;
        try {
            String replacer = String.join(",", id);
            lectureUser1 = (LectureUser) jdbcTemplate.queryForObject(String.format("SELECT * FROM \"lecture_user\" WHERE USER_ID = ? AND LECTURE_ID IN (%s)", replacer),
                    new BeanPropertyRowMapper(LectureUser.class), new Object[]{user.getId()});
        }
        catch (DataAccessException e){
            System.out.println(e.getMessage());
        }
        return lectureUser1;
    }

    public boolean signUp(User user, Long lectureId){
        lectureUserRepository.save(new LectureUser(user.getId(), lectureId));
        return true;
    }
    @Override
    public void cancel(User user, Long lectureId) {
         LectureUser lectureUser2 = findUser(user, lectureId);
         lectureUserRepository.deleteById(lectureUser2.getluid());
    }

    public int countUser(Long lectureId){
        return jdbcTemplate.queryForObject("SELECT count(*) FROM \"lecture_user\" WHERE LECTURE_ID =?",
                int.class, new Object[]{lectureId});
    }

    public boolean emailMsg(LectureUser lectureUser) {
        Date nowDate = new Date();
        String msg = "! Welcome! This is lecture: ";
        String filePath = "C:\\Users\\ladym\\IdeaProjects\\demo\\src\\main\\java\\com\\example\\demo\\model\\notification.txt";
        try {
            byte[] strToBytes = (formatter.format(nowDate) + " User id:  " + lectureUser.getUserId() + msg +  lectureUser.getLectureId()).getBytes();
            Files.write(Paths.get(filePath), strToBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

}
