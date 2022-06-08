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
        return (User)this.jdbcTemplate.queryForObject("SELECT * FROM \"user\" WHERE login = ?", new BeanPropertyRowMapper(User.class), new Object[]{login});
    }

    public User findByEmail(String email) {
        return (User)this.jdbcTemplate.queryForObject("SELECT * FROM \"user\" WHERE email = ?", new BeanPropertyRowMapper(User.class), new Object[]{email});
    }


}
