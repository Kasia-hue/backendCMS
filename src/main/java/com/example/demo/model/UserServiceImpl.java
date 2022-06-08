package com.example.demo.model;

import com.example.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,  JdbcTemplate jtm) {
        this.userRepository = userRepository;
        this.jdbcTemplate = jtm;
    }

    @Override
    public User saveUser(User user) {
        return (User)this.userRepository.save(user);
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
    public User findByLogin(String login) {
        return (User)this.jdbcTemplate.queryForObject("SELECT * FROM \"user\" WHERE login = ?",
                new BeanPropertyRowMapper(User.class), new Object[]{login});
    }

    public User findByEmail(String email) {
        return (User)this.jdbcTemplate.queryForObject("SELECT * FROM \"user\" WHERE email = ?",
                new BeanPropertyRowMapper(User.class), new Object[]{email});
    }

    @Override
    public User signUp(User user, Long lectureId, String email, String login){
        if(lectureId.equals(1) || lectureId.equals(2) || lectureId.equals(3)  )
        return (User)this.jdbcTemplate.queryForObject("INSERT INTO \"user\" (signUpLecture1) VALUE {lectureId} WHERE email = ? AND login=?",
                new BeanPropertyRowMapper(User.class), new Object[]{lectureId}, new Object[]{email}, new Object[]{login});
        else if(lectureId.equals(4) || lectureId.equals(5) || lectureId.equals(6)  )
            return (User)this.jdbcTemplate.queryForObject("INSERT INTO \"user\" (signUpLecture2) VALUE {lectureId} WHERE email = ? AND login=?",
                    new BeanPropertyRowMapper(User.class), new Object[]{lectureId}, new Object[]{email}, new Object[]{login});
        else
            return (User)this.jdbcTemplate.queryForObject("INSERT INTO \"user\" (signUpLecture3) VALUE {lectureId} WHERE email = ? AND login=?",
                    new BeanPropertyRowMapper(User.class), new Object[]{lectureId}, new Object[]{email}, new Object[]{login});
    }

    @Override
    public User cancel(User user, Long lectureId) {
        if(lectureId.equals(1) || lectureId.equals(2) || lectureId.equals(3))
            return (User)this.jdbcTemplate.queryForObject("DELETE FROM \"user\" WHERE signUpLecture1=?",
                    new BeanPropertyRowMapper(User.class), new Object[]{lectureId});
        else if(lectureId.equals(4) || lectureId.equals(5) || lectureId.equals(6))
            return (User)this.jdbcTemplate.queryForObject("DELETE FROM \"user\" WHERE signUpLecture2=?",
                    new BeanPropertyRowMapper(User.class), new Object[]{lectureId});
        else
            return (User)this.jdbcTemplate.queryForObject("DELETE FROM \"user\" WHERE signUpLecture3=?",
                    new BeanPropertyRowMapper(User.class), new Object[]{lectureId});
    }


}
