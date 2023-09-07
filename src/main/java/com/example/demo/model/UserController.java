package com.example.demo.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<User> getAll(){
        return this.userService.allUsers();
    }

    @GetMapping({"{login}"})
    public ResponseEntity<User> getUser (@PathVariable("login") String login){
        return new ResponseEntity<>(userService.findByLogin(login), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> getUserByLogin(@RequestBody User user) {
        var exists = false;
        try {
            exists =  userService.findByLogin(user.getLogin()) != null;
        }
        catch (DataAccessException e){
            System.out.println(e.getMessage());
        }
        if (exists) {
            return new ResponseEntity<>(user, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateEmail(@PathVariable("id") Long id, @RequestBody User user){
        return new ResponseEntity<>(userService.updateEmail(user, id), HttpStatus.OK);
    }
    @PutMapping("/signUp/{id}")
    public ResponseEntity<Boolean> signUpLecture (@PathVariable("id") Long lectureId, @RequestBody User user) throws IOException {
        if(userService.findUser(user, lectureId)!=null) {
            return new ResponseEntity<>(false,
                    HttpStatus.IM_USED);
        }
        if(userService.findUser(user,
                lectureId<=3 ? new String[] {"1","2","3"} : lectureId<=6 ? new String[] {"4","5","6"}: new String[] {"7","8","9"}) !=null){
            return new ResponseEntity<>(false,
                    HttpStatus.CONFLICT);
        }
        if(userService.countUser(lectureId)>=5){
            return new ResponseEntity<>(false,
                    HttpStatus.SEE_OTHER);
        }
        userService.emailMsg(new LectureUser(user.getId(), lectureId));
        return new ResponseEntity<>(userService.signUp(user, lectureId), HttpStatus.CREATED);
    }

    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<Boolean> cancel (@PathVariable("id") Long lectureId, @RequestBody User user){
        userService.cancel(user, lectureId);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}
