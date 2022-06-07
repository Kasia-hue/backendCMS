package com.example.demo;

import com.example.demo.model.Lecture;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		Lecture lecture1 = new Lecture(1, "10:00", "11:45", 1);
		Lecture lecture2 = new Lecture(2, "10:00", "11:45", 2);
		Lecture lecture3 = new Lecture(3, "10:00", "11:45", 3);
		Lecture lecture4 = new Lecture(4, "12:00", "13:45", 1);
		Lecture lecture5 = new Lecture(5, "12:00", "13:45", 2);
		Lecture lecture6 = new Lecture(6, "12:00", "13:45", 3);
		Lecture lecture7 = new Lecture(7, "14:00", "15:45", 1);
		Lecture lecture8 = new Lecture(8, "14:00", "15:45", 2);
		Lecture lecture9 = new Lecture(9, "14:00", "15:45", 3);

		List<Lecture> lectureList = new ArrayList<>();
		lectureList.add(lecture1);
		lectureList.add(lecture2);
		lectureList.add(lecture3);
		lectureList.add(lecture4);
		lectureList.add(lecture5);
		lectureList.add(lecture6);
		lectureList.add(lecture7);
		lectureList.add(lecture8);
		lectureList.add(lecture9);
	}
}
