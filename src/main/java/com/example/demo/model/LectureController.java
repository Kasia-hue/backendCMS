package com.example.demo.model;

import com.example.demo.DemoApplication;
import com.example.demo.repo.LectureRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

}
