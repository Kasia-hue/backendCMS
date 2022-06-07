package com.example.demo.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @PostMapping("/register")
//    public ResponseEntity<User> saveUser(@RequestBody User user){
//        return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);
//    }

    @GetMapping()
    public List<User> getAll(){
        return userService.allUsers();
    }

    //Jeżeli w systemie istnieje już użytkownik z danym loginem, ale z innym adresem e-mail, system powinien zaprezentować komunikat „Podany login jest już zajęty”.X/x
    @PostMapping("/register")
    public String getUserByLogin(@RequestBody User user, @RequestBody String login) {
        //sprawdzenie czy istnieje podany login
        if (userService.findByLogin(login).equals(user.getLogin())) {
            return "Podany login jest już zajęty";
        } else {
            new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);
            //userService.saveUser(user);
            return " ";
        }
    }

    //zmiana email
    @PutMapping("/{email}")
    public String changeEmail(@PathVariable String email, @RequestBody User user)  {
        //sprawdzenie czy istnieje podany email
        if (userService.findByEmail(email).equals(user.getEmail())) {
            new ResponseEntity<User>(userService.updateEmail(email), HttpStatus.OK);
            //userService.updateEmail(email);
            return "Pomyślnie zmieniono email";
        } else {
            return "Sprawdź poprawność wprowadzonych danych";
        }

    }
}
