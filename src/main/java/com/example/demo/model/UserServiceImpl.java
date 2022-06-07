package com.example.demo.model;

import com.example.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService1;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateEmail(User user, String email) {
        User existUser = userService1.findByEmail(email);
        if(existUser.equals(email))


        return existUser;
    }

    @Override
    public User findByLogin(String login) {
        return jdbcTemplate.queryForObject("SELECT login FROM User WHERE login=?",
                BeanPropertyRowMapper.newInstance(User.class), login);
    }

    @Override
    public User findByEmail(String email) {
        return jdbcTemplate.queryForObject("SELECT email FROM User WHERE email=?",
                BeanPropertyRowMapper.newInstance(User.class), email);
    }




}
