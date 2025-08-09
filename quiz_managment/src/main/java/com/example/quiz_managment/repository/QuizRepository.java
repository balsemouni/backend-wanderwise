package com.example.quiz_managment.repository;

import com.example.quiz_managment.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
