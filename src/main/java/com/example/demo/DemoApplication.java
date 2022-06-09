package com.example.demo;

import com.example.demo.model.Lecture;
import com.example.demo.model.LectureService;
import com.example.demo.repo.LectureRepository;
import com.example.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(DemoApplication.class, args);

		LectureRepository lectureRepository = configurableApplicationContext.getBean(LectureRepository.class);


		Lecture lecture1 = new Lecture("10:00", "11:45", 1L);
		lectureRepository.save(lecture1);
		Lecture lecture2 = new Lecture( "10:00", "11:45", 2L);
		lectureRepository.save(lecture2);
		Lecture lecture3 = new Lecture( "10:00", "11:45", 3L);
		lectureRepository.save(lecture3);
		Lecture lecture4 = new Lecture( "12:00", "13:45", 1L);
		lectureRepository.save(lecture4);
		Lecture lecture5 = new Lecture( "12:00", "13:45", 2L);
		lectureRepository.save(lecture5);
		Lecture lecture6 = new Lecture( "12:00", "13:45", 3L);
		lectureRepository.save(lecture6);
		Lecture lecture7 = new Lecture( "14:00", "15:45", 1L);
		lectureRepository.save(lecture7);
		Lecture lecture8 = new Lecture( "14:00", "15:45", 2L);
		lectureRepository.save(lecture8);
		Lecture lecture9 = new Lecture( "14:00", "15:45", 3L);
		lectureRepository.save(lecture9);
	}
}
