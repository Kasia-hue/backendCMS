package com.example.demo.repo;

import com.example.demo.model.LectureUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureUserRepository extends JpaRepository<LectureUser, Long> {

}
