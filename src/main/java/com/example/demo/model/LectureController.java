package com.example.demo.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/lectures")
public class LectureController {

    LectureService lectureService;

    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    @GetMapping("/schedule")
    public List<Lecture> getAll(){
        return this.lectureService.getAllLectures();
    }

    @GetMapping({"/{login}"})
    public ResponseEntity<List<Lecture>> getUser (@PathVariable("login") String login){
        return new ResponseEntity<>(lectureService.findUserLectures(login), HttpStatus.OK);
    }

    @GetMapping("/percentOfUsersLecture")
    public List<LectureService.KeyValue> percentOfUsersLecture (){
        return lectureService.percentUsers();
    }

    @GetMapping("/percentOfUsersPath")
    public List<LectureService.KeyValue> percentOfUsersPath (){
        return lectureService.percentUsersPath();
    }
}
