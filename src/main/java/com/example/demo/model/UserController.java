package com.example.demo.model;

import com.example.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @GetMapping()
    public List<User> getAll(){
        return userService.allUsers();
    }

//    @PostMapping("/register")
//    public String getUserByLogin(@RequestBody User user, String login) {
//        //sprawdzenie czy istnieje podany login
//        if (userService.findByLogin(login).equals(login)) {
//            return "Podany login jest już zajęty";
//        } else {
//            userService.saveUser(user);
//            return " ";
//        }
//    }
//
//    @PostMapping("/changeEmail")
//    public String changeEmail(@RequestBody String email){
//
//    }
}
